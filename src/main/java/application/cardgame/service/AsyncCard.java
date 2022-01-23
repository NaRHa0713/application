package application.cardgame.service;

import java.util.concurrent.TimeUnit;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.ArrayList;

import application.cardgame.model.Card;
import application.cardgame.model.CardMapper;

import application.cardgame.model.Room;
import application.cardgame.model.Game;

import application.cardgame.model.SendInfo;

import application.cardgame.model.Hand;

@Service
public class AsyncCard {
  int count = 0;

  int rank = 1;

  boolean dbUpdated = false;

  String user_name;

  ArrayList<Integer> ranking = new ArrayList<Integer>();

  @Autowired
  private Room room;

  @Autowired
  private Game game;

  @Autowired
  private CardMapper cardmapper;

  private final Logger logger = LoggerFactory.getLogger(AsyncCard.class);

  @Transactional
  public void playCard(int id, Principal prin) {
    String name = prin.getName();
    // 削除対象のフルーツを取得
    if (this.game.cardContain(id) && checkMove(name)) {
      this.game.changePlayCards(id);
      this.room.discard(id);
      cardmapper.updateById(id);

      if (checkWin(this.room.getAllHand().get(count))) {
        ranking = this.room.getRank();
        ranking.set(count, rank);
        this.room.setRank(ranking);
        rank++;
      }

      count++;
      if (count == 4) {
        count = 0;
      }
      ranking = this.room.getRank();
      while (ranking.get(count) > 0) {
        // ranking = this.room.getRank();
        if (checkEnd(ranking)) {
          break;
        }
        count++;
        if (count == 4) {
          count = 0;
        }
      }
    }
    this.dbUpdated = true;
    return;
  }

  public void doPass(Principal prin) {
    String name = prin.getName();
    if (checkMove(name)) {
      count++;
      if (count == 4) {
        count = 0;
      }
      ranking = this.room.getRank();
      while (ranking.get(count) > 0) {
        // ranking = this.room.getRank();
        if (checkEnd(ranking)) {
          break;
        }
        count++;
        if (count == 4) {
          count = 0;
        }
      }
    }
    this.dbUpdated = true;
    return;
  }

  @Async
  public void count(SseEmitter emitter, Principal prin) {
    dbUpdated = true;
    logger.info("count start");
    try {
      while (true) {// 無限ループ
        logger.info("send:" + room.getUsers());

        ArrayList<Card> cards = cardmapper.selectAllCards(); //

        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }

        SendInfo info = new SendInfo();
        this.room.setNo((this.room.getNo() + 1) % 4); // Noのカウントアップ
        info.setCards(cards); // 送る情報に格納(カード情報)
        info.setRoom(room); // 送る情報に格納(ユーザ情報)
        user_name = prin.getName();
        info.setUserName(user_name);
        info.setCounter(count);
        emitter.send(info);// ここでsendすると引数をブラウザにpushする
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
        /*
         * logger.info("send:" + cards.get(1).getImage()); logger.info("send:" +
         * room.getHand());
         */
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
      // 例外が発生したらカウントとsendを終了する
    } finally {
      emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る
    }
  }

  public boolean checkMove(String name) {
    ArrayList<String> u = this.room.getUsers();
    if (name.equals(u.get(count))) {
      return true;
    }
    return false;
  }

  public boolean checkWin(Hand hand) {
    if (hand.getHand().size() == 0) {
      return true;
    }
    return false;
  }

  public boolean checkEnd(ArrayList<Integer> rank) {
    int flag = 0;
    for (int i = 0; i < rank.size(); i++) {
      if (rank.get(i) == 0) {
        flag = 1;
      }
    }
    if (flag == 0) {
      return true;
    }
    return false;
  }
}

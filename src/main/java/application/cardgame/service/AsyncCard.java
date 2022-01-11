package application.cardgame.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import application.cardgame.model.Card;
import application.cardgame.model.CardMapper;

import application.cardgame.model.Room;

import application.cardgame.model.SendInfo;

@Service
public class AsyncCard {
  // int count = 1;

  @Autowired
  private Room room;

  @Autowired
  private CardMapper cardmapper;

  private final Logger logger = LoggerFactory.getLogger(AsyncCard.class);

  @Async
  public void count(SseEmitter emitter) {
    logger.info("count start");
    while (true) {// 無限ループ
      try {
        logger.info("send:" + room.getUsers());
        TimeUnit.SECONDS.sleep(1);// 1秒STOP

        ArrayList<Card> cards = cardmapper.selectAllCards(); // カード情報取得

        SendInfo info = new SendInfo();
        info.setCards(cards); // 送る情報に格納(カード情報)
        info.setRoom(room); // 送る情報に格納(ユーザ情報)
        logger.info("send:" + cards);
        logger.info("send:" + info);
        emitter.send(info);// ここでsendすると引数をブラウザにpushする

        /*
         * logger.info("send:" + cards.get(1).getImage()); logger.info("send:" +
         * room.getHand());
         */

      } catch (Exception e) {
        // 例外の名前とメッセージだけ表示する
        logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
        // 例外が発生したらカウントとsendを終了する
        emitter.complete();// emitterの後始末．明示的にブラウザとの接続を一度切る．
        break;
      }
    }

  }
}

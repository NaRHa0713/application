package application.cardgame.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Random;
import org.springframework.transaction.annotation.Transactional;

import application.cardgame.model.Room;
import application.cardgame.service.AsyncCard;
import application.cardgame.model.Card;
import application.cardgame.model.CardMapper;
import application.cardgame.model.Hand;
import application.cardgame.model.Game;

@Controller
public class GameController {
  int flag = 0;

  @Autowired
  private Room room;

  @Autowired
  private Game game;

  @Autowired
  CardMapper cardmapper;

  @GetMapping("/7narabe2")
  public String sample22(Principal prin) {
    if (flag == 0) {
      ArrayList<Card> all_card = cardmapper.selectAllCards();
      ArrayList<Card> hand = new ArrayList<Card>();
      ArrayList<Hand> allHand = new ArrayList<Hand>();
      Hand hand_card = new Hand();
      Random rnd = new Random();
      int r;
      for (int j = 0; j < 4; j++) {
        for (int i = 0; i < 13; i++) {
          r = rnd.nextInt(all_card.size());
          if (all_card.get(r).getId() % 13 != 7) {
            hand.add(all_card.get(r));
          }
          all_card.remove(r);
        }
        hand_card.setHand(hand);
        allHand.add(hand_card);
        hand = new ArrayList<Card>();
        hand_card = new Hand();
      }
      this.room.setAllHand(allHand);
      flag = 1;
    }
    return "7narabe2.html";
  }

  private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

  @Autowired
  private AsyncCard ac57;

  @GetMapping("step6")
  public SseEmitter pushCount() {
    // infoレベルでログを出力する
    logger.info("pushCount");

    // push処理の秘密兵器．これを利用してブラウザにpushする
    // finalは初期化したあとに再代入が行われない変数につける（意図しない再代入を防ぐ）
    final SseEmitter sseEmitter = new SseEmitter(-1L);
    this.ac57.count(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("playCard")
  public String play(@RequestParam Integer id, Principal prin) {
    this.ac57.playCard(id, prin);
    return "7narabe2.html";

  }
}

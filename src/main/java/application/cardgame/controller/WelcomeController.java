package application.cardgame.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Random;

import application.cardgame.model.Room;
import application.cardgame.service.AsyncUser;
import application.cardgame.model.Card;
import application.cardgame.model.CardMapper;

@Controller
public class WelcomeController {

  @Autowired
  private Room room;

  @GetMapping("/welcome")
  public String sample21() {
    return "welcome.html";
  }

  @GetMapping("/back")
  public String sample10(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.logoutUser(loginUser);
    return "logout.html";
  }

  @GetMapping("/7narabe")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("room", this.room);

    return "7narabe.html";
  }

  private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

  @Autowired
  private AsyncUser ac56;

  @GetMapping("step5")
  public SseEmitter pushCount() {
    // infoレベルでログを出力する
    logger.info("pushCount");

    // push処理の秘密兵器．これを利用してブラウザにpushする
    // finalは初期化したあとに再代入が行われない変数につける（意図しない再代入を防ぐ）
    final SseEmitter sseEmitter = new SseEmitter(-1L);
    this.ac56.count(sseEmitter);
    return sseEmitter;
  }

}

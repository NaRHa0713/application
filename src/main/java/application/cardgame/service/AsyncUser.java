package application.cardgame.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.beans.factory.annotation.Autowired;

import application.cardgame.model.Room;

@Service
public class AsyncUser {
  // int count = 1;

  @Autowired
  private Room room;

  private final Logger logger = LoggerFactory.getLogger(AsyncUser.class);

  @Async
  public void count(SseEmitter emitter) {
    logger.info("count start");
    while (true) {// 無限ループ
      try {
        logger.info("send:" + room.getUsers());
        TimeUnit.SECONDS.sleep(1);// 1秒STOP
        emitter.send(room.getUsers());// ここでsendすると引数をブラウザにpushする
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

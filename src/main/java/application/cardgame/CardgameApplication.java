package application.cardgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CardgameApplication {

  public static void main(String[] args) {
    SpringApplication.run(CardgameApplication.class, args);
  }

}

package application.cardgame.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.cardgame.model.Room;

@Controller
public class WelcomeController {

  @Autowired
  private Room room;

  @GetMapping("/welcome")
  public String sample21() {
    return "welcome.html";
  }

  @GetMapping("/welcome/room")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.room.addUser(loginUser);
    model.addAttribute("room", this.room);

    return "7narabe.html";
  }

}

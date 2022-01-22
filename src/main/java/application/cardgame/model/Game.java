package application.cardgame.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class Game {
  ArrayList<Integer> playCards = new ArrayList<Integer>(Arrays.asList(6,19,32,45,8,21,34,47));

  public Game() {

    this.playCards.add(6);
    this.playCards.add(19);
    this.playCards.add(32);
    this.playCards.add(45);
    this.playCards.add(8);
    this.playCards.add(21);
    this.playCards.add(34);
    this.playCards.add(47);
  }

  public void playCard(int id) {
    this.playCards.remove(this.playCards.indexOf(id));
  }

  public boolean cardContain(int id) {
    if (this.playCards.contains(id)) {
      return true;
    }
    return false;
  }

  public void changePlayCards(int id) {
    for (int i = 0; i < this.playCards.size(); i++) {
      if (this.playCards.get(i) == id) {
        if (i < 4) {
          this.playCards.set(i, this.playCards.get(i) - 1);
          if (this.playCards.get(i) % 13 == 0) {
            this.playCards.set(i, -1);
          }
        } else {
          this.playCards.set(i, this.playCards.get(i) + 1);
          if (this.playCards.get(i) % 13 == 1) {
            this.playCards.set(i, -1);
          }
        }
      }
    }
  }

  public ArrayList<Integer> getPlayCards() {
    return playCards;
  }

  public void setPlayCards(ArrayList<Integer> playCards) {
    this.playCards = playCards;
  }

}

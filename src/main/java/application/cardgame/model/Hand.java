package application.cardgame.model;

import java.util.ArrayList;

public class Hand {
  ArrayList<Card> hand = new ArrayList<Card>();

  public ArrayList<Card> getHand() {
    return hand;
  }

  public void setHand(ArrayList<Card> hand) {
    this.hand = hand;
  }

  public void remove(int id) {
    for (int i = 0; i < this.hand.size(); i++) {
      if (this.hand.get(i).isContain(id)) {
        this.hand.remove(i);
      }
    }
  }

}

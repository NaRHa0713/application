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

}

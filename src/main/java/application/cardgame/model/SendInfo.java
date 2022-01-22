package application.cardgame.model;

import java.util.ArrayList;

public class SendInfo {
  ArrayList<Card> cards;
  boolean move;
  Room room;

  // Thymeleafでフィールドを扱うためにはgetter/setterが必ず必要
  // vscodeのソースコード右クリック->ソースアクションでsetter/getterを簡単に追加できる
  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public void setCards(ArrayList<Card> cards) {
    this.cards = cards;
  }

  public boolean getMove() {
    return move;
  }

  public void setMove(Boolean move) {
    this.move = move;
  }

}

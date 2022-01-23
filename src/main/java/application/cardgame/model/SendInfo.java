package application.cardgame.model;

import java.util.ArrayList;

public class SendInfo {
  ArrayList<Card> cards;
  String user_name;
  Room room;
  int count;

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

  public String getUserName() {
    return user_name;
  }

  public void setUserName(String user_name) {
    this.user_name = user_name;
  }

  public int getCounter() {
    return count;
  }

  public void setCounter(int count) {
    this.count = count;
  }
}

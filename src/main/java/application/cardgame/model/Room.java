package application.cardgame.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Room {
  ArrayList<String> users = new ArrayList<>();
  int roomNo = 1;

  /*
   * String hand[][] = new String[4][]; ArrayList<Card> cards = new
   * ArrayList<Card>(); ArrayList<cards> hand_card = new ArrayList<cards>();
   */

  ArrayList<ArrayList<Card>> hand_card = new ArrayList<ArrayList<Card>>();

  public void addUser(String name) {
    // 同名のユーザが居たら何もせずにreturn
    for (String s : this.users) {
      if (s.equals(name)) {
        return;
      }
    }
    // 同名のユーザが居なかった場合はusersにnameを追加する
    this.users.add(name);
  }

  public void logoutUser(String name) {
    for (int i = 0; i < this.users.size(); i++) {
      if (this.users.get(i).equals(name)) {
        this.users.remove(i);
      }
    }
  }

  public void addCard(ArrayList<Card> hand) {
    // 同名のユーザが居なかった場合はusersにnameを追加する
    this.hand_card.add(hand);
  }

  // 以降はフィールドのgetter/setter
  // これらがないとThymeleafで値を取得できない
  public int getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(int roomNo) {
    this.roomNo = roomNo;
  }

  public ArrayList<String> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<String> users) {
    this.users = users;
  }

  public ArrayList<ArrayList<Card>> getHand() {
    return hand_card;
  }

  public void setHand(ArrayList<ArrayList<Card>> hand) {
    this.hand_card = hand;
  }

}

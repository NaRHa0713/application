package application.cardgame.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Room {
  ArrayList<String> users = new ArrayList<>();
  int No = 1;

  ArrayList<Hand> allHand = new ArrayList<Hand>();

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

  public void discard(int id) {
    for (int i = 0; i < this.allHand.size(); i++) {
      this.allHand.get(i).remove(id);
    }
  }

  // 以降はフィールドのgetter/setter
  // これらがないとThymeleafで値を取得できない
  public int getNo() {
    return No;
  }

  public void setNo(int No) {
    this.No = No;
  }

  public ArrayList<String> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<String> users) {
    this.users = users;
  }

  public ArrayList<Hand> getAllHand() {
    return allHand;
  }

  public void setAllHand(ArrayList<Hand> allHand) {
    this.allHand = allHand;
  }

}

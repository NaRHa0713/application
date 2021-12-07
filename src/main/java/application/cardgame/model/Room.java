package application.cardgame.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Room {
  ArrayList<String> users = new ArrayList<>();
  int roomNo = 1;

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
    for (String s : this.users) {
      if (s.equals(name)) {
        this.users.remove(name);
      }
    }
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

}

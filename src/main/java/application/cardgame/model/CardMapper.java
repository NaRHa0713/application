package application.cardgame.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CardMapper {

  // @Select("SELECT id,user,number from chamber where id = #{id}")
  // Chamber selectById(int id);

  // @Select("SELECT * from chamber where number = #{number}")
  // ArrayList<Chamber> selectAllByNumber(int number);

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  @Select("select * from CARD;")
  ArrayList<Card> selectAllCards();

  @Update("UPDATE CARD SET STATE='visible' WHERE ID = #{id}")
  void updateById(int id);

}

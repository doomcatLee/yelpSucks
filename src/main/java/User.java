import org.sql2o.*;
import java.util.List;

public class User{

  private int id;
  private String username;
  private String password;

  public User(String a, String b){
    username = a;
    password = b;
  }


  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }

  public int getId(){
    return id;
  }


  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO users (username, password) VALUES (:username, :password)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("username", username)
      .addParameter("password", password)
      .executeUpdate()
      .getKey();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM users WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static List<User> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users";
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public static User find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM users WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(User.class);
    }
  }

  @Override
  public boolean equals(Object otherUser){
    if(!(otherUser instanceof User)){
      return false;
    } else{
      User newUser = (User) otherUser;
      return this.getUsername().equals(newUser.getUsername()) && this.getId() == newUser.getId();
    }
  }
}

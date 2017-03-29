import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private String type;
  private int id;

  public Cuisine(String type) {
    this.type = type;
  }

  public String getType(){
    return type;
  }

  public int getId(){
    return id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cuisines (type) VALUES (:type)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Cuisine> all(){
    String sql = "SELECT * FROM cuisines";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  public static Cuisine find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cuisines WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
    }
  }

  @Override
  public boolean equals(Object otherCuisine){
    if(!(otherCuisine instanceof Cuisine)){
      return false;
    } else{
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) && this.getId() == newCuisine.getId();
    }
  }

}

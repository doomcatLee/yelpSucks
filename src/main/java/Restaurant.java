import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private String name;
  private String description;
  private int rating;
  private int avgPrice;
  private int cuisineId;
  private int locationId;
  private int id;

  public Restaurant(String name, String description, int rating, int avgPrice, int cuisineId, int locationId) {
    this.name = name;
    this.description = description;
    this.rating = rating;
    this.avgPrice = avgPrice;
    this.cuisineId = cuisineId;
    this.locationId = locationId;
  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description;
  }

  public int getRating(){
    return rating;
  }

  public int getPriceRange(){
    return avgPrice;
  }

  public int getCuisineId(){
    return cuisineId;
  }

  public int getLocationId(){
    return locationId;
  }

  public int getId(){
    return id;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO restaurants (name, description, rating, avgPrice, cuisineId, locationId) VALUES (:name, :description, :rating, :avgPrice, :cuisineId, :locationId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("rating", this.rating)
        .addParameter("avgPrice", this.avgPrice)
        .addParameter("cuisineId", this.cuisineId)
        .addParameter("locationId", this.locationId)
        .executeUpdate()
        .getKey();
    }
  }

  public void update(String column, String newInfo){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE restaurants SET name = :newInfo WHERE id=:id";
      con.createQuery(sql)
        .addParameter("newInfo", newInfo)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM restaurants WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id",id)
        .executeUpdate();
    }
  }

  public static List<Restaurant> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM restaurants";
      return con.createQuery(sql)
        .executeAndFetch(Restaurant.class);
    }
  }

  public static Restaurant find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM restaurants WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if(!(otherRestaurant instanceof Restaurant)){
      return false;
    } else{
      Restaurant newRest = (Restaurant) otherRestaurant;
      return this.getName().equals(newRest.getName()) && this.getId() == newRest.getId();
    }
  }

}

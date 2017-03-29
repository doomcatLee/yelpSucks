import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource{

  @Override
  protected void before(){
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/java_yelp_sucks_test", null, null);
  }

  @Override
  protected void after(){
    try(Connection con = DB.sql2o.open()){
      String deleteCuisinesQuery = "DELETE FROM cuisines *;";
      String deleteRestaurantsQuery = "DELETE FROM restaurants *;";
      String deleteLocationsQuery = "DELETE FROM locations *;";
      con.createQuery(deleteCuisinesQuery).executeUpdate();
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
    }
  }
}

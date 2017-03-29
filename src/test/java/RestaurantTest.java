import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void restaurant_instantiates_correctly(){
    Restaurant newRest = new Restaurant("Bob's", "Best burger", 5, 20, 1, 1);
    assertTrue(newRest instanceof Restaurant);
  }

  @Test
  public void test_getters(){
    Restaurant newRest = new Restaurant("Bob's", "Best burger", 5, 20, 1, 1);
    assertEquals("Bob's", newRest.getName());
    assertEquals("Best burger", newRest.getDescription());
    assertEquals(5, newRest.getRating());
    assertEquals(20, newRest.getPriceRange());
    assertEquals(1, newRest.getCuisineId());
    assertEquals(1, newRest.getLocationId());
  }

  @Test
  public void save_savesCorrectly(){
    Restaurant newRest = new Restaurant("Bob's", "Best burger", 5, 20, 1, 1);
    newRest.save();
    assertTrue(Restaurant.all().get(0).equals(newRest));
  }

  @Test
  public void all_returnsAllInstancesOfRestaurant(){
    Restaurant firstRest = new Restaurant("Bob's", "Best burger", 5, 20, 1, 1);
    firstRest.save();
    Restaurant secondRest = new Restaurant("Jane's", "Best sandwiches", 4, 10, 1, 1);
    secondRest.save();
    assertEquals(firstRest, Restaurant.all().get(0));
    assertEquals(secondRest, Restaurant.all().get(1));
  }

  @Test
  public void find_returnsRestaurantWithSameId(){
    Restaurant newRest = new Restaurant("Bob's", "Best burger", 5, 20, 1, 1);
    newRest.save();
    assertEquals(newRest, Restaurant.find(newRest.getId()));
  }

  @Test
  public void update_updatesEntryCorrectly(){
    Restaurant newRest = new Restaurant("Bobs", "Best burger", 5, 20, 1, 1);
    newRest.save();
    newRest.update("name", "Janes");
    assertEquals("Janes", Restaurant.all().get(0).getName());
  }

  @Test
  public void delete_restaurant(){
    Restaurant newRest = new Restaurant("Bobs", "Best burger", 5, 20, 1, 1);
    newRest.save();
    int id = newRest.getId();
    newRest.delete();
    assertEquals(null, Restaurant.find(id));
  }
}

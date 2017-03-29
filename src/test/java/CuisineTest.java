import org.sql2o.*;
import org.junit.*;
import java.util.Arrays;
import static org.junit.Assert.*;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void newCuisine_initializesCorrectly(){
    Cuisine testCuisine = new Cuisine("Chinese");
    assertTrue(testCuisine instanceof Cuisine);
  }

  @Test
  public void newCuisine_returns_name(){
    Cuisine testCuisine = new Cuisine("Chinese");
    assertEquals("Chinese", testCuisine.getType());
  }

  @Test
  public void newcuisine_save_works(){
    Cuisine testCuisine = new Cuisine("Chinese");
    testCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(testCuisine));
  }

  @Test
  public void all_returnsAllInstancesOfCuisine_true(){
    Cuisine firstCuisine = new Cuisine("Chinese");
    firstCuisine.save();
    Cuisine secondCuisine = new Cuisine("Mexican");
    secondCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(firstCuisine));
    assertTrue(Cuisine.all().get(1).equals(secondCuisine));
  }

  @Test
  public void save_assignsIdToObject(){
    Cuisine testCuisine = new Cuisine("Chinese");
    testCuisine.save();
    Cuisine savedCuisine = Cuisine.all().get(0);
    assertEquals(testCuisine.getId(), savedCuisine.getId());
  }

  @Test
  public void find_returnsCuisineWithSameId(){
    Cuisine firstCuisine = new Cuisine("Chinese");
    firstCuisine.save();
    Cuisine secondCuisine = new Cuisine("Mexican");
    secondCuisine.save();
    assertEquals(Cuisine.find(secondCuisine.getId()), secondCuisine);
  }

  @Test
  public void getRestuarantsFromCuisineObject(){
    Cuisine newCuisine = new Cuisine("Italian");
    newCuisine.save();
    Restaurant firstRest = new Restaurant("Bob's", "Best burger", 5, 20, newCuisine.getId(), 1);
    firstRest.save();
    Restaurant secondRest = new Restaurant("Jane's", "Best sandwiches", 4, 10, newCuisine.getId(), 1);
    secondRest.save();
    Restaurant[] rests = new Restaurant[] {firstRest, secondRest};
    assertTrue(newCuisine.getRestaurants().containsAll(Arrays.asList(rests)));
  }
}

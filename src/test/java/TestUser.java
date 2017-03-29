import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestUser{
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void user_Object_instantiation(){
    User newUser = new User("doomcat", "12345best");
    assertTrue(newUser instanceof User);
  }

  @Test
  public void user_getters_mofo(){
    User newUser = new User("doomcat", "12345best");
    assertEquals("doomcat", newUser.getUsername());
    assertEquals("12345best", newUser.getPassword());
  }

  @Test
  public void save_savesCorrectly(){
    User newUser = new User("doomcat", "12345best");
    newUser.save();
    assertTrue(User.all().get(0).equals(newUser));
  }
}

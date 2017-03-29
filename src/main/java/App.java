import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("type");
      Cuisine newCuisine = new Cuisine(type);
      newCuisine.save();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cuisines-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/cuisine-delete.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:cuisineId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Cuisine newCuis = Cuisine.find(Integer.parseInt(request.params("cuisineId")));
      model.put("cuisine", newCuis);
      model.put("template", "templates/cuisine.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:cuisineId/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params("cuisineId")));
      cuisine.delete();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisinesUser", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("type");
      Cuisine newCuisine = new Cuisine(type);
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/cuisinesUser.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/form-login", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("type");
      Cuisine newCuisine = new Cuisine(type);
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/form-login.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }
}

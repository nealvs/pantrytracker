package controllers.tracker;

import play.mvc.Controller;
import play.mvc.Result;


public class CategoryController extends Controller {
  
    public static Result index() {
        return ok("Category"); 
        //return ok(index.render("Your new 2 application is ready.")); 
    }
  
}

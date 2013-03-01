package controllers.tracker;

import play.mvc.Controller;
import play.mvc.Result;


public class LocationController extends Controller {
  
    public static Result index() {
        return ok("Location"); 
        //return ok(index.render("Your new 2 application is ready.")); 
    }
  
}

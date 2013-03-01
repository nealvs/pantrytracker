package controllers.tracker;

import play.mvc.Controller;
import play.mvc.Result;


public class DashboardController extends Controller {
  
    public static Result index() {
        return ok("Dashboard"); 
        //return ok(index.render("Your new 2 application is ready.")); 
    }
  
}

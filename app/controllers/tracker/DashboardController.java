package controllers.tracker;

import controllers.security.TrackerSecured;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(TrackerSecured.class)
public class DashboardController extends Controller {
  
    public static Result index() {
        return ok("Dashboard"); 
        //return ok(index.render("Your new 2 application is ready.")); 
    }
  
}

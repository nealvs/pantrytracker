package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok("Pantry Tracker"); 
        //return ok(index.render("Your new 2 application is ready.")); 
    }
  
}

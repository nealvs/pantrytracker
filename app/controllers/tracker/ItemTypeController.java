package controllers.tracker;

import play.mvc.Controller;
import play.mvc.Result;


public class ItemTypeController extends Controller {
  
    public static Result index() {
        return ok("ItemType"); 
        //return ok(index.render("Your new 2 application is ready.")); 
    }
  
    public static Result add() {
        return TODO; 
    }
    public static Result save() {
        return TODO; 
    }
    public static Result delete() {
        return TODO; 
    }
    public static Result view() {
        return TODO; 
    }
    
}

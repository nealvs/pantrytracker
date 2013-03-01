package controllers;

import models.account.User;
import play.mvc.Controller;
import play.data.Form;
import play.mvc.Result;
import views.html.login;


public class LoginController extends Controller {
  
    public static class Login {
        public String email;
        public String password;
        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }
    
    public static Result index() {
        return ok(
            login.render(Form.form(Login.class))
        );
    }
  
    public static Result login() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            return redirect(controllers.tracker.routes.DashboardController.index());
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.LoginController.index());
    }
    

}

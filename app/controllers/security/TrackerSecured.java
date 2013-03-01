package controllers.security;

import controllers.routes;
import models.account.Account;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;


public class TrackerSecured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.LoginController.index());
    }
    
    // Access rights
    public static boolean isMemberOf(Long accountId) {
        return Account.isMember(accountId, Context.current().request().username()
        );
    }
    
}

package models.tracker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class RecipeIngredient extends Model {

    @Id
    @Column(name = "recipe_ingredient_id")
    public Long recipeIngredientId;
    
    @ManyToOne
    @Constraints.Required
    @JoinColumn(name="account_id")
    public Account account;
    
    
    
}

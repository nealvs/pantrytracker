package models.tracker;

import java.util.Date;
import javax.persistence.*;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class RecipeCategory extends Model {

    @Id
    @Column(name = "recipe_category_id")
    public Long recipeCategoryId;
    
    @Column(name = "name")
    public String name;
    
    @Column(name = "sort_order")
    public Integer sortOrder;
    
    @Column(name = "created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date created;
    
    @Column(name = "deleted")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date deleted;
    
    @ManyToOne
    @Constraints.Required
    @JoinColumn(name="account_id")
    public Account account;
    
}

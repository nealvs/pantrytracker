package models.tracker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class Item extends Model {

    @Id
    @Column(name = "item_id")
    public Long itemId;
    
    @Column(name = "sku")
    public String sku;
    
    @ManyToOne
    @Constraints.Required
    @JoinColumn(name="account_id")
    public Account account;
    
    
}

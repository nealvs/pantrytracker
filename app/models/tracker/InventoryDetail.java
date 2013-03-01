package models.tracker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class InventoryDetail extends Model {

    @Id
    @Column(name = "inventory_detail_id")
    public Long inventoryDetailId;
    
    
    @ManyToOne
    @Constraints.Required
    public Account account;
    
}

package models.tracker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class InventoryHeader extends Model {

    @Id
    @Column(name = "inventory_header_id")
    public Long inventoryHeaderId;
    
    
    @ManyToOne
    @Constraints.Required
    public Account account;
    
}

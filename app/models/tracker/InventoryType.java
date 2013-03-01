package models.tracker;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class InventoryType extends Model {

    @Id
    @Column(name = "inventory_type_id")
    public Long inventoryTypeId;
    
    @Column(name = "name")
    public String name;
    
    @Column(name = "sort_order")
    public Integer sortOrder;
    
    @Column(name = "active")
    public boolean active;
    
    @ManyToOne
    @Constraints.Required
    public Account account;
    
}

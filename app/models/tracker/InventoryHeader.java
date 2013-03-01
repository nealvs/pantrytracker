package models.tracker;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import models.account.Account;
import models.account.User;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class InventoryHeader extends Model {

    @Id
    @Column(name = "inventory_header_id")
    public Long inventoryHeaderId;
    
    @ManyToMany
    @JoinTable(name = "inventory_header_detail")
    public List<InventoryDetail> details = new ArrayList<InventoryDetail>();
    
    @OneToMany
    @JoinColumn(name="inventory_type_id")
    public InventoryType type;
    
    @ManyToOne
    @Constraints.Required
    @JoinColumn(name="account_id")
    public Account account;
    
    
}

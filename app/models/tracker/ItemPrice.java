package models.tracker;

import java.util.Date;
import javax.persistence.*;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class ItemPrice extends Model {

    @Id
    @Column(name = "item_price_id")
    public Long itemPriceId;
    
    @Column(name = "created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date created;
    
    @ManyToOne
    @Constraints.Required
    public Account account;
    
}

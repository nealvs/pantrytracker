package models.tracker;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import models.account.Account;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class ItemType extends Model {

    @Id
    @Column(name = "item_type_id")
    public Long itemTypeId;
    
    @Column(name = "created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date created;
    
    @Column(name = "deleted")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date deleted;
    
    @ManyToOne
    @Constraints.Required
    public Account account;
    
}

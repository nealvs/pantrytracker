package models.tracker;

import javax.persistence.Column;
import javax.persistence.Id;
import play.data.validation.Constraints;
import play.db.ebean.Model;


public class Unit extends Model {

    @Id
    @Column(name = "unit_id")
    public Long unitId;
    
    @Column(name = "name")
    @Constraints.Required
    public String name;
    
    @Column(name = "active")
    public boolean active;
    
    
    
}

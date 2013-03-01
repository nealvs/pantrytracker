package models.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity 
@Table(name="users")
public class User extends Model {

    @Id
    @Column(name = "user_id")
    public Integer userId;
    
    @Constraints.Required
    @Formats.NonEmpty
    @Column(name = "email")
    public String email;
    
    @Constraints.Required
    @Column(name = "name")
    public String name;
    
    @Column(name = "salt")
    public String salt;
    
    @Constraints.Required
    @Column(name = "password")
    public String password;
    
    @Constraints.Required
    @Column(name = "first_name")
    public String firstName;
    
    @Constraints.Required
    @Column(name = "last_name")
    public String lastName;
    
    @Column(name = "created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date created;
    
    @Column(name = "deleted")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date deleted;
    
    @Column(name = "last_login")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date lastLogin;
    
    @ManyToMany(mappedBy = "AccountUser")
    public List<Account> accounts = new ArrayList<Account>();
    
    // -- Queries
    public static Model.Finder<String,User> find = new Model.Finder(String.class, User.class);
    
    /**
     * Retrieve all users.
     */
    public static List<User> findAll() {
        return find.all();
    }

    /**
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    /**
     * Authenticate a User.
     */
    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }
    
    // --
    public String toString() {
        return "User(" + email + ")";
    }

}


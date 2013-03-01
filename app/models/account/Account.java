package models.account;

import com.avaje.ebean.Ebean;
import java.util.*;
import javax.persistence.*;
import play.db.ebean.Model;


@Entity 
public class Account extends Model {

    @Id
    @Column(name = "account_id")
    public Long accountId;
    
    @Column(name = "name")
    public String name;
    
    @ManyToMany
    @JoinTable(name = "account_user")
    public List<User> users = new ArrayList<User>();

    public Account() {
    }
    
    // -- Queries
    public static Model.Finder<Long, Account> find = new Model.Finder(Long.class, Account.class);
    
    /**
     * Retrieve project for user
     */
    public static List<Account> findInvolving(String user) {
        return find.where()
            .eq("members.email", user)
            .findList();
    }
    
    /**
     * Delete all project in a folder
     */
    public static void deleteInFolder(String folder) {
        Ebean.createSqlUpdate(
            "delete from project where folder = :folder"
        ).setParameter("folder", folder).execute();
    }
    
    /**
     * Create a new project.
     */
    public static Account create(String name) {
        //Account project = new Account(name);
        //project.save();
        //project.saveManyToManyAssociations("members");
        //return project;
        return null;
    }
    
    /**
     * Rename a project
     */
    public static String rename(Long projectId, String newName) {
        Account project = find.ref(projectId);
        project.name = newName;
        project.update();
        return newName;
    }
    
    /**
     * Add a member to this project
     */
    public static void addMember(Long project, String user) {
        Account p = Account.find.setId(project).fetch("users", "email").findUnique();
        p.users.add(
            User.find.ref(user)
        );
        p.saveManyToManyAssociations("users");
    }
    
    /**
     * Remove a member from this project
     */
    public static void removeMember(Long project, String user) {
        Account p = Account.find.setId(project).fetch("users", "email").findUnique();
        p.users.remove(
            User.find.ref(user)
        );
        p.saveManyToManyAssociations("users");
    }
    
    /**
     * Check if a user is a member of this project
     */
    public static boolean isMember(Long accountId, String user) {
        return find.where()
            .eq("users.email", user)
            .eq("id", accountId)
            .findRowCount() > 0;
    } 
    
    // --
    public String toString() {
        return "Account(" + accountId + ") with " + (users == null ? "null" : users.size()) + " users";
    }
    
}
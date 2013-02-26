package models.account;

import com.avaje.ebean.Ebean;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import play.db.ebean.Model;


@Entity 
public class Account extends Model {

    @Id
    public Long id;
    
    public String name;
    
    public String folder;
    
    @ManyToMany
    public List<User> members = new ArrayList<User>();

    public Account() {
    }
    
    public Account(String name, User owner) {
        this.name = name;
        this.folder = folder;
        this.members.add(owner);
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
    public static Account create(String name, String folder, String owner) {
        Account project = new Account(name, User.find.ref(owner));
        project.save();
        project.saveManyToManyAssociations("members");
        return project;
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
     * Rename a folder
     */
    public static String renameFolder(String folder, String newName) {
        Ebean.createSqlUpdate(
            "update project set folder = :newName where folder = :folder"
        ).setParameter("folder", folder).setParameter("newName", newName).execute();
        return newName;
    }
    
    /**
     * Add a member to this project
     */
    public static void addMember(Long project, String user) {
        Account p = Account.find.setId(project).fetch("members", "email").findUnique();
        p.members.add(
            User.find.ref(user)
        );
        p.saveManyToManyAssociations("members");
    }
    
    /**
     * Remove a member from this project
     */
    public static void removeMember(Long project, String user) {
        Account p = Account.find.setId(project).fetch("members", "email").findUnique();
        p.members.remove(
            User.find.ref(user)
        );
        p.saveManyToManyAssociations("members");
    }
    
    /**
     * Check if a user is a member of this project
     */
    public static boolean isMember(Long project, String user) {
        return find.where()
            .eq("members.email", user)
            .eq("id", project)
            .findRowCount() > 0;
    } 
    
    // --
    public String toString() {
        return "Account(" + id + ") with " + (members == null ? "null" : members.size()) + " members";
    }
    
}
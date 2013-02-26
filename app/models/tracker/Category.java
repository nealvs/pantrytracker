package models.tracker;

import com.avaje.ebean.Ebean;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import models.account.Account;
import models.account.User;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class Category extends Model {

    @Id
    public Long id;
    
    @Constraints.Required
    public String title;
    
    public boolean done = false;
    
    @Formats.DateTime(pattern="MM/dd/yy")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date dueDate;
    
    @ManyToOne
    public User assignedTo;
    
    public String folder;
    
    @ManyToOne
    public Account account;
    
    // -- Queries
    
    public static Model.Finder<Long,Category> find = new Model.Finder(Long.class, Category.class);
    
    /**
     * Retrieve todo tasks for the user.
     */
    public static List<Category> findTodoInvolving(String user) {
//       return find.join("account")
//           .where()
//                .eq("done", false)
//                .eq("project.members.email", user)
//           .findList();
        return null;
    }
    
    /**
     * Find tasks related to a project
     */
    public static List<Category> findByAccount(Long accountId) {
        return Category.find.where()
            .eq("project.id", accountId)
            .findList();
    }
    
    /**
     * Delete all tasks in a folder
     */
    public static void deleteInFolder(Long project, String folder) {
        Ebean.createSqlUpdate(
            "delete from task where folder = :folder and project_id = :project"
        ).setParameter("folder", folder)
         .setParameter("project", project)
         .execute();
    }
    
    /**
     * Rename a folder
     */
    public static String renameFolder(Long project, String folder, String newName) {
        Ebean.createSqlUpdate(
            "update task set folder = :newName where folder = :folder and project_id = :project"
        ).setParameter("folder", folder)
            .setParameter("newName", newName)
            .setParameter("project", project)
            .execute();
        return newName;
    }
    
    /**
     * Create a task
     */
    public static Category create(Category task, Long accountId, String folder) {
        task.account = Account.find.ref(accountId);
        task.folder = folder;
        task.save();
        return task;
    }
    
    /**
     * Mark a task as done or not
     */
    public static void markAsDone(Long taskId, Boolean done) {
        Category task = Category.find.ref(taskId);
        task.done = done;
        task.update();
    }
    
    /**
     * Check if a user is the owner of this task
     */
    public static boolean isOwner(Long task, String user) {
        return find.where()
            .eq("project.members.email", user)
            .eq("id", task)
            .findRowCount() > 0;
    }

    // --
    public String toString() {
        return "Category(" + id + ") in project " + account;
    }
}

package models.tracker;

import com.avaje.ebean.Ebean;
import java.util.*;
import javax.persistence.*;
import models.account.Account;
import models.account.User;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity 
public class ItemCategory extends Model {

    @Id
    @Column(name = "item_category_id")
    public Long itemCategoryId;
    
    @Constraints.Required
    public String name;
    
    @Column(name = "created")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date created;
    
    @Column(name = "deleted")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date deleted;
    
    @ManyToOne
    @Constraints.Required
    public Account account;
    
    // -- Queries
    public static Model.Finder<Long,ItemCategory> find = new Model.Finder(Long.class, ItemCategory.class);
    
    /**
     * Retrieve todo tasks for the user.
     */
    public static List<ItemCategory> findTodoInvolving(String user) {
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
    public static List<ItemCategory> findByAccount(Long accountId) {
        return ItemCategory.find.where()
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
    public static ItemCategory create(ItemCategory task, Long accountId, String folder) {
        task.account = Account.find.ref(accountId);
        task.folder = folder;
        task.save();
        return task;
    }
    
    /**
     * Mark a task as done or not
     */
    public static void markAsDone(Long taskId, Boolean done) {
        ItemCategory task = ItemCategory.find.ref(taskId);
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

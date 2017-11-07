package highlighter.checklistapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import highlighter.checklistapp.controller.AccessChecklistDB;
import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistDB;
import highlighter.checklistapp.entity.ChecklistItem;

/**
 * Created by Khorly on 2/11/17.
 */

public class ChecklistDAO {
    public static ChecklistDB accessChecklistDB;

    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");
        
        accessChecklistDB = new ChecklistDB(context);
        populateChecklist();

    }


    public static void populateChecklist() {
        if (accessChecklistDB.getAllCheckListItemFromArchive().size()==0) {
            //Checking to prevent duplication of items and checklist when doing initial population
            accessChecklistDB.addChecklistToDB("Daily item 1", "Daily", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(0,"Daily item 1 description1" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(0,"Daily item 1 description2" , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Daily item 2", "Daily", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,"Daily item 2 description1" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,"Daily item 2 description2" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,"Daily item 2 description3" , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Weekly item 2", "Weekly", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Weekly item 2 description1" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Weekly item 2 description2" , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Weekly item 3", "Weekly", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(3,"Weekly item 2 description1" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(3,"Weekly item 2 description2" , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Biweekly item 1", "Biweekly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Biweekly item 2", "Biweekly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Monthly item 1", "Monthly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Monthly item 2", "Monthly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Yearly item 1", "Yearly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Yearly item 2", "Yearly", ChecklistDB.ARCHIVE);
            
            ArrayList<ChecklistItem> items = new ArrayList<ChecklistItem>();
            items = accessChecklistDB.getAllCheckListItemFromArchive();
            Log.w("checklistDB", items.toString());
        }
    }
}

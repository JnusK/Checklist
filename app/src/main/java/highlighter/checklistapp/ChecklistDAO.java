package highlighter.checklistapp;

import android.content.Context;
import android.util.Log;

import highlighter.checklistapp.controller.AccessChecklistDB;
import highlighter.checklistapp.entity.ChecklistDB;

/**
 * Created by Khorly on 2/11/17.
 */

public class ChecklistDAO {
    public static ChecklistDB accessChecklistDB;


    static boolean call_once = true;
    static Context context2;


    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        context2 = context;
        accessChecklistDB = new ChecklistDB(context);
        populateChecklist(context);

    }


    public static void populateChecklist(Context context) {

        ChecklistDB db = new ChecklistDB(context2);

        if (call_once) {
            db.addChecklistToDB("Daily item 1", "Daily", ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(0,"Daily item 1 description1" , 0 , ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(0,"Daily item 1 description2" , 0 , ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Daily item 2", "Daily", ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(1,"Daily item 2 description1" , 0 , ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(1,"Daily item 2 description2" , 0 , ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Weekly item 2", "Weekly", ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(2,"Weekly item 2 description1" , 0 , ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(2,"Weekly item 2 description2" , 0 , ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Weekly item 3", "Weekly", ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(3,"Weekly item 2 description1" , 0 , ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(3,"Weekly item 2 description2" , 0 , ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Biweekly item 1", "Biweekly", ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Biweekly item 2", "Biweekly", ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Monthly item 1", "Monthly", ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Monthly item 2", "Monthly", ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Yearly item 1", "Yearly", ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Yearly item 2", "Yearly", ChecklistDB.ARCHIVE);
            call_once = false;
        }


    }
}

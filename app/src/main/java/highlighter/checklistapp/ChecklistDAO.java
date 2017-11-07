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
            db.addChecklistToDB("Daily Item 1 " ,0 , 0, "Daily" );
            db.addCheckListItemsToDB(0,0,"Daily item 1 description1" , 0, ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(1,0,"Daily item 1 description2" , 0, ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Daily Item 2 " ,1 , 0, "Daily" );
            db.addCheckListItemsToDB(2,1,"Daily item 2 description1" , 0, ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(4,1,"Daily item 2 description2" , 0, ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Weekly Item 2 " ,2 , 0, "Weekly" );
            db.addCheckListItemsToDB(5,2,"Weekly item 2 description1" , 0, ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(6,2,"Weekly item 2 description2" , 0, ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Weekly Item 3 " ,3 , 0, "Weekly" );
            db.addCheckListItemsToDB(7,3,"Weekly item 3 description1" , 0, ChecklistDB.ARCHIVE);
            db.addCheckListItemsToDB(8,3,"Weekly item 3 description2" , 0, ChecklistDB.ARCHIVE);

            db.addChecklistToDB("Biweekly Item 1" ,4 , 0, "Biweekly" );

            db.addChecklistToDB("Biweekly Item 2" ,5 , 0, "Biweekly" );

            db.addChecklistToDB("Monthly Item 1" ,6 , 0, "Monthly" );

            db.addChecklistToDB("Monthly Item 2" ,7 , 0, "Monthly" );

            db.addChecklistToDB("Yearly Item 1" ,8 , 0, "Yearly" );

            db.addChecklistToDB("Yearly Item 2" ,9 , 0, "Yearly" );
            call_once = false;
        }



    }
}

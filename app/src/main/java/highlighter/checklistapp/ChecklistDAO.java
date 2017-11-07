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


    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");
        accessChecklistDB = new ChecklistDB(context);
        populateChecklist();

    }


    public static void populateChecklist() {

        if (call_once) {
            accessChecklistDB.addChecklistToDB("Daily Item 1 " ,0 , 0, "Daily" );
            accessChecklistDB.addCheckListItemsToDB(0,0,"Daily item 1 description1" , 0, ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,0,"Daily item 1 description2" , 0, ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Daily Item 2 " ,1 , 0, "Daily" );
            accessChecklistDB.addCheckListItemsToDB(2,1,"Daily item 2 description1" , 0, ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(4,1,"Daily item 2 description2" , 0, ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Weekly Item 2 " ,2 , 0, "Weekly" );
            accessChecklistDB.addCheckListItemsToDB(5,2,"Weekly item 2 description1" , 0, ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(6,2,"Weekly item 2 description2" , 0, ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Weekly Item 3 " ,3 , 0, "Weekly" );
            accessChecklistDB.addCheckListItemsToDB(7,3,"Weekly item 3 description1" , 0, ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(8,3,"Weekly item 3 description2" , 0, ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Biweekly Item 1" ,4 , 0, "Biweekly" );

            accessChecklistDB.addChecklistToDB("Biweekly Item 2" ,5 , 0, "Biweekly" );

            accessChecklistDB.addChecklistToDB("Monthly Item 1" ,6 , 0, "Monthly" );

            accessChecklistDB.addChecklistToDB("Monthly Item 2" ,7 , 0, "Monthly" );

            accessChecklistDB.addChecklistToDB("Yearly Item 1" ,8 , 0, "Yearly" );

            accessChecklistDB.addChecklistToDB("Yearly Item 2" ,9 , 0, "Yearly" );
            call_once = false;
        }



    }
}

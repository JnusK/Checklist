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

    static Context context2;


    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        context2 = context;
        accessChecklistDB = new ChecklistDB(context);
        populateChecklist(context);

    }


    public static void populateChecklist(Context context){

        ChecklistDB db = new ChecklistDB(context2);

        db.addChecklistToDB("Daily item 1" , 1 , 1, "Daily");
        db.addCheckListItemsToDB(111,1,"Daily item 1 description" , 0);
        db.addCheckListItemsToDB(122,1,"Daily item 1 description" , 0);

        db.addChecklistToDB("Daily item 2" , 2 , 1, "Daily");
        db.addCheckListItemsToDB(222,2,"Daily item 2 description" , 0);
        db.addCheckListItemsToDB(233,2,"Daily item 2 description" , 0);

        db.addChecklistToDB("Weekly item 2" , 3 , 1, "Weekly");
        db.addCheckListItemsToDB(333,3,"Weekly item 1 description" , 0);
        db.addCheckListItemsToDB(334,3,"Weekly item 1 description" , 0);

        db.addChecklistToDB("Weekly item 3" , 4 , 1, "Weekly");
        db.addCheckListItemsToDB(444,4,"Weekly item 2 description" , 0);
        db.addCheckListItemsToDB(445,4,"Weekly item 2 description" , 0);

        db.addChecklistToDB("Biweekly item 1" , 5 , 1, "Biweekly");
        db.addCheckListItemsToDB(555,5,"Biweekly item 1 description" , 0);
        db.addCheckListItemsToDB(556,5,"Biweekly item 1 description" , 0);

        db.addChecklistToDB("Biweekly item 2" , 6 , 1, "Biweekly");
        db.addCheckListItemsToDB(666,6,"Biweekly item 2 description" , 0);
        db.addCheckListItemsToDB(667,6,"Biweekly item 2 description" , 0);

        db.addChecklistToDB("Monthly item 1" , 7 , 1, "Monthly");
        db.addCheckListItemsToDB(777,7,"Monthly item 1 description" , 0);
        db.addCheckListItemsToDB(778,7,"Monthly item 1 description" , 0);

        db.addChecklistToDB("Monthly item 2" , 8 , 1, "Monthly");
        db.addCheckListItemsToDB(888,8,"Monthly item 2 description" , 0);
        db.addCheckListItemsToDB(889,8,"Monthly item 2 description" , 0);

        db.addChecklistToDB("Yearly item 1" , 9 , 1, "Yearly");
        db.addCheckListItemsToDB(999,9,"Yearly item 1 description" , 0);
        db.addCheckListItemsToDB(990,9,"Yearly item 1 description" , 0);

        db.addChecklistToDB("Yearly item 2" , 10 , 1, "Yearly");
        db.addCheckListItemsToDB(1000,10,"Yearly item 2 description" , 0);
        db.addCheckListItemsToDB(1001,10,"Yearly item 2 description" , 0);
    }


}

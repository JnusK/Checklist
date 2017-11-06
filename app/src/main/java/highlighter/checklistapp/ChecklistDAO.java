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

    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        accessChecklistDB = new ChecklistDB(context);
    }
}

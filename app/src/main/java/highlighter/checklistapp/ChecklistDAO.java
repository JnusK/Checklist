package highlighter.checklistapp;

import android.content.Context;
import android.util.Log;

import highlighter.checklistapp.controller.AccessChecklistDB;
import highlighter.checklistapp.entity.ChecklistDB;

/**
 * Created by Khorly on 2/11/17.
 */

public class ChecklistDAO {
    public static ChecklistDB checklistDB;

    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        checklistDB = new ChecklistDB(context);
    }
}

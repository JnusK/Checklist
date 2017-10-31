package highlighter.checklistapp.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONStringer;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jnus on 23/10/2017.
 */

public class AccessChecklistDB {


    public static int[] findChecklistByFrequency(int frequency_id){
        /**
         * Find the checklists that are of the frequency selected and return their id
         */
        int[] checklist_id_array = new int[50];

        return checklist_id_array;
    }

    public static String[] findChecklist(int checklist_id){
        /**
         * Find the checklist and return in an array
         */
        String[] checklist = new String[20];

        return checklist;
    }

    public static void editChecklist(String part){
        /**
         * Edit checklist
         */
    }
}

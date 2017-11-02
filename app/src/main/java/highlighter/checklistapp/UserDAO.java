package highlighter.checklistapp;

import android.content.Context;
import android.util.Log;

import highlighter.checklistapp.controller.AccessUserDB;

/**
 * Created by Khorly on 2/11/17.
 */

public class UserDAO {
    public static AccessUserDB accessUserDB;

    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        accessUserDB = new AccessUserDB(context); // This will be your DB Handler Class
        accessUserDB.addUserToDB(12345, "admin", 1, 0);
        accessUserDB.addUserToDB(234, "tech", 0, 0);
    }
}

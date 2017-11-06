package highlighter.checklistapp;

import android.content.Context;
import android.util.Log;

import highlighter.checklistapp.controller.AccessUserDB;
import highlighter.checklistapp.entity.UserDB;

/**
 * Created by Khorly on 2/11/17.
 */

public class UserDAO {
    public static UserDB userDB;

    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        userDB = new UserDB(context); // This will be your DB Handler Class
        userDB.addUserToDB(12345, "admin", 1, 0);
        userDB.addUserToDB(234, "tech", 0, 0);
        userDB.addUserToDB(12, "admin", 1, 0);
    }
}

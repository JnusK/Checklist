package highlighter.checklistapp.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONStringer;

/**
 * Created by Jnus on 23/10/2017.
 */

public class AccessUserDB {
    public static int findUser(String user_id){
        /**
         * Used to check if user exist in DB.
         * Return 1 when exist and 0 when does not exist
         */

        return 1;
    }

    public static int checkPW(String pw){
        /**
         * Check if pw provided by user tallies with DB
         * Return 1 when correct and 0 when incorrect
         */
        return 1;

    }

    public static void addUser(String user_id, String pw){
        /**
         * Add account to DB, should check prior if user exist
         */
    }

    public static int checkUserType(String user_id){
        /**
         * Check if user is admin or user
         * 1 is admin, 0 is user
         */
        return 0;
    }

    public static int changePW(String user_id, String old_pw, String new_pw){
        /**
         * Allow user to change pw
         * 1 is succesful, 0 is unsuccessful
         */
        return 1;
    }
}

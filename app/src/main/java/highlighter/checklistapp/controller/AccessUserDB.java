package highlighter.checklistapp.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONStringer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import highlighter.checklistapp.entity.User;
import highlighter.checklistapp.entity.UserDB;

/**
 * Created by Jnus on 23/10/2017.
 */

public class AccessUserDB{

    private UserDB userDB;

    public AccessUserDB(){}

    public AccessUserDB(UserDB userDB){
        this.userDB = userDB;
    }

    //For debugging to check if the database exist
    public void checkDB(){
        userDB.checkDB();
    }

    public int authenticateUser(int id, String password){
        return userDB.authenticateUser(id, password);
    }

    public int checkUserType(int user_id){
        return userDB.checkUserType(user_id);
    }

    public int addUserToDB(int id, String password, int type, int date_added){
        return userDB.addUserToDB(id, password, type, date_added);
    }

    public int changePassword(int user_id, String new_pw){
        return userDB.changePassword(user_id, new_pw);
    }

    public User getUser(int user_id){
        return userDB.getUser(user_id);
    }

    public int updateUser(User user){
        return userDB.updateUserDB(user);
    }

    public void deleteUser(int user_id){
        userDB.deleteUser(user_id);
    }
}

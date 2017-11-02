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
import java.util.List;

import highlighter.checklistapp.entity.User;
import highlighter.checklistapp.entity.UserDB;

/**
 * Created by Jnus on 23/10/2017.
 */

public class AccessUserDB extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 5;
    // Database Name
    private static final String DATABASE_NAME = "UserDB";
    // Contacts table name
    private static final String TABLE_USERS = "Users";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DATE_ADDED = "date_added";

    ArrayList<User> userArray = new ArrayList<User>();
    public UserDB userDB = new UserDB(userArray);

    public AccessUserDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void checkDB(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.close();
            Log.w("userDB", "exist");
        }catch (Exception e) {

            Log.i(getClass().getName(), e.toString());
            System.out.print(e);
            System.out.println("Not working");
        }
    }

    public int findUser(int user_id){
        /**
         * Used to check if user exist in DB.
         * Return the index when exist and -1 when does not exist
         */
        ArrayList<User> users = userDB.getUsers();
        for (int x=0; x<users.size(); x++) {
            if (users.get(x).getID()==(user_id)) {
                return x;
            }
        }
        return -1;
    }

    public int checkPassword(String password, int index){
        /**
         * Check if pw provided by user tallies with DB
         * Return 1 when correct and 0 when incorrect
         */
        ArrayList<User> users = userDB.getUsers();
        if(users.get(index).getPassword().equals(password)){
            return 1;
        }
        return 0;

    }

    public int authenticateUser(int id, String password){
        /**
         * Check user and password against user database
         * return 1 if successful
         * return 0 if user does not exist
         * return -1 if password is incorrect
         */
        int index;
        index = this.findUser(id);
        if(index == -1){
            return 0;
        }
        //password is wrong
        if(this.checkPassword(password, index) == 0){
            return -1;
        }
        //successful authentication
        else return 1;
    }


    public int checkUserType(int user_id){
        /**
         * Check if user is admin or user
         * 1 is admin, 0 is user, -1 is invalid
         */
        ArrayList<User> users = userDB.getUsers();
        for (int x=0; x<users.size(); x++) {
            if (users.get(x).getID()==(user_id)) {
                return users.get(x).getType();
            }
        }
        return -1;
    }


    public int addUserToDB(int id, String password, int type, int date_added){
        /**
         * Add account to DB
         * return 1 if successful and 0 if user exist
         */
        Log.i("userDB", "trying to add");
        if(this.findUser(id) == 1){
            return 0;
        }

        User user = new User(id, password, type, date_added);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getID()); // user_id
        values.put(KEY_PASSWORD, user.getPassword()); // Password
        values.put(KEY_TYPE, user.getType());
        values.put(KEY_DATE_ADDED, user.getDateAdded());

        // Inserting Row into SQLite
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
        //Add into object
        this.userDB.addUser(user);
        return 1;
    }

    public ArrayList<User> changePW(int user_id, String new_pw){
        /**
         * Allow user to change pw
         * 1 is successful, 0 is unsuccessful
         */
        ArrayList<User> users = userDB.getUsers();
        for (int x=0; x<users.size(); x++) {
            if (users.get(x).getID()==(user_id)) {
                users.get(x).setPassword(new_pw);
                return users;
            }
        }
        return users;
    }

    // Getting All Users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(0, "", 0, 0);
                user.setID(cursor.getInt(0));
                user.setPassword(cursor.getString(1));
                user.setType(cursor.getInt(2));
                user.setDateAdded(cursor.getInt(3));
        // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
    // return user list
        return userList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASSWORD + " TEXT,"
        + KEY_TYPE + " INTEGER," + KEY_DATE_ADDED + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.w("userDB", "created");
        System.out.println("onCreate running");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Creating tables again
        onCreate(db);
    }
}

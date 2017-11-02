package highlighter.checklistapp.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import highlighter.checklistapp.controller.AccessUserDB;

/**
 * Created by Jnus on 23/10/2017.
 */

public class UserDB extends SQLiteOpenHelper {

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

   // ArrayList<User> userArray = new ArrayList<User>();

    public UserDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //userArray = getAllUsers();

    }

    //For debugging to check if the database exist
    public void checkDB(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.close();
            Log.w("userDB", "DB exist");
        }catch (Exception e) {

            Log.i(getClass().getName(), e.toString());
            System.out.print(e);
            Log.w("userDB","DB does not exist");
        }
    }



    public int findUser(int user_id){
        /**
         * Used to check if user exist in DB.
         * Return the index when exist and -1 when does not exist
         */
        ArrayList<User> userArray = getAllUsers();
        for (int x=0; x<userArray.size(); x++) {
            if (userArray.get(x).getID()==(user_id)) {
                Log.w("userDB", "user exist");
                return x;
            }
        }
        Log.w("userDB", "user does not exist");
        return -1;
    }

    public int checkPassword(String password, int index){
        /**
         * Check if pw provided by user tallies with DB
         * Return 1 when correct and 0 when incorrect
         */
        ArrayList<User> userArray = getAllUsers();
        if(userArray.get(index).getPassword().equals(password)){
            Log.w("userDB", "pw correct");
            return 1;
        }
        Log.w("userDB", "pw not correct");
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
        else {
            Log.w("userDB", "user authenticated");
            return 1;
        }
    }


    public int checkUserType(int user_id){
        /**
         * Check if user is admin or user
         * 1 is admin, 0 is user
         */
        User user = getUser(user_id);
        Log.w("userDB", "Type returned");
        return user.getType();
    }


    public int addUserToDB(int id, String password, int type, int date_added){
        /**
         * Add account to DB
         * return 1 if successful and 0 if user exist
         */
        if(this.findUser(id) != -1){
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
        //userArray.add(user);
        Log.w("userDB", "User added");
        return 1;
    }

    public int changePassword(int user_id, String new_pw){
        /**
         * Allow user to change pw
         * 1 is successful, 0 is unsuccessful
         */
        ArrayList<User> userArray = getAllUsers();
        for (int x=0; x<userArray.size(); x++) {
            if (userArray.get(x).getID()==(user_id)) {
                //Update object
                userArray.get(x).setPassword(new_pw);
                //Update DB
                int reply = updateUserDB(userArray.get(x));
                Log.w("userDB", "Password changed");
                return 1;
            }
        }
        return 0;
    }

    public int updateUserDB(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getID());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_TYPE, user.getType());
        values.put(KEY_DATE_ADDED, user.getDateAdded());
        Log.w("userDB", "Updating user");
        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getID())});
    }

    // Getting All Users
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
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
        Log.w("userDB", "userArray populated");
        return userList;
    }

    // Getting one user
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_PASSWORD, KEY_TYPE, KEY_DATE_ADDED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User((cursor.getInt(0)),
                cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
        // return user
        return user;
    }

    // Deleting a user
    public void deleteUser(int user_id) {
        int index = findUser(user_id);
        //remove from obj
        //userArray.remove(index);
        //remove from DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user_id) });
        db.close();
        Log.w("userDB", "User Deleted");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASSWORD + " TEXT,"
        + KEY_TYPE + " INTEGER," + KEY_DATE_ADDED + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.w("userDB", "DB created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        Log.w("userDB", "DB updated");
        // Creating tables again
        onCreate(db);
    }
}

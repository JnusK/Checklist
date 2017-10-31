package highlighter.checklistapp.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Joseph on 10/24/2017.
 */

public class UserDB {

    ArrayList<User> userDB;

    public void addUser(User user){
        /**
         * Add a user to the ArrayList<User>
         */
        this.userDB.add(user);
    }

    public ArrayList<User> getUsers(){
        return userDB;
    }

    public UserDB(ArrayList<User> userDB, String user, String password){
        this.userDB = userDB;
    }


}

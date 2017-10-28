package highlighter.checklistapp.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    String user;
    String password;


    public int authenticate(String id, String password){
        SharedPreferences db = getSharedPreferences("users", MODE_PRIVATE);
        //key pair is id:{pw, user_type}
        try {
            Set<String> user = user_db.getStringSet(key:id);
        }
        catch{
            return 0;
        }

        return 1;
    }

    public int addUser(String user, String password){
       return 1;
    }

    public ArrayList<User> readDB(){
        ArrayList<User> arr = new ArrayList<>();

        SQLiteDatabase db = openDatabase("App",null);
        Cursor resultSet = db.rawQuery("Select * from UserDB",null);
        resultSet.moveToFirst();
        while (resultSet.isClosed() == false){
            User user = new User();

        }
    }

}

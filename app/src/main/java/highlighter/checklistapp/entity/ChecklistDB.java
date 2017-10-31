package highlighter.checklistapp.entity;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joseph on 10/24/2017.
 */

public class ChecklistDB {


    ArrayList<Checklist> checklistDB;
    int frequency;

    public ArrayList<Checklist> loadCheckLists(int frequency){


        return null;
    }

    public int update(int servicable, int checklist_id, int checklist_item_no){

        return 1;
    }


}

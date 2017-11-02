package highlighter.checklistapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistItem;

/**
 * Created by Jnus on 23/10/2017.
 */

public class AccessChecklistDB extends SQLiteOpenHelper {


    //Database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ChecklistDB";
    //table name
    private static final String TABLE_CHECKLISTS = "Checklists";
    private static final String TABLE_CHECKLISTITEMS = "ChecklistItems";
    //Table Columns names
    private static final String CHECKLIST_NAME = "name";
    private static final String CHECKLIST_ID = "id";
    private static final String CHECKLIST_FREQUENCY = "frequency";
    private static final String CHECKLIST_DATE_ADDED = "date_added";

    private static final String ITEM_CHECKLIST_ID = "checklist_id"; //a column to hold the id of the checklist it belongs to
    private static final String ITEM_ID = "id";
    private static final String ITEM_DESC = "desc";
    private static final String ITEM_SERV = "serv";

    ArrayList<ChecklistItem> checklistItemArrayList = new ArrayList<ChecklistItem>();


    public AccessChecklistDB(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



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




    public int addCheckListItemsToDB(int checklist_id, int id, String description, int serviceability){


        //Adds a checklistitem to the DB. 1 if successfull, 0 if not


        ChecklistItem cli = new ChecklistItem(checklist_id, id, description, serviceability);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_CHECKLIST_ID , checklist_id);
        values.put(ITEM_ID , cli.getId());
        values.put(ITEM_DESC , cli.getDescription());
        values.put(ITEM_SERV , cli.getServiceability());

        db.insert(TABLE_CHECKLISTITEMS , null , values);
        db.close();

        return 1;

    }



    public int addChecklistToDB(String name, int id, int date_added, int frequency, ArrayList<ChecklistItem> checklistItems){


        //Adds a checklist to the DB. 1 if successfull, 0 if not

        Checklist cl = new Checklist(name,id,date_added,frequency,checklistItems);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHECKLIST_NAME, cl.getName());
        values.put(CHECKLIST_ID, cl.getID());
        values.put(CHECKLIST_DATE_ADDED,cl.getDateAdded());
        values.put(CHECKLIST_FREQUENCY, cl.getFrequency());

        db.insert(TABLE_CHECKLISTS ,null, values);
        db.close();





        return 1;
    }

    public List<ChecklistItem> selectCheckListItems(int checklist_id){

        //checklist_id is the id of the checklist
        //gets all the checklistitems based on the checklist_id provided into a list

        List<ChecklistItem> checkListItem = new ArrayList<ChecklistItem>();


        String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTITEMS + " WHERE "
                +ITEM_CHECKLIST_ID +   " = " + checklist_id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){

            do {
                ChecklistItem cli = new ChecklistItem(0, 0, "", 0);
                cli.setChecklistID(cursor.getInt(0));
                cli.setID(cursor.getInt(1)); //because position 0 is the checklist_id
                cli.setDescription(cursor.getString(2));
                cli.setServiceability(cursor.getInt(3));
                checkListItem.add(cli);
            }
            while(cursor.moveToNext());

        }

        return checkListItem;





    }



    public List<Checklist> getAllChecklist(){

        List<Checklist> checkList = new ArrayList<Checklist>();

        String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery , null);

        if(cursor.moveToFirst()){

            do {
                Checklist check = new Checklist("", 0, 0, 0, null);
                check.setID(cursor.getInt(0));
                check.setName(cursor.getString(1));
                check.setFrequency(cursor.getInt(2));
                check.setDateAdded(cursor.getInt(3));
                //Add the checklist to the list
                checkList.add(check);


            }
            while(cursor.moveToNext());



        }


        return checkList;



    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_CHECKLIST_TABLE = "CREATE TABLE " + TABLE_CHECKLISTS + "("
                + CHECKLIST_ID + " INTEGER PRIMARY KEY," + CHECKLIST_NAME + " TEXT,"
                + CHECKLIST_FREQUENCY + " INTEGER," + CHECKLIST_DATE_ADDED + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_CHECKLIST_TABLE);

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_CHECKLISTITEMS + "("
                + ITEM_CHECKLIST_ID + " INTEGER PRIMARY KEY,"
                + ITEM_ID + " INTEGER," + ITEM_DESC + " TEXT,"
                + ITEM_SERV + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTITEMS);

        onCreate(sqLiteDatabase);




    }
}
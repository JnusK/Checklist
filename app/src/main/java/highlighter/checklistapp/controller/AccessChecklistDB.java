package highlighter.checklistapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistItem;

/**
 * Created by Jnus on 23/10/2017.
 */


//not to be used anymore


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
    private static final String ITEM_DESC = "description";
    private static final String ITEM_SERV = "serviceability";

    public static final int SEARCH_NAME  = 1;
    public static final int SEARCH_ID  = 2;
    public static final int SEARCH_FREQUENCY  = 3;
    public static final int SEARCH_DATE_ADDED  = 4;



    ArrayList<ChecklistItem> checklistItemArrayList = new ArrayList<ChecklistItem>();


    public void checkDB(){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.close();
            Log.w("accessDB", "DB exist");
        }catch (Exception e) {

            Log.i(getClass().getName(), e.toString());
            System.out.print(e);
            Log.w("accessDB","DB does not exist");
        }
    }


    public AccessChecklistDB(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    public ArrayList<Checklist> findChecklist(String value, int choice){


                ArrayList<Checklist> checklist_array = new ArrayList<Checklist>();
                String selectQuery = "";

                switch (choice) {

                    case SEARCH_NAME:

                        selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                                +CHECKLIST_NAME +   "=" +  "'" + value + "'";
                        break;


                    case SEARCH_ID:
                        selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                                +CHECKLIST_ID+   "=" +  "'" + value + "'";
                        break;

                    case SEARCH_FREQUENCY:
                        selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                                +CHECKLIST_FREQUENCY+   "=" +  "'" + value + "'";
                        break;

                    case SEARCH_DATE_ADDED:
                        selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                                +CHECKLIST_DATE_ADDED+   "=" +  "'" + value + "'";
                        break;

                }

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery , null);
                if(cursor.moveToFirst()){

                    do {

                        Checklist check = new Checklist("", 0, 0, "", null);
                        check.setID(cursor.getInt(0));
                        check.setName(cursor.getString(1));
                        check.setFrequency(cursor.getString(2));
                        check.setDateAdded(cursor.getInt(3));
                        //Add the checklist to the list

                        check.setchecklistItems(selectCheckListItems(check.getID()));
                        checklist_array.add(check);


                    }
                    while(cursor.moveToNext());

                }

                return checklist_array;

        }

    public  ArrayList<Checklist> findChecklistByFrequency(String frequency_id){
        /**
         * Find the checklists that are of the frequency selected and return their id
         */
        ArrayList<Checklist> checklist_array = new ArrayList<Checklist>();

        String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                +CHECKLIST_FREQUENCY +   "=" +  "'" + frequency_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){

            do {

                Checklist check = new Checklist("", 0, 0, "", null);
                check.setID(cursor.getInt(0));
                check.setName(cursor.getString(1));
                check.setFrequency(cursor.getString(2));
                check.setDateAdded(cursor.getInt(3));
                //Add the checklist to the list

                check.setchecklistItems(selectCheckListItems(check.getID()));
                checklist_array.add(check);



            }
            while(cursor.moveToNext());

        }

        return checklist_array;



    }

    public ArrayList<Checklist> findChecklist(int checklist_id){
        /**
         * Find the checklist and return in an array
         */
        ArrayList<Checklist> checklist_array = new ArrayList<Checklist>();

        String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                +CHECKLIST_ID +   "=" +  "'" + checklist_id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery , null);
        if(cursor.moveToFirst()){

            do {



                Checklist check = new Checklist("", 0, 0, "", null);
                check.setID(cursor.getInt(0));
                check.setName(cursor.getString(1));
                check.setFrequency(cursor.getString(2));
                check.setDateAdded(cursor.getInt(3));
                //Add the checklist to the list

                check.setchecklistItems(selectCheckListItems(check.getID()));
                checklist_array.add(check);



            }
            while(cursor.moveToNext());

        }




        return checklist_array;
    }

    public static void editChecklist(String part){
        /**
         * Edit checklist
         */


    }




    public int addCheckListItemsToDB(int checklist_id, int id, String description, int serviceability){
        /**
         * Adds a checklistitem to the DB.
         * 1 if successfull, 0 if not
         */

        ChecklistItem cli = new ChecklistItem(checklist_id, id, description, serviceability);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_CHECKLIST_ID , checklist_id);
        values.put(ITEM_ID , cli.getChecklistID());
        values.put(ITEM_DESC , cli.getDescription());
        values.put(ITEM_SERV , cli.getServiceability());

        db.insert(TABLE_CHECKLISTITEMS , null , values);
        db.close();
        Log.i("accessDB","addChecklist items to db");
        //TODO: Add created item to the corresponding checklist


        //Checklist items are now added into the respective checklist in the checklistDB



        return 1;

    }



    public int addChecklistToDB(String name, int id, int date_added, String frequency){


        //Adds a checklist to the DB. 1 if successful, 0 if not

        Checklist cl = new Checklist(name,id,date_added,frequency,null);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHECKLIST_NAME, cl.getName());
        values.put(CHECKLIST_ID, cl.getID());
        values.put(CHECKLIST_DATE_ADDED,cl.getDateAdded());
        values.put(CHECKLIST_FREQUENCY, cl.getFrequency());

        db.insert(TABLE_CHECKLISTS ,null, values);
        db.close();
        Log.i("accessDB","addChecklist to db");


        //TODO: Add checklist to checklistDB

        //Add checklist to checklistDB


        return 1;
    }


    public void clearTables(){

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTITEMS);



    }




    public ArrayList<ChecklistItem> selectCheckListItems(int checklist_id){

        //checklist_id is the id of the checklist
        //gets all the checklistitems based on the checklist_id provided into a list

        ArrayList<ChecklistItem> checkListItem = new ArrayList<ChecklistItem>();


        String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTITEMS + " WHERE "
                +ITEM_ID +   "=" +  "'" + checklist_id + "'";
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


    public ArrayList<Checklist> getAllChecklist(){

        ArrayList<Checklist> checkList = new ArrayList<Checklist>();

        String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery , null);

        if(cursor.moveToFirst()){

            do {
                Checklist check = new Checklist("", 0, 0, "", null);
                check.setID(cursor.getInt(0));
                check.setName(cursor.getString(1));
                check.setFrequency(cursor.getString(2));
                check.setDateAdded(cursor.getInt(3));
                //Add the checklist to the list

                check.setchecklistItems(selectCheckListItems(check.getID()));




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
                + CHECKLIST_FREQUENCY + " TEXT," + CHECKLIST_DATE_ADDED + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_CHECKLIST_TABLE);

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_CHECKLISTITEMS + "("
                + ITEM_CHECKLIST_ID + " INTEGER,"
                + ITEM_ID + " INTEGER," + ITEM_DESC + " TEXT,"
                + ITEM_SERV + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
        Log.w("checklistDB", "DB created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTITEMS);

        onCreate(sqLiteDatabase);




    }
}

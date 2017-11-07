package highlighter.checklistapp.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import highlighter.checklistapp.customClass.Subscriber;

/**
 * Created by Joseph on 10/24/2017.
 */





public class ChecklistDB extends SQLiteOpenHelper{





    //Database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ChecklistDB";
    //table name
    private static final String TABLE_CHECKLISTS = "Checklists";
    private static final String TEMPLATE_TABLE_CHECKLISTS = "TemplateChecklists";

    private static final String TABLE_CHECKLISTITEMS = "ChecklistItems";
    private static final String TEMPLATE_TABLE_CHECKLISTITEMS = "TemplateChecklistItems";

    //Table Columns names
    private static final String CHECKLIST_NAME = "name";
    private static final String CHECKLIST_ID = "id";
    private static final String CHECKLIST_FREQUENCY = "frequency";
    private static final String CHECKLIST_DATE_ADDED = "date_added";
    private static final String CHECKLIST_LAST_EDIT_DATE = "last_edit_date";
    private static final String CHECKLIST_LAST_EDIT_USER_ID = "last_edit_user_id";
    private static final String CHECKLIST_IS_ARCHIVE = "IS_ARCHIVE";



    private static final String ITEM_CHECKLIST_ID = "checklist_id";
    private static final String ITEM_ID = "id"; //this must match with checklist_id
    private static final String ITEM_DESC = "description";
    private static final String ITEM_SERV = "serviceability";

    public static final int SEARCH_NAME  = 1;
    public static final int SEARCH_ID  = 2;
    public static final int SEARCH_FREQUENCY  = 3;
    public static final int SEARCH_DATE_ADDED  = 4;
    public static final int SEARCH_LAST_EDIT_DATE  = 5;
    public static final int SEARCH_LAST_EDIT_USER_ID  = 6;
    public static final int SEARCH_IS_ARCHIVE  = 7;




    public static final int ARCHIVE = 1;
    public static final int TEMPLATE = 2;

    ArrayList<Checklist> checklistDB;
    String frequency;



    /*public ChecklistDB(ArrayList<Checklist> checklistDB, String frequency){
        this.checklistDB = checklistDB;
        this.frequency = frequency;
    }*/

    ArrayList<Subscriber> subscriber_list = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber){
        subscriber_list.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscriber_list.remove(subscriber);
    }

    public void notifySubscribers(){
        for (int i = 0; i < subscriber_list.size(); i+=1){
            subscriber_list.get(i).update();
        }
    }

    public ChecklistDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

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


    public int updateChecklist(int serviceability, int checklist_ID, int checklist_item_no){
        String selectQuery = "UPDATE " + TABLE_CHECKLISTITEMS + " SET "
                +ITEM_SERV +   "=" +  "'" + serviceability + "'" + " WHERE " + ITEM_CHECKLIST_ID + "=" + checklist_item_no;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);
        return 1;
    }


    public int deleteChecklist(int checklist_id){
        String selectQuery = "DELETE * FROM " + TABLE_CHECKLISTS + " WHERE "
                +CHECKLIST_ID +   "=" +  "'" + checklist_id + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);

        selectQuery =  "DELETE * FROM " + TABLE_CHECKLISTITEMS + " WHERE "
                +ITEM_ID +   "=" +  "'" + checklist_id + "'";
        db.execSQL(selectQuery);

        return 1;
    }



    public int addCheckListItemsToDB(int id, String description, int serviceability, int choice){
        ArrayList<ChecklistItem> checklistArraylist = getAllCheckListItemFromArchive();

        int unixTime = (int) (System.currentTimeMillis() / 1000L);

        if(checklistArraylist.size() ==0){



            addCheckListItemsToDB(0,id,description ,serviceability,choice);
            return 1;

        }


        else if (checklistArraylist.size() != 0) {
            int[] array = new int[checklistArraylist.size()];

            for (int i = 0; i < checklistArraylist.size(); i++) {

                array[i] = checklistArraylist.get(i).getChecklistID();


            }


            array = InsertionSort(array, checklistArraylist.size());
            addCheckListItemsToDB(array[checklistArraylist.size() - 1] + 1,id,description ,serviceability,choice);
            return 1;
        }

return 0;
    }


    public int addCheckListItemsToDB(int checklist_id, int id, String description, int serviceability, int choice){
        /**
         * Adds a checklistitem to the DB.
         * 1 if successfull, 0 if not
         */


        //choice is used to tell which table is the checklist going to be written into
        //1 for writing the checklist into archive table
        //2 for writing the checklist into the template table

        ChecklistItem cli = new ChecklistItem(checklist_id, id, description, serviceability);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_CHECKLIST_ID , checklist_id);
        values.put(ITEM_ID , cli.getId());
        values.put(ITEM_DESC , cli.getDescription());
        values.put(ITEM_SERV , cli.getServiceability());

        if(choice == ARCHIVE) {
            db.insert(TABLE_CHECKLISTITEMS, null, values);
        }

        else if (choice == TEMPLATE){

            db.insert(TEMPLATE_TABLE_CHECKLISTITEMS, null, values);
        }





        db.close();
        Log.i("accessDB","addChecklist items to db");
        //TODO: Add created item to the corresponding checklist






        return 1;

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
        values.put(ITEM_ID , cli.getId());
        values.put(ITEM_DESC , cli.getDescription());
        values.put(ITEM_SERV , cli.getServiceability());

        db.insert(TABLE_CHECKLISTITEMS , null , values);
        db.close();
        Log.i("accessDB","addChecklist items to db");
        //TODO: Add created item to the corresponding checklist






        return 1;

    }


    public static int[] InsertionSort(int[] slot,int n){
        int temp;
        for (int i = 1;i<n; i++){
            for (int j = i;j>0;j--){

                if (slot[j]<slot[j-1]){
                    temp = slot[j];
                    slot[j] = slot[j-1];
                    slot[j-1] = temp;
                }

            }
        }

        return slot;
    }

    public int addChecklistToDB(String name, String frequency, int choice){

        ArrayList<Checklist> checklistArraylist = getAllChecklist();

        int unixTime = (int) (System.currentTimeMillis() / 1000L);

        if(checklistArraylist.size() ==0){



            addChecklistToDB(name,0,unixTime,frequency ,choice);
            return 1;

        }


        else if (checklistArraylist.size() != 0) {
            int[] array = new int[checklistArraylist.size()];

            for (int i = 0; i < checklistArraylist.size(); i++) {

                array[i] = checklistArraylist.get(i).getID();


            }


            array = InsertionSort(array, checklistArraylist.size());
            addChecklistToDB(name, array[checklistArraylist.size() - 1] + 1, unixTime, frequency, choice);
            return 1;
        }



        return 0;
    }





    public int addChecklistToDB(String name, int id, int date_added, String frequency, int choice){


        //choice is used to tell which table is the checklist going to be written into
        //1 for writing the checklist into archive table
        //2 for writing the checklist into the template table


        //Adds a checklist to the DB. 1 if successful, 0 if not

        Checklist cl = new Checklist(name,id,date_added,frequency,null);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHECKLIST_NAME, cl.getName());
        values.put(CHECKLIST_ID, cl.getID());
        values.put(CHECKLIST_DATE_ADDED,cl.getDateAdded());
        values.put(CHECKLIST_FREQUENCY, cl.getFrequency());

        if(choice == ARCHIVE) {
            db.insert(TABLE_CHECKLISTS, null, values);
        }
        else if (choice == TEMPLATE){
            db.insert(TEMPLATE_TABLE_CHECKLISTS, null, values);

        }
        db.close();
        Log.i("accessDB","addChecklist to db");

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

        return 1;
    }

    public ArrayList<Checklist> findChecklistFromTemplate(String value, int choice){


        ArrayList<Checklist> checklist_array = new ArrayList<Checklist>();
        String selectQuery = "";

        switch (choice) {

            case SEARCH_NAME:

                selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTS + " WHERE "
                        +CHECKLIST_NAME +   "=" +  "'" + value + "'";
                break;


            case SEARCH_ID:
                selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTS + " WHERE "
                        +CHECKLIST_ID+   "=" +  "'" + value + "'";
                break;

            case SEARCH_FREQUENCY:
                selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTS + " WHERE "
                        +CHECKLIST_FREQUENCY+   "=" +  "'" + value + "'";
                break;

            case SEARCH_DATE_ADDED:
                selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTS + " WHERE "
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




    public ArrayList<Checklist> findChecklist(String value, int choice){
//        public static final int SEARCH_NAME  = 1;
//        public static final int SEARCH_ID  = 2;
//        public static final int SEARCH_FREQUENCY  = 3;
//        public static final int SEARCH_DATE_ADDED  = 4;

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

            case SEARCH_LAST_EDIT_DATE:

                selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                        +CHECKLIST_LAST_EDIT_DATE+   "=" +  "'" + value + "'";
                break;

            case SEARCH_LAST_EDIT_USER_ID:

                selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                        +CHECKLIST_LAST_EDIT_USER_ID+   "=" +  "'" + value + "'";
                break;

            case SEARCH_IS_ARCHIVE:

                selectQuery = "SELECT * FROM " + TABLE_CHECKLISTS + " WHERE "
                        +CHECKLIST_IS_ARCHIVE+   "=" +  "'" + value + "'";
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


    public ArrayList<Checklist> getAllChecklistFromTemplate(){

        ArrayList<Checklist> checkList = new ArrayList<Checklist>();

        String selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTS;
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



    public ArrayList<ChecklistItem> selectCheckListItemsFromTemplate(int checklist_id){

        //checklist_id is the id of the checklist
        //gets all the checklistitems based on the checklist_id provided into a list

        ArrayList<ChecklistItem> checkListItem = new ArrayList<ChecklistItem>();


        String selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTITEMS + " WHERE "
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


    public ArrayList<ChecklistItem> getAllChecklistItemFromTemplate(){
        {
            {

                //checklist_id is the id of the checklist
                //gets all the checklistitems based on the checklist_id provided into a list

                ArrayList<ChecklistItem> checkListItem = new ArrayList<ChecklistItem>();


                String selectQuery = "SELECT * FROM " + TEMPLATE_TABLE_CHECKLISTITEMS;
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
        }
    }



    public ArrayList<ChecklistItem> getAllCheckListItemFromArchive(){
        {

            //checklist_id is the id of the checklist
            //gets all the checklistitems based on the checklist_id provided into a list

            ArrayList<ChecklistItem> checkListItem = new ArrayList<ChecklistItem>();


            String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTITEMS;
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
    }

    public int findCheckListItemID(int checklistitem_id){


        {

            //checklist_id is the id of the checklist
            //gets all the checklistitems based on the checklist_id provided into a list

            ArrayList<ChecklistItem> checkListItem = new ArrayList<ChecklistItem>();


            String selectQuery = "SELECT * FROM " + TABLE_CHECKLISTITEMS + " WHERE "
                    +ITEM_CHECKLIST_ID +   "=" +  "'" + checklistitem_id + "'";
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

            int checklist_id = checkListItem.get(0).getId();
            return checklist_id;




        }








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

    public void clearTables(){

        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTITEMS);



    }



   /* public ArrayList<Checklist> getCheckLists(){
        return checklistDB;
    }
    public void addChecklist(Checklist check){
        checklistDB.add(check);
    }
    public int update(int servicable, int checklist_id, int checklist_item_no){
        return 1;
    }*/


    @Override
    public void onCreate(SQLiteDatabase db) {



        String CREATE_CHECKLIST_TABLE = "CREATE TABLE " + TABLE_CHECKLISTS + "("
                + CHECKLIST_ID + " INTEGER PRIMARY KEY," + CHECKLIST_NAME + " TEXT,"
                + CHECKLIST_FREQUENCY + " TEXT," + CHECKLIST_DATE_ADDED + " INTEGER," + CHECKLIST_LAST_EDIT_DATE + " INTEGER,"
                + CHECKLIST_LAST_EDIT_USER_ID + " INTEGER," + CHECKLIST_IS_ARCHIVE + " INTEGER" + ")";
        db.execSQL(CREATE_CHECKLIST_TABLE);



                 CREATE_CHECKLIST_TABLE = "CREATE TABLE " + TEMPLATE_TABLE_CHECKLISTS + "("
                + CHECKLIST_ID + " INTEGER PRIMARY KEY," + CHECKLIST_NAME + " TEXT,"
                + CHECKLIST_FREQUENCY + " TEXT,"  + CHECKLIST_DATE_ADDED + " INTEGER," + CHECKLIST_LAST_EDIT_DATE + " INTEGER,"
                         + CHECKLIST_LAST_EDIT_USER_ID + " INTEGER," + CHECKLIST_IS_ARCHIVE + " INTEGER" + ")";
        db.execSQL(CREATE_CHECKLIST_TABLE);

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_CHECKLISTITEMS + "("
                + ITEM_CHECKLIST_ID + " INTEGER PRIMARY KEY,"
                + ITEM_ID + " INTEGER," + ITEM_DESC + " TEXT,"
                + ITEM_SERV + " INTEGER" + ")";
        db.execSQL(CREATE_ITEM_TABLE);

        CREATE_ITEM_TABLE = "CREATE TABLE " + TEMPLATE_TABLE_CHECKLISTITEMS+ "("
                + ITEM_CHECKLIST_ID + " INTEGER PRIMARY KEY,"
                + ITEM_ID + " INTEGER," + ITEM_DESC + " TEXT,"
                + ITEM_SERV + " INTEGER" + ")";
        db.execSQL(CREATE_ITEM_TABLE);


        Log.w("checklistDB", "DB created");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHECKLISTITEMS);

        db.execSQL("DROP TABLE IF EXISTS " + TEMPLATE_TABLE_CHECKLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TEMPLATE_TABLE_CHECKLISTITEMS);


        onCreate(db);







    }
}
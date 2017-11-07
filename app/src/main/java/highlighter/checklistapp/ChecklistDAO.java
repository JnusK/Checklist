package highlighter.checklistapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import highlighter.checklistapp.controller.AccessChecklistDB;
import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistDB;
import highlighter.checklistapp.entity.ChecklistItem;

/**
 * Created by Khorly on 2/11/17.
 */

public class ChecklistDAO {
    public static ChecklistDB accessChecklistDB;

    public static void DBHandler(Context context){
        Log.d("DBHandler", "user ");

        accessChecklistDB = new ChecklistDB(context);
        populateChecklist();

    }


    public static void populateChecklist() {
        if (accessChecklistDB.getAllCheckListItemFromArchive().size()==0) {
            accessChecklistDB.addChecklistToDB("Unloading Rack", "Daily", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(0,"Inspect all equipment installed for any signs of leaks, cracks, chipped paint, corrosion and missing parts. Ensure all manifolds are covered with protective caps." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(0,"Inspect the hose for signs of external damage and ensure protective caps are in place." , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Slop Tank", "Daily", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,"Visually check all equipment installed in slop tank site and pits for signs of leaks, damage or missing parts." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,"Operate hand pump to draw samples from tank sump and carry out WPT." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(1,"Manually drain basket strainer." , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Control Building", "Weekly", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Switch on and check all lightings, air-conditioners and emergency lightings are serviceable." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Check that loading pump panel switches are mechanically serviceable by operating on and off." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Check that unloading pump panel switches are mechanically serviceable by operating on and off." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Check fire phone is serviceable by picking up the hand set and getting response from the fire station." , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Visually check the DC and facility panel switches are serviceable. (all the light bulbs should glow)" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Test & check that PA / intercom systems are serviceable. (If applicable)" , 0 , ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(2,"Monitor and ensure all equipment are calibrated by means of record or white board display." , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Weekly item 3", "Weekly", ChecklistDB.ARCHIVE);
            accessChecklistDB.addCheckListItemsToDB(3,"Weekly item 2 description1" , 0 , ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Biweekly item 1", "Biweekly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Biweekly item 2", "Biweekly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Monthly item 1", "Monthly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Monthly item 2", "Monthly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Yearly item 1", "Yearly", ChecklistDB.ARCHIVE);

            accessChecklistDB.addChecklistToDB("Yearly item 2", "Yearly", ChecklistDB.ARCHIVE);
            
            ArrayList<ChecklistItem> items = new ArrayList<ChecklistItem>();
            items = accessChecklistDB.getAllCheckListItemFromArchive();
            Log.w("checklistDB", items.toString());
        }
    }
}

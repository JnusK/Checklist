package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapter;
import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistDB;

public class UserHomepage extends AppCompatActivity {

    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");
    Spinner frequency_spinner = null;
    ListView list = null;
    String selectedFrequency = null;

    ArrayList<Checklist> checklists = new ArrayList<Checklist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //populateChecklist();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        frequency_spinner = (Spinner)findViewById(R.id.user_homepage_spinner);

        populateList("Daily");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, frequency_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequency_spinner.setAdapter(dataAdapter);
        frequency_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFrequency = frequency_spinner.getSelectedItem().toString();
                populateList(selectedFrequency);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String checklistName = checklists.get(position).getName();
                int checklistID = checklists.get(position).getID();
                Intent i = new Intent(UserHomepage.this, UserDetailedChecklistPage.class);
                i.putExtra("checklistName", checklistName);
                i.putExtra("checklistID", checklistID);
                startActivity(i);
            }
        });
    }

    /*
    public void populateChecklist(){

        ChecklistDB db = new ChecklistDB(this);

        db.addChecklistToDB("Daily item 1" , 1 , 1, "Daily");
        db.addCheckListItemsToDB(111,1,"Daily item 1 description" , 0);
        db.addCheckListItemsToDB(122,1,"Daily item 1 description" , 0);

        db.addChecklistToDB("Daily item 2" , 2 , 1, "Daily");
        db.addCheckListItemsToDB(222,2,"Daily item 2 description" , 0);
        db.addCheckListItemsToDB(233,2,"Daily item 2 description" , 0);

        db.addChecklistToDB("Weekly item 2" , 3 , 1, "Weekly");
        db.addCheckListItemsToDB(333,3,"Weekly item 1 description" , 0);
        db.addCheckListItemsToDB(334,3,"Weekly item 1 description" , 0);

        db.addChecklistToDB("Weekly item 3" , 4 , 1, "Weekly");
        db.addCheckListItemsToDB(444,4,"Weekly item 2 description" , 0);
        db.addCheckListItemsToDB(445,4,"Weekly item 2 description" , 0);

        db.addChecklistToDB("Biweekly item 1" , 5 , 1, "Biweekly");
        db.addCheckListItemsToDB(555,5,"Biweekly item 1 description" , 0);
        db.addCheckListItemsToDB(556,5,"Biweekly item 1 description" , 0);

        db.addChecklistToDB("Biweekly item 2" , 6 , 1, "Biweekly");
        db.addCheckListItemsToDB(666,6,"Biweekly item 2 description" , 0);
        db.addCheckListItemsToDB(667,6,"Biweekly item 2 description" , 0);

        db.addChecklistToDB("Monthly item 1" , 7 , 1, "Monthly");
        db.addCheckListItemsToDB(777,7,"Monthly item 1 description" , 0);
        db.addCheckListItemsToDB(778,7,"Monthly item 1 description" , 0);

        db.addChecklistToDB("Monthly item 2" , 8 , 1, "Monthly");
        db.addCheckListItemsToDB(888,8,"Monthly item 2 description" , 0);
        db.addCheckListItemsToDB(889,8,"Monthly item 2 description" , 0);

        db.addChecklistToDB("Yearly item 1" , 9 , 1, "Yearly");
        db.addCheckListItemsToDB(999,9,"Yearly item 1 description" , 0);
        db.addCheckListItemsToDB(990,9,"Yearly item 1 description" , 0);

        db.addChecklistToDB("Yearly item 2" , 10 , 1, "Yearly");
        db.addCheckListItemsToDB(1000,10,"Yearly item 2 description" , 0);
        db.addCheckListItemsToDB(1001,10,"Yearly item 2 description" , 0);
    }
    */

    public void populateList(String chosenFreq) {
        //Get DATA from DB pass to String[]
        ChecklistDB DB = new ChecklistDB(this);
        //Pass data to ArrayAdapter
        //checklists = DB.getAllChecklist(); //put in freq then search
        //DB.addChecklistToDB("Test building",5,1,1);
        checklists = DB.findChecklist(chosenFreq,ChecklistDB.SEARCH_FREQUENCY);
        //In CustomListAdapter
        CustomListAdapter adapter = new CustomListAdapter(this, checklists);
        list = (ListView)findViewById(R.id.user_homepage_list);
        list.setAdapter(adapter);
    }
}
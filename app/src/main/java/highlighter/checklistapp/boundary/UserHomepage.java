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
import highlighter.checklistapp.controller.AccessChecklistDB;
import highlighter.checklistapp.customClass.CustomListAdapter;
import highlighter.checklistapp.entity.Checklist;

public class UserHomepage extends AppCompatActivity {

    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");
    Spinner frequency_spinner = null;
    ListView list = null;
    String selectedFrequency = null;

    ArrayList<Checklist> checklists = new ArrayList<Checklist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
                Intent i = new Intent(UserHomepage.this, UserDetailedChecklistPage.class);
                i.putExtra("checklistName", checklistName);
                startActivity(i);
            }
        });
    }

    public void populateList(String chosenFreq) {
        //Get DATA from DB pass to String[]
        AccessChecklistDB DB = new AccessChecklistDB(this);
        //Pass data to ArrayAdapter
        //checklists = DB.getAllChecklist(); //put in freq then search
        //DB.addChecklistToDB("Test building",5,1,1);
        checklists = DB.findChecklistByFrequency(chosenFreq);
        //In CustomListAdapter
        CustomListAdapter adapter = new CustomListAdapter(this, checklists);
        list = (ListView) findViewById(R.id.user_homepage_list);
        list.setAdapter(adapter);
    }
}
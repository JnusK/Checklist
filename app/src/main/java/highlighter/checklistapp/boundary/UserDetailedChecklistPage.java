package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.controller.AccessChecklistDB;
import highlighter.checklistapp.customClass.CustomListAdapter2;
import highlighter.checklistapp.entity.Checklist;

public class UserDetailedChecklistPage extends UserHomepage{

    TextView textViewChecklistName;
    RadioButton rbServiceable, rbUnserviceable;
    String ChecklistName;
    ListView list;
    ArrayList<Checklist> checklists = new ArrayList<Checklist>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        rbServiceable = (RadioButton)findViewById(R.id.rbServiceable);
        rbUnserviceable = (RadioButton)findViewById(R.id.rbUnserviceable);
        textViewChecklistName = (TextView)findViewById(R.id.textViewChecklistName);

        getChecklistName();

        //fill list based on checklist name
        populateList(ChecklistName);

        /*
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //For testing purposes
                Toast.makeText(getApplicationContext(), "End", Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    private void getChecklistName(){
        Intent intent = getIntent();
        ChecklistName = intent.getStringExtra("checklistName");
        textViewChecklistName.setText(ChecklistName);
    }

    public void populateList(String checklistName) {
        //Get DATA from DB pass to String[]
        AccessChecklistDB DB = new AccessChecklistDB(this);
        //Pass data to ArrayAdapter
        //checklists = DB.getAllChecklist(); //put in freq then search
        //DB.addChecklistToDB("Test building",5,1,1);
        //checklists = DB.findChecklist(chosenFreq);
        //checklists = DB.findChecklist(checklistName);
        //In CustomListAdapter
        CustomListAdapter2 adapter = new CustomListAdapter2(this, checklists);
        list = (ListView) findViewById(R.id.user_detailedchecklist_list);
        list.setAdapter(adapter);
    }
}

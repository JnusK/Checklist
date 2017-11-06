package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapter2;
import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistDB;
import highlighter.checklistapp.entity.ChecklistItem;

public class UserDetailedChecklistPage extends UserHomepage{

    TextView textViewChecklistName = null;
    RadioButton rbServiceable = null, rbUnserviceable = null;
    ListView list = null;
    ArrayList<Checklist> checklists = new ArrayList<Checklist>();
    ArrayList<ChecklistItem> checklistsItem = new ArrayList<ChecklistItem>();
    String ChecklistName = null;
    int ChecklistID;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        rbServiceable = (RadioButton)findViewById(R.id.rbServiceable);
        rbUnserviceable = (RadioButton)findViewById(R.id.rbUnserviceable);

        textViewChecklistName = (TextView)findViewById(R.id.textViewChecklistName);
        getChecklistDetails();
        //fill list based on checklist name
        populateList();

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

    private void getChecklistDetails(){
        Intent intent = getIntent();
        ChecklistName = intent.getStringExtra("checklistName");
        ChecklistID = intent.getIntExtra("checklistID", 0);
        textViewChecklistName.setText(ChecklistName);
    }

    public void populateList() {
        //Get DATA from DB pass to String[]
        ChecklistDB DB = new ChecklistDB(this);
        //Pass data to ArrayAdapter
        //checklists = DB.getAllChecklist(); //put in freq then search
        //DB.addChecklistToDB("Test building",5,1,1);
        //checklists = DB.findChecklist(chosenFreq);
        checklistsItem = DB.selectCheckListItems(ChecklistID);
        //In CustomListAdapter
        CustomListAdapter2 adapter = new CustomListAdapter2(this, checklistsItem);
        list = (ListView) findViewById(R.id.user_detailedchecklist_list);
        list.setAdapter(adapter);
    }
}

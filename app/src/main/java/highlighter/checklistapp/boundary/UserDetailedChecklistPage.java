package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapter2;
import highlighter.checklistapp.entity.ChecklistDB;
import highlighter.checklistapp.entity.ChecklistItem;

public class UserDetailedChecklistPage extends UserHomepage{

    TextView textViewChecklistName = null;
    RadioButton rbServiceable = null, rbUnserviceable = null;
    String ChecklistName = null;
    int ChecklistID;
    ListView list;
    ArrayList<ChecklistItem> checklists = new ArrayList<ChecklistItem>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        rbServiceable = (RadioButton)findViewById(R.id.rbServiceable);
        rbUnserviceable = (RadioButton)findViewById(R.id.rbUnserviceable);

        textViewChecklistName = (TextView)findViewById(R.id.textViewChecklistName);
        getChecklistDetails();
        textViewChecklistName.setText(ChecklistName);

        //fill list based on checklist name
        populateList();
    }

    private void getChecklistDetails(){
        Intent intent = getIntent();
        ChecklistName = intent.getStringExtra("checklistName");
        ChecklistID = intent.getIntExtra("checklistID", 0);
    }

    public void populateList() {
        //Get DATA from DB pass to String[]
        ChecklistDB DB = new ChecklistDB(this);
        //Pass data to ArrayAdapter
        checklists = DB.selectCheckListItems(ChecklistID);
        //In CustomListAdapter
        CustomListAdapter2 adapter = new CustomListAdapter2(this, checklists);
        list = (ListView)findViewById(R.id.user_detailedchecklist_list);
        list.setAdapter(adapter);
    }
}

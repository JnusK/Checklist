package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import highlighter.checklistapp.ChecklistDAO;
import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapter2;
import highlighter.checklistapp.entity.ChecklistDB;
import highlighter.checklistapp.entity.ChecklistItem;

public class UserDetailedChecklistPage extends UserHomepage{

    TextView textViewChecklistName = null;
    RadioButton rbServiceable = null, rbUnserviceable = null;
    String ChecklistName = null;
    int ChecklistID, each_serviceability;
    ListView list;
    Button btnSave;
    ArrayList<ChecklistItem> checklists = new ArrayList<ChecklistItem>();
    View rowView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

//        rbServiceable = (RadioButton)findViewById(R.id.rbServiceable);
//        rbUnserviceable = (RadioButton)findViewById(R.id.rbUnserviceable);

        textViewChecklistName = (TextView)findViewById(R.id.textViewChecklistName);
        getChecklistDetails();
        textViewChecklistName.setText(ChecklistName);

        //fill list based on checklist name
        populateList();

        btnSave = findViewById(R.id.user_detailedchecklist_btnSave);
        btnSave.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ChecklistDAO.accessChecklistDB.addChecklistToDB(ChecklistName , "Daily" , 1);
                ChecklistID = ChecklistDAO.accessChecklistDB.findChecklist(ChecklistName, 1).get(0).getID();

                for (int i = 0; i < checklists.size(); i++){
                    rowView = list.getChildAt(i);
                    RadioButton radio_serviceable = rowView.findViewById(R.id.rbServiceable);
                    RadioButton radio_unserviceable = rowView.findViewById(R.id.rbUnserviceable);

                    if (radio_serviceable.isChecked()) {
                        each_serviceability = 0;
                    }
                    else {
                        each_serviceability = 1;
                    }

                    ChecklistDAO.accessChecklistDB.updateChecklist(each_serviceability,ChecklistID, checklists.get(i).getChecklistItemID());
                }

                Toast.makeText(UserDetailedChecklistPage.this, "Update success", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private void getChecklistDetails(){
        Intent intent = getIntent();
        ChecklistName = intent.getStringExtra("checklistName");
        ChecklistID = intent.getIntExtra("checklistID", 0);
    }

    public void populateList() {
        //Get DATA from DB pass to String[]
        //Pass data to ArrayAdapter
        checklists = ChecklistDAO.accessChecklistDB.selectCheckListItems(ChecklistID);
        //In CustomListAdapter
        CustomListAdapter2 adapter = new CustomListAdapter2(this, checklists);
        list = findViewById(R.id.user_detailedchecklist_list);
        list.setAdapter(adapter);
    }
}

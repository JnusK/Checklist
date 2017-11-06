package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapter2;

public class UserDetailedChecklistPage extends UserHomepage{

    TextView textViewChecklistName;
    RadioButton rbServiceable, rbUnserviceable;

    ListView list;
    String[] itemname ={
            "Test Building",
            "Test Building 2",
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        rbServiceable = (RadioButton)findViewById(R.id.rbServiceable);
        rbUnserviceable = (RadioButton)findViewById(R.id.rbUnserviceable);
        textViewChecklistName = (TextView)findViewById(R.id.textViewChecklistName);
        getChecklistDescription();

        CustomListAdapter2 adapter = new CustomListAdapter2(this, itemname);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

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

    private void getChecklistDescription(){
        Intent intent = getIntent();
        String ChecklistName = intent.getStringExtra("checklistName");
        textViewChecklistName.setText(ChecklistName);
    }
}

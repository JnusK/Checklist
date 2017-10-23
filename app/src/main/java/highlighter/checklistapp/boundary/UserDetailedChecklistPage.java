package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import highlighter.checklistapp.R;

public class UserDetailedChecklistPage extends UserChecklistsPage{

    TextView textViewChecklistItem;
    CheckBox cbItem;

    ListView list;
    String[] itemname ={
            "Test Building",
            "Test Building 2",
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        textViewChecklistItem = (TextView)findViewById(R.id.textViewChecklistItem);
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

         cbItem = (CheckBox) (CheckBox)findViewById(R.id.cbChecklistItem);

    }

    private void getChecklistDescription(){
        Intent intent = getIntent();
        String ChecklistItem = intent.getStringExtra("selectedDesc");
        textViewChecklistItem.setText(ChecklistItem);
    }
}

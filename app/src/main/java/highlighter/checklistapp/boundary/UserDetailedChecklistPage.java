package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import highlighter.checklistapp.R;

public class UserDetailedChecklistPage extends UserChecklistsPage{

    TextView textViewChecklistItem;

    ListView list;
    String[] itemname ={
            "Service Building",
            "Control Building",
    };

    Integer[] imgid={
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        textViewChecklistItem = (TextView)findViewById(R.id.textViewChecklistItem);
        getChecklistDescription();

        //for adapter
        CustomListAdapter2 adapter = new CustomListAdapter2(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Selecteditem = itemname[+position];

                //For testing purposes
                //Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getChecklistDescription(){
        Intent intent = getIntent();
        String ChecklistItem = intent.getStringExtra("selectedDesc");
        textViewChecklistItem.setText(ChecklistItem);
    }
}

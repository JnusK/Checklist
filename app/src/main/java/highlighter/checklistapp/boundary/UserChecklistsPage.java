package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import highlighter.checklistapp.R;

public class UserChecklistsPage extends UserHomepage{

    TextView frequencytextView;

    ListView list;
    String[] itemname ={
            "Service Building",
            "Control Building",
    };

    Integer[] imgid={
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_checklistspage);

        frequencytextView = (TextView)findViewById(R.id.user_checklistspage_textview_frequency);
        getFrequencyText();

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Selecteditem = itemname[+position];

                Intent i = new Intent(UserChecklistsPage.this, UserDetailedChecklistPage.class);
                i.putExtra("selectedDesc", Selecteditem);
                startActivity(i);

                //For testing purposes
                //Toast.makeText(getApplicationContext(), Selecteditem, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getFrequencyText(){
        Intent intent = getIntent();
        String frequencytext = intent.getStringExtra("ftext");
        frequencytextView.setText(frequencytext);
    }

}
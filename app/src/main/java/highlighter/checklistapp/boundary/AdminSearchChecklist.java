package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapterEditChecklist;

public class AdminSearchChecklist extends AppCompatActivity {

    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");
    Spinner frequency_spinner;
    ListView list;
    ArrayList<String> checklist_names;
    Button add_checklist;
    String chosen_frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_checklist);

        initialiseView();
    }


    private void initialiseView(){
        add_checklist = (Button) findViewById(R.id.admin_search_checklist_add_button);
        frequency_spinner = (Spinner) findViewById(R.id.admin_search_checklist_spinner);

        add_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AdminSearchChecklist.this, AdminEditChecklist.class);
                startActivity(i);
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, frequency_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequency_spinner.setAdapter(dataAdapter);
        frequency_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosen_frequency = frequency_spinner.getSelectedItem().toString();
                populateList();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        populateList();
    }

    private void populateList(){
        getFrequencyChecklists();
        CustomListAdapterEditChecklist adapter = new CustomListAdapterEditChecklist(this, checklist_names);
        list = (ListView)findViewById(R.id.admin_search_checklist_listview);
        list.setAdapter(adapter);
    }

    //edit this
    private void getFrequencyChecklists(){
        checklist_names = new ArrayList<>();
        //checklist_names = getFrequency(chosen_frequency);

        //fake data
        if (chosen_frequency == "Weekly"){
            checklist_names.add("Service Building");
            checklist_names.add("Control Building");
        }
        else {
            checklist_names.add("Listing Building");
        }

    }

}

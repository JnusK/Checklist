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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import highlighter.checklistapp.ChecklistDAO;
import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapterEditChecklist;
import highlighter.checklistapp.entity.Checklist;

public class AdminSearchChecklist extends AppCompatActivity {

    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");
    Spinner frequency_spinner;
    ListView list;
    ArrayList<Integer> checklist_id;
    ArrayList<Checklist> checklist_list;
    Button add_checklist;
    String chosen_frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_checklist);

        initialiseView();
    }


    private void initialiseView(){
        add_checklist = findViewById(R.id.admin_search_checklist_add_button);
        frequency_spinner = findViewById(R.id.admin_search_checklist_spinner);

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
        ArrayList<Checklist> checklist_list = ChecklistDAO.accessChecklistDB.findChecklist(chosen_frequency, 3);
        CustomListAdapterEditChecklist adapter = new CustomListAdapterEditChecklist(this, checklist_list);
        list = findViewById(R.id.admin_search_checklist_listview);
        list.setAdapter(adapter);
    }

    //edit this
//    private void getFrequencyChecklists(){
//        checklist_id = new ArrayList<>();
//        checklist_list = new ArrayList<>();
//        checklist_list = ChecklistDAO.accessChecklistDB.findChecklist(chosen_frequency, 3);
//
//        for (int i = 0; i < checklist_list.size(); i += 1){
//            checklist_id.add(checklist_list.get(i).getID());
//        }

//        //fake data
//        if (chosen_frequency == "Weekly"){
//            checklist_id.add("Service Building");
//            checklist_id.add("Control Building");
//        }
//        else {
//            checklist_id.add("Listing Building");
//        }
//    }

}

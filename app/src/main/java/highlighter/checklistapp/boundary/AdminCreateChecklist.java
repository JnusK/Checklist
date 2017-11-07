package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.ChecklistDAO;
import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapterNewChecklist;
import highlighter.checklistapp.entity.Checklist;

public class AdminCreateChecklist extends AppCompatActivity {

    ArrayList<String> checklist_items = new ArrayList<>();
    ArrayList<String> checklist_items_names = new ArrayList<>();
    ListView list;
    int checklist_id;
    Button add_button, create_button;
    String new_item_name, checklist_name;
    TextView item_name_input, checklist_name_input, each_checklist_item_input;
    CustomListAdapterNewChecklist adapter;
    View eachRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_checklist);
        initialiseView();
    }

    private void createNewChecklist(){
        checklist_name = checklist_name_input.getText().toString();
        ChecklistDAO.accessChecklistDB.addChecklistToDB(checklist_name , "Daily" , 1);
        checklist_id = ChecklistDAO.accessChecklistDB.findChecklist(checklist_name, 1).get(0).getID();
        for (int i = 0; i < checklist_items.size(); i++){
            Log.d("TEST", "CHECK ITEM: " + checklist_items.get(i));
            ChecklistDAO.accessChecklistDB.addCheckListItemsToDB(checklist_id, checklist_items.get(i), 0, 1);
        }
    }

    private void initialiseView(){
        add_button = findViewById(R.id.admin_create_checklist_add_button);
        item_name_input = findViewById(R.id.admin_create_checklist_item_name);
        checklist_name_input = findViewById(R.id.admin_create_checklist_name);
        create_button = findViewById(R.id.admin_create_checklist_create_button);

        adapter = new CustomListAdapterNewChecklist(this, checklist_items);
        list = findViewById(R.id.admin_create_checklist_items_list);
        list.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new_item_name = item_name_input.getText().toString();
                checklist_items.add(new_item_name);
                Log.d("TEST: ", "ARRAY: " + new_item_name);
                item_name_input.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminCreateChecklist.this, AdminSearchChecklist.class);
                createNewChecklist();
                startActivity(i);
            }
        });
    }
}
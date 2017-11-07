package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.ChecklistDAO;
import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapterNewChecklist;

public class AdminEditChecklist extends AppCompatActivity {

    ArrayList<String> checklist_items = new ArrayList<>();
    ListView list;
    Button add_button, create_button, delete_button;
    String new_item_name, checklist_name;
    TextView item_name_input, checklist_name_input;
    CustomListAdapterNewChecklist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_checklist);

        initialiseView();
        getChecklistDetails();
    }

    private void createNewChecklist(){
        //Create new checklist
        // checklistname = checklist_name
        // checklistitems = checklist_items
        
    }

    //edit this
    private void deleteChecklist(){

    }

    private void initialiseView(){
        add_button = findViewById(R.id.admin_create_checklist_add_button);
        item_name_input = findViewById(R.id.admin_create_checklist_item_name);
        checklist_name_input = findViewById(R.id.admin_create_checklist_name);
        create_button = findViewById(R.id.admin_create_checklist_create_button);
        delete_button = findViewById(R.id.admin_create_checklist_delete_button);

        adapter = new CustomListAdapterNewChecklist(this, checklist_items);
        list = findViewById(R.id.admin_create_checklist_items_list);
        list.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new_item_name = item_name_input.getText().toString();
                checklist_items.add(new_item_name);
                item_name_input.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminEditChecklist.this, AdminSearchChecklist.class);
                createNewChecklist();
                startActivity(i);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminEditChecklist.this, AdminSearchAccount.class);
                deleteChecklist();
                startActivity(i);
            }
        });
    }

    private void getChecklistDetails(){
        Intent intent = getIntent();
        if(intent.hasExtra("selected_checklist")){
            checklist_name = intent.getStringExtra("selected_checklist");
            checklist_name_input.setText(checklist_name);

            //fake data
            checklist_items.add("Item 1");
            checklist_items.add("Item 2");
            checklist_items.add("Item 3");
            checklist_items.add("Item 4");
        }
    }
}

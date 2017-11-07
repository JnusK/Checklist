package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.ChecklistDAO;
import highlighter.checklistapp.R;
import highlighter.checklistapp.customClass.CustomListAdapterExistingChecklist;
import highlighter.checklistapp.customClass.Subscriber;
import highlighter.checklistapp.entity.ChecklistItem;

public class AdminEditChecklist extends AppCompatActivity implements Subscriber{

    ArrayList<ChecklistItem> checklist_items = new ArrayList<>();
//    ArrayList<Integer> checklist_items_id = new ArrayList<>();
    ListView list;
    Button add_button, create_button, delete_button;
    String new_item_description, checklist_name;
    int checklist_id;
    TextView item_name_input, checklist_name_input;
    CustomListAdapterExistingChecklist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_checklist);

        initialiseView();
        getChecklistDetails();
        initialiseListView();
        ChecklistDAO.accessChecklistDB.addSubscriber(this);
    }

    @Override
    public void update() {
        checklist_items = ChecklistDAO.accessChecklistDB.selectCheckListItems(checklist_id);
        adapter.clear();
        adapter.addAll(checklist_items);
    }

    private void createNewChecklist(){
        checklist_name = checklist_name_input.getText().toString();
        ChecklistDAO.accessChecklistDB.updateChecklistName(checklist_name, checklist_id);
    }

    //edit this
    private void deleteChecklist(){
        //TODO
        ChecklistDAO.accessChecklistDB.deleteChecklist(checklist_id, 1);
    }

    private void initialiseView(){
        add_button = findViewById(R.id.admin_edit_checklist_add_button);
        item_name_input = findViewById(R.id.admin_edit_checklist_item_name);
        checklist_name_input = findViewById(R.id.admin_edit_checklist_name);
        create_button = findViewById(R.id.admin_edit_checklist_create_button);
        delete_button = findViewById(R.id.admin_edit_checklist_delete_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST 1", "SIZE "+checklist_items.size());
                new_item_description = item_name_input.getText().toString();
                ChecklistDAO.accessChecklistDB.addCheckListItemsToDB(checklist_id, new_item_description, 0, 1);
                item_name_input.setText("");
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
                Intent i= new Intent(AdminEditChecklist.this, AdminSearchChecklist.class);
                deleteChecklist();
                startActivity(i);
            }
        });
    }

    private void initialiseListView(){
        adapter = new CustomListAdapterExistingChecklist(this, checklist_items);
        list = findViewById(R.id.admin_edit_checklist_items_list);
        list.setAdapter(adapter);
    }

    private void getChecklistDetails(){
        Intent intent = getIntent();

        if(intent.hasExtra("selected_checklist_id")){
            checklist_id = Integer.parseInt(intent.getStringExtra("selected_checklist_id"));
            checklist_name = ChecklistDAO.accessChecklistDB.findChecklist(Integer.toString(checklist_id), 2).get(0).getName();
            checklist_name_input.setText(checklist_name);
        }

        checklist_items = ChecklistDAO.accessChecklistDB.selectCheckListItems(checklist_id);
    }
}

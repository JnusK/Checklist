package highlighter.checklistapp.customClass;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import highlighter.checklistapp.ChecklistDAO;
import highlighter.checklistapp.R;
import highlighter.checklistapp.UserDAO;
import highlighter.checklistapp.boundary.UserDetailedChecklistPage;
import highlighter.checklistapp.entity.Checklist;
import highlighter.checklistapp.entity.ChecklistItem;

/**
 * Created by Khorly on 24/10/17.
 */

public class CustomListAdapterExistingChecklist extends ArrayAdapter<ChecklistItem> implements Subscriber{
    private final Activity context;
    int checklist_id;
    ArrayList<Integer> checklist_items_id;
    ArrayList<ChecklistItem> checklist_items_list;
    TextView checklist_item_name;
    View rowView;
    LayoutInflater inflater;
    Button delete_button;

    public CustomListAdapterExistingChecklist(Activity context, ArrayList<ChecklistItem> checklist_items) {
        super(context, R.layout.new_checklist_item, checklist_items);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklist_items_list=checklist_items;

//        this.checklist_id=ChecklistDAO.accessChecklistDB.findCheckListID(checklist_items_id.get(0));
        ChecklistDAO.accessChecklistDB.addSubscriber(this);
    }

    @Override
    public void update() {
        checklist_items_list = ChecklistDAO.accessChecklistDB.selectCheckListItems(checklist_id);
        for (int i = 0; i < checklist_items_list.size(); i += 0){
            checklist_items_id = new ArrayList<>();
            checklist_items_id.add(checklist_items_list.get(i).getChecklistID());
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.new_checklist_item, null, true);
        checklist_item_name = rowView.findViewById(R.id.new_checklist_item_name);
        delete_button = rowView.findViewById(R.id.new_checklist_item_delete);
        Log.d("TEST", "position: " + position);

        ChecklistItem selected_checklist = checklist_items_list.get(position);
        checklist_item_name.setText(selected_checklist.getDescription());

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklist_items_id.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    };
}

package highlighter.checklistapp.boundary;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.control.ChecklistDAO;
import highlighter.checklistapp.entity.ChecklistItem;

/**
 * Created by Khorly on 24/10/17.
 */

public class CustomListAdapterExistingChecklist extends ArrayAdapter<ChecklistItem>{
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

        this.checklist_id = checklist_items.get(0).getChecklistID();

    }

//    @Override
//    public void update() {
//        checklist_items_list = ChecklistDAO.accessChecklistDB.selectCheckListItems(checklist_id);
//        notifyDataSetChanged();
//    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.new_checklist_item, null, true);
        checklist_item_name = rowView.findViewById(R.id.new_checklist_item_name);
        delete_button = rowView.findViewById(R.id.new_checklist_item_delete);
        Log.d("TEST", "position: " + position);

        final ChecklistItem selected_checklist = checklist_items_list.get(position);
        checklist_item_name.setText(selected_checklist.getDescription());

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChecklistDAO.accessChecklistDB.deleteChecklistItem(selected_checklist.getChecklistItemID(),1);
            }
        });

        return rowView;
    };
}

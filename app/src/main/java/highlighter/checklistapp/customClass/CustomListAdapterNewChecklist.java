package highlighter.checklistapp.customClass;

import android.app.Activity;
import android.content.Intent;
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

/**
 * Created by Khorly on 24/10/17.
 */

public class CustomListAdapterNewChecklist extends ArrayAdapter<Integer> implements Subscriber{
    private final Activity context;
    int checklist_id;
    ArrayList<Integer> checklist_items;
    TextView checklist_item_name;
    View rowView;
    LayoutInflater inflater;
    Button delete_button;

    public CustomListAdapterNewChecklist(Activity context, ArrayList<Integer> checklist_items) {
        super(context, R.layout.new_checklist_item, checklist_items);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklist_items=checklist_items;
        this.checklist_id=ChecklistDAO.accessChecklistDB.findCheckListID(checklist_items.get(0));
        ChecklistDAO.accessChecklistDB.addSubscriber(this);
    }

    @Override
    public void update() {
//        checklist_items = ChecklistDAO.accessChecklistDB.selectCheckListItems(checklist_id);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.new_checklist_item, null, true);
        checklist_item_name = rowView.findViewById(R.id.new_checklist_item_name);
        delete_button = rowView.findViewById(R.id.new_checklist_item_delete);

        checklist_item_name.setText(checklist_items.get(position));

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklist_items.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    };
}

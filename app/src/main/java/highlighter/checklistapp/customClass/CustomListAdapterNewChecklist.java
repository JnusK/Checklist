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

import highlighter.checklistapp.R;
import highlighter.checklistapp.boundary.UserDetailedChecklistPage;

/**
 * Created by Khorly on 24/10/17.
 */

public class CustomListAdapterNewChecklist extends ArrayAdapter<String>{
    private final Activity context;
    ArrayList<String> checklist_items;
    TextView checklist_item_name;
    View rowView;
    LayoutInflater inflater;
    Button delete_button;

    public CustomListAdapterNewChecklist(Activity context, ArrayList<String> checklist_items) {
        super(context, R.layout.new_checklist_item, checklist_items);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklist_items=checklist_items;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.new_checklist_item, null, true);
        checklist_item_name = (TextView) rowView.findViewById(R.id.new_checklist_item_name);
        delete_button = (Button) rowView.findViewById(R.id.new_checklist_item_delete);

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

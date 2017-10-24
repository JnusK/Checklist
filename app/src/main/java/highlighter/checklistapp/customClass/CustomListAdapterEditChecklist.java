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

import java.lang.reflect.Array;
import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.boundary.AdminEditAccount;
import highlighter.checklistapp.boundary.AdminEditChecklist;
import highlighter.checklistapp.boundary.UserDetailedChecklistPage;

/**
 * Created by Khorly on 24/10/17.
 */

public class CustomListAdapterEditChecklist extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList<String> checklist_names;
    TextView each_checklist_name;
    View rowView;
    LayoutInflater inflater;
    Button edit_button;

    public CustomListAdapterEditChecklist(Activity context, ArrayList<String> checklist_names) {
        super(context, R.layout.edit_checklist_item, checklist_names);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklist_names=checklist_names;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.edit_checklist_item, null, true);
        each_checklist_name = (TextView) rowView.findViewById(R.id.edit_checklist_item_name);
        edit_button = (Button) rowView.findViewById(R.id.edit_checklist_item_button);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected_checklist = checklist_names.get(position);

                Intent i = new Intent(context, AdminEditChecklist.class);
                i.putExtra("selected_checklist", selected_checklist);
                context.startActivity(i);
            }
        });

        each_checklist_name.setText(checklist_names.get(position));
        return rowView;
    };
}

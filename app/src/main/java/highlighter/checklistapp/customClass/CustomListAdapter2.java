package highlighter.checklistapp.customClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.entity.ChecklistItem;

public class CustomListAdapter2 extends ArrayAdapter<ChecklistItem> {

    private final Activity context;
    private ArrayList<ChecklistItem> checklists;

    public CustomListAdapter2(Activity context, ArrayList<ChecklistItem> checklists) {
        super(context, R.layout.mylist2, checklists);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklists=checklists;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist2, null,true);

        TextView txtItemDesc = rowView.findViewById(R.id.textViewChecklistItemDesc);
        RadioButton rbServiceable = rowView.findViewById(R.id.rbServiceable);
        RadioButton rbUnserviceable = rowView.findViewById(R.id.rbUnserviceable);

        txtItemDesc.setText(checklists.get(position).getDescription());
        if (checklists.get(position).getServiceability() == 0){
            rbServiceable.setChecked(true);
        }
        else {
            rbUnserviceable.setChecked(true);
        }

        return rowView;
    };
}

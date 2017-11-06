package highlighter.checklistapp.customClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.entity.ChecklistItem;

public class CustomListAdapter2 extends ArrayAdapter<ChecklistItem> {

    private final Activity context;
    private ArrayList<ChecklistItem> checklist;

    public CustomListAdapter2(Activity context, ArrayList<ChecklistItem> checklist) {
        super(context, R.layout.mylist2, checklist);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklist=checklist;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist2, null,true);

        //TextView txtItemName = (TextView) rowView.findViewById(R.id.textViewItemName);
        TextView txtItemDesc = (TextView) rowView.findViewById(R.id.textViewItemDesc);

        //txtItemName.setText(checklist.get(position).get);
        txtItemDesc.setText("Description "+ checklist.get(position).getDescription());

        return rowView;

    };
}

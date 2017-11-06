package highlighter.checklistapp.customClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.entity.Checklist;

public class CustomListAdapter2 extends ArrayAdapter<Checklist> {

    private final Activity context;
    private ArrayList<Checklist> itemname;

    public CustomListAdapter2(Activity context, ArrayList<Checklist> itemname) {
        super(context, R.layout.mylist2, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist2, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textViewChecklistName);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textViewChecklistDescription);

        txtTitle.setText(itemname.get(position).getName());
        extratxt.setText("Description "+itemname.get(position).getchecklistItems());

        return rowView;

    };
}

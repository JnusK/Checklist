package highlighter.checklistapp.boundary;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.entity.Checklist;

public class CustomListAdapter extends ArrayAdapter<Checklist> {

    private final Activity context;
    private ArrayList<Checklist> itemname;

    public CustomListAdapter(Activity context, ArrayList<Checklist> itemname) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textViewChecklistName);
        txtTitle.setText(itemname.get(position).getName());
        return rowView;

    };
}

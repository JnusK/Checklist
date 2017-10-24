package highlighter.checklistapp.customClass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import highlighter.checklistapp.R;

public class CustomListAdapter2 extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;

    public CustomListAdapter2(Activity context, String[] itemname) {
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

        txtTitle.setText(itemname[position]);
        extratxt.setText("Description "+itemname[position]);

        return rowView;

    };
}

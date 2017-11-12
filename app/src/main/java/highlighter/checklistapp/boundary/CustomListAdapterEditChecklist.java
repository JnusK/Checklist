package highlighter.checklistapp.boundary;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import highlighter.checklistapp.R;
import highlighter.checklistapp.control.ChecklistDAO;
import highlighter.checklistapp.entity.Checklist;

/**
 * Created by Khorly on 24/10/17.
 */

public class CustomListAdapterEditChecklist extends ArrayAdapter<Checklist> implements Subscriber{

    private final Activity context;
    ArrayList<Checklist> checklist_list;
    ArrayList<String> checklist_name = new ArrayList<>();
    TextView each_checklist_name_view, last_modified_view;
    View rowView;
    LayoutInflater inflater;
    Button edit_button, delete_button;
    int last_modified_id;
    String frequency;

    public CustomListAdapterEditChecklist(Activity context, ArrayList<Checklist> checklist_list) {
        super(context, R.layout.edit_checklist_item, checklist_list);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.checklist_list=checklist_list;
//        this.frequency = checklist_list.get(0).getFrequency();
        getChecklistNames();
        ChecklistDAO.accessChecklistDB.addSubscriber(this);
    }

    @Override
    public void update(){
//        checklist_list = ChecklistDAO.accessChecklistDB.findChecklist(frequency, 3);
//        notifyDataSetChanged();
    }

    public void getChecklistNames() {
        for (int i = 0; i < checklist_list.size(); i += 1){
            checklist_name.add(checklist_list.get(i).getName());
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.edit_checklist_item, null, true);
        each_checklist_name_view =  rowView.findViewById(R.id.edit_checklist_item_name);
        edit_button = rowView.findViewById(R.id.edit_checklist_item_button);
//        delete_button = rowView.findViewById(R.id.edit_checklist_delete_button);
        last_modified_view = rowView.findViewById(R.id.edit_checklist_last_modified);


        each_checklist_name_view.setText(checklist_name.get(position));
        //get last_modified_id
        //set last_modified_id view
        last_modified_view.setText("Last modified by: ");

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_checklist_id = checklist_list.get(position).getID();

                Intent i = new Intent(context, AdminEditChecklist.class);
                i.putExtra("selected_checklist_id", Integer.toString(selected_checklist_id));
                context.startActivity(i);
            }
        });

        //edit this
//        delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                 ChecklistDAO.accessChecklistDB.deleteChecklist(checklist_list.get(position).getID(), 1);
//
//            //delete checklist
//            }
//        });

        return rowView;
    }
}

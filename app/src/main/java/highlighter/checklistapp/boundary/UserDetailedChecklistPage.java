package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import highlighter.checklistapp.R;

public class UserDetailedChecklistPage extends UserChecklistsPage{

    TextView textViewChecklistItem;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailedchecklist);

        textViewChecklistItem = (TextView)findViewById(R.id.textViewChecklistItem);
        getChecklistDescription();
    }

    private void getChecklistDescription(){
        Intent intent = getIntent();
        String ChecklistItem = intent.getStringExtra("selectedDesc");
        textViewChecklistItem.setText(ChecklistItem);
    }
}

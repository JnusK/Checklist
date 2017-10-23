package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import highlighter.checklistapp.R;

public class UserHomepage extends AppCompatActivity{
    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");
    Spinner frequency_spinner;
    String frequencytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        Button btnViewAll = (Button)findViewById(R.id.user_homepage_ViewAllbtn);
        frequency_spinner = (Spinner) findViewById(R.id.user_homepage_spinner);
        addItemsToSpinner();

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(UserHomepage.this, UserChecklistsPage.class);
                i.putExtra("ftext", frequencytext);
                startActivity(i);
            }
        });

        frequency_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                frequencytext = frequency_spinner.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void addItemsToSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, frequency_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequency_spinner.setAdapter(dataAdapter);
    }
}
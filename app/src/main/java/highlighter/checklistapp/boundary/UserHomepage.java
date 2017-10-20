package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.List;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import highlighter.checklistapp.R;

public class UserHomepage extends AppCompatActivity {
    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");


    Spinner frequency_spinner;
    Button next_button;
    String frequency_selected;
    UserHomepage current_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        next_button = (Button) findViewById(R.id.user_homepage_next_button);
        frequency_spinner = (Spinner) findViewById(R.id.user_homepage_spinner);
        current_screen = this;

        addItemsToSpinner();
        addListenerOnSpinnerItemSelection();
        addListenerOnButton();
    }


    public void addItemsToSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, frequency_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequency_spinner.setAdapter(dataAdapter);

    }

    public void addListenerOnSpinnerItemSelection() {
        frequency_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(UserHomepage.this, item.toString(),
                            Toast.LENGTH_SHORT).show();
                    frequency_selected = item.toString();
                }
                Toast.makeText(UserHomepage.this, "Selected",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {
        next_button.setOnClickListener(new View.OnClickListener() { //set listener for button clicks
            @Override
            public void onClick(View view) { //code to run when button is clicked
                Intent myIntent = new Intent(current_screen,UserChecklistsPage.class); //specify which Activity to move to
                myIntent.putExtra("key",frequency_selected); //put in data to pass to next activity
                startActivity(myIntent); //start new activity/screen
            }
        });
    }

}
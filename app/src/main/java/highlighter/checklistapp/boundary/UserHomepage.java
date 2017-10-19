package highlighter.checklistapp.boundary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import highlighter.checklistapp.R;

public class UserHomepage extends AppCompatActivity {
    public static final List<String> frequency_list = Arrays.asList("Daily", "Weekly", "Biweekly", "Monthly", "Yearly");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        addItemsToSpinner();
    }

    public void addItemsToSpinner() {
        Spinner frequency_spinner;
        frequency_spinner = (Spinner) findViewById(R.id.user_homepage_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, frequency_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequency_spinner.setAdapter(dataAdapter);
    }
}
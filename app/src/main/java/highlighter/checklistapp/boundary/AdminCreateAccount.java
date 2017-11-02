package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import highlighter.checklistapp.R;
import highlighter.checklistapp.controller.AccessUserDB;

/**
 * Created by Spencer on 20/10/2017.
 */

public class AdminCreateAccount extends AdminHomepage {

    TextView name, password;
    Button create_button;
    String user_password;
    RadioGroup radio_group_user_type;
    RadioButton radio_admin, radio_technician;
    int username, user_type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_createaccount);
        initialiseView();
    }

    private void initialiseView(){
        name = findViewById(R.id.admin_createaccount_name);
        password = findViewById(R.id.admin_createaccount_password);
        create_button = findViewById(R.id.admin_createaccount_create);
        radio_group_user_type = findViewById(R.id.admin_createaccount_radio_group);
        radio_admin = findViewById(R.id.admin_createaccount_radio_admin);
        radio_technician = findViewById(R.id.admin_createaccount_radio_technician);

        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminCreateAccount.this, AdminHomepage.class);
                createNewProfile();
                startActivity(i);
            }
        });

        radio_group_user_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == radio_admin.getId()){
                    user_type = 1;
                }
                else {
                    user_type = 0;
                }
            }
        });
    }

    //edit this
    private void createNewProfile(){
        username = Integer.parseInt(name.getText().toString());
        user_password = password.getText().toString();
        int currentTime = getCurrentTime();
        LoginActivity.accessUserDB.addUserToDB(username, user_password, user_type, currentTime);
        String test1 = LoginActivity.accessUserDB.test;
        Toast.makeText(AdminCreateAccount.this, test1,
                Toast.LENGTH_LONG).show();
        int test = LoginActivity.accessUserDB.authenticateUser(username, user_password);

    }

    private int getCurrentTime(){
        int unixTime = (int) (System.currentTimeMillis() / 1000L);
        return unixTime;
    }
}

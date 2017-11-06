package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import highlighter.checklistapp.R;
import highlighter.checklistapp.UserDAO;
import highlighter.checklistapp.entity.User;

public class AdminEditAccount extends AppCompatActivity {

    TextView name, password;
    Button create_button, delete_button;
    String user_password, new_user_password;
    int user_type, user_id, new_user_id, new_user_type;
    User user;
    RadioGroup radio_group_user_type;
    RadioButton radio_admin, radio_technician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_account);

        initialiseView();
        getAccountDetails();
        setView();
    }

    private void getAccountDetails(){
        Intent intent = getIntent();
        user_id = intent.getIntExtra("selected_item", 0);
        user = UserDAO.userDB.getUser(user_id);
        user_type = user.getType();
        user_password = user.getPassword();
    }

    private void setView(){
        name.setText(Integer.toString(user_id));
        password.setText(user_password);
        if (user_type == 1) {
            //admin
            radio_admin.setChecked(true);
        }
        else {
            //admin
            radio_technician.setChecked(true);
        }
    }

    private void updateAccount(){
        UserDAO.userDB.deleteUser(user_id);
        new_user_id = Integer.parseInt(name.getText().toString());
        new_user_password = password.getText().toString();
        if (radio_admin.isChecked()){
            user_type = 1;
        }
        else {
            user_type = 0;
        }
        int currentTime = getCurrentTime();
        UserDAO.userDB.addUserToDB(new_user_id, new_user_password, new_user_type, currentTime);
    }

    private int getCurrentTime(){
        int unixTime = (int) (System.currentTimeMillis() / 1000L);
        return unixTime;
    }

    private void deleteAccount(){
        UserDAO.userDB.deleteUser(user_id);
    }

    private void initialiseView(){
        name = findViewById(R.id.admin_edit_account_name);
        password = findViewById(R.id.admin_edit_account_password);
        create_button = findViewById(R.id.admin_edit_account_create);
        radio_group_user_type = findViewById(R.id.admin_edit_account_radio_group);
        radio_admin = findViewById(R.id.admin_edit_account_radio_admin);
        radio_technician = findViewById(R.id.admin_edit_account_radio_technician);
        delete_button = findViewById(R.id.admin_edit_account_delete_button);

        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminEditAccount.this, AdminSearchAccount.class);
                updateAccount();
                startActivity(i);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminEditAccount.this, AdminSearchAccount.class);
                deleteAccount();
                startActivity(i);
            }
        });

    }
}

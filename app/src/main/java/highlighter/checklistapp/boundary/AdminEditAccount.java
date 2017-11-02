package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import highlighter.checklistapp.R;

public class AdminEditAccount extends AppCompatActivity {

    TextView name, password;
    Button create_button;
    String user_password;
    int username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_account);

        initialiseView();
        getAccountDetails();
    }

    //edit this
    private void getAccountDetails(){
        Intent intent = getIntent();
        String selected_item = intent.getStringExtra("selected_item");
        name.setText(selected_item);
    }

    //edit this
    private void updateAccount(){
        username = Integer.parseInt(name.getText().toString());
        user_password = password.getText().toString();
    }

    private void initialiseView(){
        name = (TextView) findViewById(R.id.admin_edit_account_name);
        password = (TextView) findViewById(R.id.admin_edit_account_password);
        create_button = (Button) findViewById(R.id.admin_edit_account_create);

        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminEditAccount.this, AdminSearchAccount.class);
                updateAccount();
                startActivity(i);
            }
        });
    }
}

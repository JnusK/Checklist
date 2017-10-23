package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import highlighter.checklistapp.R;

/**
 * Created by Spencer on 20/10/2017.
 */

public class AdminCreateAccount extends AdminHomepage {

    TextView name, password;
    Button create_button;

    String username, user_password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_createaccount);

        name = (TextView) findViewById(R.id.admin_createaccount_name);
        password = (TextView) findViewById(R.id.admin_createaccount_password);
        create_button = (Button) findViewById(R.id.admin_createaccount_create);

        create_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminCreateAccount.this, AdminHomepage.class);
                createNewProfile();
                startActivity(i);
            }
        });
    }

    private void createNewProfile(){
        username = name.getText().toString();
        user_password = password.getText().toString();
        Toast.makeText(AdminCreateAccount.this, "Created new user",
                Toast.LENGTH_LONG).show();
    }
}

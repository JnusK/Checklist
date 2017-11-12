package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import highlighter.checklistapp.R;
import highlighter.checklistapp.control.UserDAO;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    TextView name, password;
    Button login_button;
    int username;
    String user_password;
    Intent admin_i, user_i;
    int authenticate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialiseView();

    }

    private void initialiseView(){
        name = findViewById(R.id.login_name);
        password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                username = Integer.parseInt(name.getText().toString());
                user_password = password.getText().toString();
                admin_i= new Intent(LoginActivity.this, AdminHomepage.class);
                user_i = new Intent(LoginActivity.this, UserHomepage.class);
                authenticate = UserDAO.userDB.authenticateUser(username, user_password);
                if (authenticate == 1) {
                    //user and password correct
                    int user_type = UserDAO.userDB.checkUserType(username);
                    if (user_type == 1) {
                        //admin
                        startActivity(admin_i);
                    }
                    else {
                        //user
                        startActivity(user_i);
                    }
                }
                else if (authenticate == 0) {
                    //user does not exist
                    Toast.makeText(LoginActivity.this, "User does not exist",
                            Toast.LENGTH_LONG).show();
                    name.setText("");
                    password.setText("");
                }
                else {
                    //password incorrect
                    Toast.makeText(LoginActivity.this, "Password incorrect",
                            Toast.LENGTH_LONG).show();
                    password.setText("");
                }

            }
        });
    }

}
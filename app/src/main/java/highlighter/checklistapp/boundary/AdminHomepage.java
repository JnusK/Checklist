package highlighter.checklistapp.boundary;

import highlighter.checklistapp.LoginActivity;
import highlighter.checklistapp.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class AdminHomepage extends LoginActivity implements OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId())
        {

            //To be updated, Spencer 19/10/2017
            case R.id.admin_homepage_textViewcreate:
                i = new Intent(this, UserHomepage.class);
                startActivity(i);
                break;
            case R.id.admin_homepage_textViewedit:
                i = new Intent(this, UserHomepage.class);
                startActivity(i);
                break;
            case R.id.admin_homepage_textViewmodify:
                i = new Intent(this, UserHomepage.class);
                startActivity(i);
                break;
            case R.id.admin_homepage_textViewstatus:
                i = new Intent(this, UserHomepage.class);
                startActivity(i);
                break;
            case R.id.admin_homepage_textViewchecklist:
                i = new Intent(this, UserHomepage.class);
                startActivity(i);
                break;
        }
    }
}

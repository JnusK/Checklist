package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import highlighter.checklistapp.R;

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
            case R.id.admin_homepage_manage_account_button:
                i = new Intent(AdminHomepage.this, AdminSearchAccount.class);
                startActivity(i);
                break;

            case R.id.admin_homepage_manage_checklist_button:
                i = new Intent(AdminHomepage.this, AdminSearchChecklist.class);
                startActivity(i);
                break;
        }
    }
}

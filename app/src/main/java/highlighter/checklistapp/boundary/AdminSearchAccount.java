package highlighter.checklistapp.boundary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import highlighter.checklistapp.R;

public class AdminSearchAccount extends AppCompatActivity {

    ListView account_list;
    EditText search_filter;
    ArrayAdapter adapter;
    ArrayList<String> account_names;
    Button add_account_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_account);

        getAccounts();
        initialiseView();
    }

    private void initialiseView(){
        account_list = findViewById(R.id.admin_search_account_search_accounts_list);
        search_filter = findViewById(R.id.admin_search_account_search_filter);
        add_account_button = findViewById(R.id.admin_search_account_search_add_button);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, account_names);
        account_list.setAdapter(adapter);

        account_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String selected_item = account_names.get(+position);

                Intent i = new Intent(AdminSearchAccount.this, AdminEditAccount.class);
                i.putExtra("selected_item", selected_item);
                startActivity(i);
            }
        });

        add_account_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(AdminSearchAccount.this, AdminCreateAccount.class);
                startActivity(i);
            }
        });

        activateSearch();
    }

    //edit this
    private void getAccounts(){
        account_names = new ArrayList<>();

        //fake data
        account_names.add("A");
        account_names.add("B");
        account_names.add("C");
        account_names.add("D");

    }

    private void activateSearch(){
        search_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

package highlighter.checklistapp.boundary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import highlighter.checklistapp.R;

public class AdminSearchAccount extends AppCompatActivity {

    ListView account_list;
    EditText search_filter;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_account);
        account_list = (ListView) findViewById(R.id.admin_search_account_search_accounts_list);
        search_filter = (EditText) findViewById(R.id.admin_search_account_search_filter);

        populateList();
        activateSearch();
    }

    private void populateList(){
        ArrayList<String> accounts = new ArrayList<>();
        accounts.add("A");
        accounts.add("B");
        accounts.add("C");
        accounts.add("D");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, accounts);
        account_list.setAdapter(adapter);
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

package com.dev.rohmts.crudsqlite.Activities;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import com.dev.rohmts.crudsqlite.DBDataSource;
import com.dev.rohmts.crudsqlite.Pojo.Employee;
import com.dev.rohmts.crudsqlite.R;

import java.util.ArrayList;

public class ViewDataActivity extends ListActivity {

    private DBDataSource dataSource;
    private ArrayList<Employee> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        dataSource = new DBDataSource(this);
        dataSource.open();
        values = dataSource.getAllEmployee();

        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }
}

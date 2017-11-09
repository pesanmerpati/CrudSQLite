package com.dev.rohmts.crudsqlite.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.rohmts.crudsqlite.DBDataSource;
import com.dev.rohmts.crudsqlite.Pojo.Employee;
import com.dev.rohmts.crudsqlite.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAdd, buttonView, buttonClear;
    private EditText etFrstname, etLastName, etSalary;

    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.btAdd);
        buttonClear = findViewById(R.id.btClear);
        buttonView = findViewById(R.id.btView);
        etFrstname = findViewById(R.id.etFrstName);
        etLastName = findViewById(R.id.etLastName);
        etSalary = findViewById(R.id.etSalary);

        dataSource = new DBDataSource(this);
        dataSource.open();

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        String frstName = null, lastName = null, salary = null;

        Employee employee = null;

        if (etFrstname.getText()!=null && etLastName.getText()!=null && etSalary.getText()!=null) {
            frstName = etFrstname.getText().toString();
            lastName = etLastName.getText().toString();
            salary = etSalary.getText().toString();
        }

        switch (view.getId()) {
            case R.id.btAdd:
                employee = dataSource.createEmployee(frstName, lastName, salary);
                Toast.makeText(this, "Employee "+employee.getFirstName()+" "+employee.getLastName()
                        +" added! ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btView:
                Intent intent = new Intent(MainActivity.this, ViewDataActivity.class);
                startActivity(intent);
                break;
            case R.id.btClear:
                etFrstname.setText("");
                etLastName.setText("");
                etSalary.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuUpdate:
                Intent intentUpdate = new Intent(this, EditDataActivity.class);
                startActivity(intentUpdate);
                break;
            case R.id.menuDelete:
                Intent intentDelete = new Intent(this, DeleteDataActivity.class);
                startActivity(intentDelete);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

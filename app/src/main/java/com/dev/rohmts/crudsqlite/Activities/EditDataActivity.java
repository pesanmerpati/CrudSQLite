package com.dev.rohmts.crudsqlite.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.rohmts.crudsqlite.DBDataSource;
import com.dev.rohmts.crudsqlite.DBHelper;
import com.dev.rohmts.crudsqlite.Pojo.Employee;
import com.dev.rohmts.crudsqlite.R;

public class EditDataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUpdateId, etUpdateFrstName, etUpdateLastName, etUpdateSalary;
    private Button buttonSave, buttonLoad;

    private DBDataSource dataSource;
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private long idLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        etUpdateId = findViewById(R.id.etEmpId);
        etUpdateFrstName = findViewById(R.id.etUpdateFrstName);
        etUpdateLastName = findViewById(R.id.etUpdateLastName);
        etUpdateSalary = findViewById(R.id.etUpdateSalary);

        buttonLoad = findViewById(R.id.btLoad);
        buttonSave = findViewById(R.id.btSave);
        buttonSave.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLoad:
                dataSource = new DBDataSource(this);
                dataSource.open();

                idLoad = Long.parseLong(etUpdateId.getText().toString().trim());
                Employee empl = dataSource.getEmployee(idLoad);

                etUpdateFrstName.setText(empl.getFirstName());
                etUpdateLastName.setText(empl.getLastName());
                etUpdateSalary.setText(empl.getSalary());
                dataSource.close();
                break;

            case R.id.btSave:
                dataSource = new DBDataSource(this);
                dataSource.open();
                Employee emplUpdt = new Employee();
                emplUpdt.setFirstName(etUpdateFrstName.getText().toString());
                emplUpdt.setLastName(etUpdateLastName.getText().toString());
                emplUpdt.setSalary(etUpdateSalary.getText().toString());
                emplUpdt.setEmpId(idLoad);

                dataSource.updateEmployee(emplUpdt);
                Intent intent = new Intent(this, ViewDataActivity.class);
                startActivity(intent);
                finish();
                dataSource.close();

                Toast.makeText(this, "Update done!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

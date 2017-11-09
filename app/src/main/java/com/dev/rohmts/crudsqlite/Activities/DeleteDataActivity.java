package com.dev.rohmts.crudsqlite.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.rohmts.crudsqlite.DBDataSource;
import com.dev.rohmts.crudsqlite.DBHelper;
import com.dev.rohmts.crudsqlite.Pojo.Employee;
import com.dev.rohmts.crudsqlite.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DeleteDataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmpId;
    private TextView textViewResult;
    private Button buttonDelete;
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
        editTextEmpId = findViewById(R.id.etEmpId);
        textViewResult = findViewById(R.id.tvResult);
        buttonDelete = findViewById(R.id.btDelete);
        buttonDelete.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        dataSource = new DBDataSource(this);
        dataSource.open();

        long idDelete = Long.parseLong(editTextEmpId.getText().toString().trim());
        Employee empl = dataSource.getEmployee(idDelete);
        dataSource.deleteEmploye(idDelete);
        textViewResult.setText("Data with employee id "+empl+" has been deleted");
    }
}

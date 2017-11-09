package com.dev.rohmts.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.dev.rohmts.crudsqlite.Pojo.Employee;

import java.util.ArrayList;



public class DBDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnId, DBHelper.columnFirstName,
            DBHelper.columnLastname, DBHelper.columnSalary};

    public DBDataSource (Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLiteException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Employee createEmployee(String frst, String last, String salary) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnFirstName, frst);
        values.put(DBHelper.columnLastname, last);
        values.put(DBHelper.columnSalary, salary);

        long insertId = database.insert(DBHelper.tableName, null, values);

        Cursor cursor = database.query(DBHelper.tableName, allColumns, DBHelper.columnId+" = "
        +insertId, null, null, null, null);

        cursor.moveToFirst();

        Employee newEmpl = cursorToEmpl(cursor);
        cursor.close();
        return newEmpl;
    }

    private Employee cursorToEmpl(Cursor cursor) {

        Employee employee = new Employee();
        /*Log.v("info", "The getLONG "+cursor.getLong(0));
        Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));*/
        employee.setEmpId(cursor.getLong(0));
        employee.setFirstName(cursor.getString(1));
        employee.setLastName(cursor.getString(2));
        employee.setSalary(cursor.getString(3));
        return employee;
    }

    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> listEmployee = new ArrayList<Employee>();
        Cursor cursor = database.query(DBHelper.tableName, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Employee employee = cursorToEmpl(cursor);
            listEmployee.add(employee);
            cursor.moveToNext();
        }
        cursor.close();
        return listEmployee;
    }

    public Employee getEmployee(long id) {
        Employee employee = new Employee();
        Cursor cursor = database.query(DBHelper.tableName, allColumns, "_id="+id, null, null, null, null);
        cursor.moveToFirst();
        employee = cursorToEmpl(cursor);
        cursor.close();
        return employee;
    }

    public void updateEmployee(Employee emp) {
        String stringFilter = "_id=" + emp.getEmpId();
        ContentValues args = new ContentValues();
        args.put(DBHelper.columnFirstName, emp.getFirstName());
        args.put(DBHelper.columnLastname, emp.getLastName());
        args.put(DBHelper.columnSalary, emp.getSalary());
        database.update(DBHelper.tableName, args, stringFilter, null);
    }

    public void deleteEmploye(long id) {
        String stringFilter = "_id=" + id;
        database.delete(DBHelper.tableName, stringFilter, null);
    }



}

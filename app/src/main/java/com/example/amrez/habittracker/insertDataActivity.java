package com.example.amrez.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrez.habittracker.SqlData.Contract;
import com.example.amrez.habittracker.SqlData.DbHelper;


public class insertDataActivity extends AppCompatActivity {
    EditText name, age, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        bio = (EditText) findViewById(R.id.bio);
    }

    public void insertData() {

        String nameString = name.getText().toString().trim();
        String ageString = age.getText().toString().trim();
        String bioString = bio.getText().toString().trim();

        DbHelper mDbHelper = new DbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.DataEntry.COLUMN_NAME, nameString);
        values.put(Contract.DataEntry.AGE, Integer.parseInt(ageString));
        values.put(Contract.DataEntry.BIO, bioString);

        long newRowId = db.insert(Contract.DataEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error in saving habit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved successfully Row where saved is : " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public void OnInsert(View view) {
        if ("".equals(name.getText().toString())) {
            Toast.makeText(this, "Habit Name must be not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        insertData();
        finish();
    }
}

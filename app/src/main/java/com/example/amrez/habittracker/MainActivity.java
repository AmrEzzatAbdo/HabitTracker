package com.example.amrez.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.amrez.habittracker.SqlData.Contract;
import com.example.amrez.habittracker.SqlData.DbHelper;

public class MainActivity extends AppCompatActivity {

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, insertDataActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new DbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabase();
    }

    private Cursor read() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Contract.DataEntry._ID,
                Contract.DataEntry.COLUMN_NAME,
                Contract.DataEntry.AGE,
                Contract.DataEntry.BIO
        };

        Cursor cursor = db.query(Contract.DataEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    private void displayDatabase() {
        Cursor cursor = read();

        TextView displayView = (TextView) findViewById(R.id.TSDataViewer);

        try {
            displayView.setText("table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(Contract.DataEntry._ID + " | " +
                    Contract.DataEntry.COLUMN_NAME + " | " +
                    Contract.DataEntry.AGE + " | " +
                    Contract.DataEntry.BIO + " | " + "\n"
            );

            int idColumnIndex = cursor.getColumnIndex(Contract.DataEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.DataEntry.COLUMN_NAME);
            int ageColumnIndex = cursor.getColumnIndex(Contract.DataEntry.AGE);
            int bioColumnIndex = cursor.getColumnIndex(Contract.DataEntry.BIO);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBio = cursor.getString(bioColumnIndex);
                int currentAge = cursor.getInt(ageColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentAge + " - " +
                        currentBio
                ));
            }
        } finally {
            cursor.close();
        }
    }

    private void insert() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        values1.put(Contract.DataEntry.COLUMN_NAME, "Amr");
        values1.put(Contract.DataEntry.AGE, 22);
        values2.put(Contract.DataEntry.COLUMN_NAME, "Moro");
        values2.put(Contract.DataEntry.AGE, 25);
        db.insert(Contract.DataEntry.TABLE_NAME, null, values1);
        db.insert(Contract.DataEntry.TABLE_NAME, null, values2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insertData:
                insert();
                displayDatabase();
                return true;
            case R.id.deletAll:
                DbHelper mDbHelper = new DbHelper(this);
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                db.execSQL("delete from " + Contract.DataEntry.TABLE_NAME);
                displayDatabase();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package id.shimo.mindfulness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.shimo.mindfulness.helper.DBHelper;
import id.shimo.mindfulness.model.Journal;

import id.shimo.mindfulness.adapter.JournalAdapter;

public class Home extends AppCompatActivity {
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Journal> journalHolder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add = (FloatingActionButton) findViewById(R.id.addJurnal);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddJurnalActivity.class);
                startActivity(intent);
            }
        });

        DBHelper db = new DBHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.rvJournals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DBHelper(this).readJournals();

        while(cursor.moveToNext()){
            Journal obj = new Journal(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
            journalHolder.add(obj);
        }

        JournalAdapter adapter = new  JournalAdapter(journalHolder, Home.this, sqLiteDatabase);
        recyclerView.setAdapter((RecyclerView.Adapter) adapter);
    }


}
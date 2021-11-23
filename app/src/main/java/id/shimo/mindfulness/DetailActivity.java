package id.shimo.mindfulness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import id.shimo.mindfulness.helper.DBHelper;

public class DetailActivity extends AppCompatActivity {
    private TextView tvRate, tvDatetime, tvBestThing, tvWorstThing, tvSeekRate, tvRadioResult, tvCheckResult;
    Integer id;
    String bestThing, worstThing, rate, radioButton, check, datetime;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        bestThing = intent.getStringExtra("BestThing");
        worstThing = intent.getStringExtra("WorstThing");
        rate = intent.getStringExtra("Rate");
        radioButton = intent.getStringExtra("RadioButton");
        check = intent.getStringExtra("Check");
        datetime = intent.getStringExtra("DateTime");
        id = intent.getIntExtra("Id", 0);

        tvBestThing = (TextView) findViewById(R.id.bestThing);
        tvWorstThing = (TextView) findViewById(R.id.worstThing);
        tvSeekRate = (TextView) findViewById(R.id.seekRate);
        tvRadioResult = (TextView) findViewById(R.id.radioResult);
        tvCheckResult = (TextView) findViewById(R.id.checkResult);
        tvDatetime = (TextView) findViewById(R.id.datetime);

        tvBestThing.setText(bestThing);
        tvWorstThing.setText(worstThing);
        tvSeekRate.setText(rate);
        tvRadioResult.setText(radioButton);
        tvCheckResult.setText(check);
        tvDatetime.setText(datetime);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(DetailActivity.this, "onRestart Running", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(DetailActivity.this, "onResume Running", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(DetailActivity.this, "onStop Running", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(DetailActivity.this, "onDestroy Running", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
                intent.putExtra("BestThing", bestThing);
                intent.putExtra("WorstThing", worstThing);
                intent.putExtra("Rate", rate);
                intent.putExtra("RadioButton", radioButton);
                intent.putExtra("Check", check);
                intent.putExtra("DateTime", datetime);
                intent.putExtra("Id", id);
                startActivity(intent);
                return true;
            case R.id.delete:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
                alertDialog.setTitle("Delete Confirmation");
                alertDialog.setMessage("Are you sure to delete this journal?");
                alertDialog.setCancelable(false);
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Log.v("tess", id.toString());
                        db = new DBHelper(DetailActivity.this);
                        db.deleteJournal(id);
                        startActivity(new Intent(DetailActivity.this, Home.class));
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return true;
        }

        return false;
    }
}
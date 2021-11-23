package id.shimo.mindfulness;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView tvRate, tvBestThing, tvWorstThing, tvSeekRate, tvRadioResult, tvCheckResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String bestThing = intent.getStringExtra("BestThing");
        String worstThing = intent.getStringExtra("WorstThing");
        String rate = intent.getStringExtra("Rate");
        String radioButton = intent.getStringExtra("RadioButton");
        String check = intent.getStringExtra("Check");

        tvBestThing = (TextView) findViewById(R.id.bestThing);
        tvWorstThing = (TextView) findViewById(R.id.worstThing);
        tvSeekRate = (TextView) findViewById(R.id.seekRate);
        tvRadioResult = (TextView) findViewById(R.id.radioResult);
        tvCheckResult = (TextView) findViewById(R.id.checkResult);

        tvBestThing.setText(bestThing);
        tvWorstThing.setText(worstThing);
        tvSeekRate.setText(rate);
        tvRadioResult.setText(radioButton);
        tvCheckResult.setText(check);
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
}
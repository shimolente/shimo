package id.shimo.mindfulness;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddJurnalActivity extends AppCompatActivity {

    private TextInputLayout etBestThing, etWorstThing;
    private SeekBar seekRate;
    private ArrayList<String> didChecks;
    private CheckBox smileCheck, gratefulCheck, careCheck;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_jurnal);

        etBestThing = findViewById(R.id.bestThing);
        etWorstThing = findViewById(R.id.worstThing);
        seekRate = findViewById(R.id.rateSeek);

        smileCheck = (CheckBox)findViewById(R.id.smileCheck);
        gratefulCheck = (CheckBox)findViewById(R.id.gratefulCheck);
        careCheck = (CheckBox)findViewById(R.id.careCheck);
        didChecks = new ArrayList<>();

        btnSubmit = (Button) findViewById(R.id.button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

package id.shimo.mindfulness;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import id.shimo.mindfulness.helper.DBHelper;

public class UpdateActivity extends AppCompatActivity {

    private TextInputLayout etBestThing, etWorstThing;
    private SeekBar seekRate;
    private ArrayList<String> didChecks;
    private CheckBox smileCheck, gratefulCheck, careCheck;
    private Button btnSubmit;
    private AlertDialog.Builder dialog;
    private LayoutInflater inflater;
    private View dialogView;
    private StringBuilder stringChecks;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView tvRate, tvBestThing, tvWorstThing, tvSeekRate, tvRadioResult, tvCheckResult, tvDatetime;
    private DBHelper db;
    String bestThing, worstThing, rate, radioButtonIntent, check, datetime;
    Integer id;
    private RadioButton yesRadio, noRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_jurnal);

        Intent intent = getIntent();
        bestThing = intent.getStringExtra("BestThing");
        worstThing = intent.getStringExtra("WorstThing");
        rate = intent.getStringExtra("Rate");
        radioButtonIntent = intent.getStringExtra("RadioButton");
        check = intent.getStringExtra("Check");
        datetime = intent.getStringExtra("DateTime");
        id = intent.getIntExtra("Id", 0);

        etBestThing = findViewById(R.id.username);
        etWorstThing = findViewById(R.id.password);
        seekRate = findViewById(R.id.rateSeek);
        tvRate = findViewById(R.id.ratetextvalue);

        etBestThing.getEditText().setText(bestThing);
        etWorstThing.getEditText().setText(worstThing);
        tvRate.setText(rate);
        seekRate.setProgress(Integer.valueOf(rate));

        radioGroup = findViewById(R.id.radioGroup);
        yesRadio = findViewById(R.id.yesCheck);
        noRadio = findViewById(R.id.noCheck);
        if(radioButtonIntent.equals(yesRadio.getText().toString())){
            radioGroup.check(R.id.yesCheck);
        }else if(radioButtonIntent.equals(noRadio.getText().toString())){
            radioGroup.check(R.id.noCheck);
        }

        smileCheck = (CheckBox)findViewById(R.id.smileCheck);
        gratefulCheck = (CheckBox)findViewById(R.id.gratefulCheck);
        careCheck = (CheckBox)findViewById(R.id.careCheck);
        didChecks = new ArrayList<>();

        String[] did = check.split("- ");
        if(did.length > 1){
            smileCheck.setChecked(true);
            didChecks.add(smileCheck.getText().toString());
        }
        if(did.length > 2){
            gratefulCheck.setChecked(true);
            didChecks.add(gratefulCheck.getText().toString());
        }
        if(did.length > 3){
            careCheck.setChecked(true);
            didChecks.add(careCheck.getText().toString());
        }

        checkOnClick();

        btnSubmit = (Button) findViewById(R.id.login);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpdate(didChecks);
            }
        });
    }

    private void checkOnClick() {
        smileCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (smileCheck.isChecked())
                    didChecks.add(smileCheck.getText().toString());
                else
                    didChecks.remove(smileCheck.getText().toString());
            }
        });

        gratefulCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gratefulCheck.isChecked())
                    didChecks.add(gratefulCheck.getText().toString());
                else
                    didChecks.remove(gratefulCheck.getText().toString());
            }
        });

        careCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (careCheck.isChecked())
                    didChecks.add(careCheck.getText().toString());
                else
                    didChecks.remove(careCheck.getText().toString());
            }
        });
    }

    private void btnUpdate(ArrayList<String> benefits){
        //benefits array to string
        stringChecks = new StringBuilder();
        for (String s : didChecks)
            stringChecks.append(" - "+s).append("\n");

        radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        db = new DBHelper(this);
        db.updateJournal(id,
                etBestThing.getEditText().getText().toString(),
                etWorstThing.getEditText().getText().toString(),
                tvRate.getText().toString(),
                radioButton.getText().toString(),
                stringChecks.toString(),
                datetime);
    }
}

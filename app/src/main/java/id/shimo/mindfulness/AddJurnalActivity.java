package id.shimo.mindfulness;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class AddJurnalActivity extends AppCompatActivity {

    private TextInputLayout etCompanyName, etJobTitle, etJobDesc, etCountry;
    private SeekBar seekBarSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_jurnal);
    }
}

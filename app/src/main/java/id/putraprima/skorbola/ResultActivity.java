package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MatchActivity.KET_KEY;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView pemenang = (TextView) findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            pemenang.setText(extras.getString(KET_KEY));
        }
    }
}

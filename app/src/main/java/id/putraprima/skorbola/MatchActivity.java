package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.AWAYTEAM_KEY;
import static id.putraprima.skorbola.MainActivity.HOMETEAM_KEY;
import static id.putraprima.skorbola.MainActivity.IMAGEHOME_KEY;
import static id.putraprima.skorbola.MainActivity.IMAGETEAM_KEY;

public class MatchActivity extends AppCompatActivity {

    public static final String KET_KEY = "ket";

    private TextView hometext;
    private ImageView homeImage;
    private ImageView teamImage;
    private TextView teamtext;
    private TextView homeScore;
    private TextView teamScore;
    private Uri uri2;
    private Uri uri;

    int scoreA =0;
    int scoreB =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        hometext = findViewById(R.id.txt_home);
        homeImage = findViewById(R.id.home_logo);
        homeScore = findViewById(R.id.score_home);
        teamImage = findViewById(R.id.away_logo);
        teamtext = findViewById(R.id.txt_away);
        teamScore = findViewById(R.id.score_away);

        Bundle extrass = getIntent().getExtras();
        if(extrass != null){
            hometext.setText(extrass.getString(HOMETEAM_KEY));
            uri = Uri.parse(extrass.getString(IMAGEHOME_KEY));
            uri2 = Uri.parse(extrass.getString(IMAGETEAM_KEY));
            teamtext.setText(extrass.getString(AWAYTEAM_KEY));
            Bitmap bitmap = null;
            Bitmap bitmap2 = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            }catch (IOException e){
                e.printStackTrace();
            }
            homeImage.setImageBitmap(bitmap);
            teamImage.setImageBitmap(bitmap2);
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void scoreHome(View view) {
        scoreA++;
        homeScore.setText(String.valueOf(scoreA));
    }

    public void scoreTeam(View view) {
        scoreB++;
        teamScore.setText(String.valueOf(scoreB));
    }

    public void handlecek(View view) {
        String ket = null;
        if (scoreA>scoreB){
            ket = "Name of Winning is "+hometext.getText().toString();
        }else  if (scoreB>scoreA){
            ket = "Name of Winning is "+teamtext.getText().toString();
        }else if (scoreA==scoreB){
            ket = "Name of Winning is Draw";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(KET_KEY, ket);
        startActivity(intent);
    }
}

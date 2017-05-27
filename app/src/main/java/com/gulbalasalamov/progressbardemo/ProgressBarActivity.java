package com.gulbalasalamov.progressbardemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarActivity extends AppCompatActivity {

    ProgressBar pb;
    Button btn;
    TextView tv;
    int progressDurumu = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        pb = (ProgressBar) findViewById(R.id.progressbar);
        pb.setVisibility(View.GONE);
        btn = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Uygulama 10 saniye içinde kapatılacak", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.VISIBLE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressDurumu < 100) {
                            progressDurumu = progressDurumu + 10;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    pb.setProgress(progressDurumu);
                                    tv.setText("Değer: " + progressDurumu + "/" + pb.getMax());
                                }
                            });

                            try {
                                //200ms uyku
                                //progresi yavasca göster
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        progressDurumu = 0;
                    }
                }).start();

                Toast.makeText(ProgressBarActivity.this, "Uygulama kapatılıyor. ", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            System.exit(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
}
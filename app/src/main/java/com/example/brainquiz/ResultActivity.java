package com.example.brainquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvFinalScore = findViewById(R.id.tvFinalScore);
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        Button btnBack = findViewById(R.id.btnBack);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String language = getIntent().getStringExtra("language");
        String level = getIntent().getStringExtra("level");

        tvFinalScore.setText("Score: " + score + "/" + total);

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("language", language);
            intent.putExtra("level", level);
            startActivity(intent);
            finish();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
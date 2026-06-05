package com.example.brainquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgLanguage, rgLevel;
    Button btnStart;
    TextView tvSelectLanguage, tvSelectLevel;
    RadioButton rbEnglish, rbArabic, rbEasy, rbMedium, rbHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgLanguage = findViewById(R.id.rgLanguage);
        rgLevel = findViewById(R.id.rgLevel);
        btnStart = findViewById(R.id.btnStart);
        tvSelectLanguage = findViewById(R.id.tvSelectLanguage);
        tvSelectLevel = findViewById(R.id.tvSelectLevel);
        rbEnglish = findViewById(R.id.rbEnglish);
        rbArabic = findViewById(R.id.rbArabic);
        rbEasy = findViewById(R.id.rbEasy);
        rbMedium = findViewById(R.id.rbMedium);
        rbHard = findViewById(R.id.rbHard);

        // تحميل الإعدادات المحفوظة
        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        String savedLang = sp.getString("language", "English");
        String savedLevel = sp.getString("level", "Easy");

        if (savedLang.equals("Arabic")) {
            rbArabic.setChecked(true);
            updateUI("Arabic");
        } else {
            rbEnglish.setChecked(true);
        }

        if (savedLevel.equals("Medium")) {
            rbMedium.setChecked(true);
        } else if (savedLevel.equals("Hard")) {
            rbHard.setChecked(true);
        } else {
            rbEasy.setChecked(true);
        }

        rgLanguage.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbArabic) {
                updateUI("Arabic");
            } else {
                updateUI("English");
            }
        });

        btnStart.setOnClickListener(v -> {
            String language = "English";
            if (rgLanguage.getCheckedRadioButtonId() == R.id.rbArabic) {
                language = "Arabic";
            }

            String level = "Easy";
            int checkedLevel = rgLevel.getCheckedRadioButtonId();
            if (checkedLevel == R.id.rbMedium) {
                level = "Medium";
            } else if (checkedLevel == R.id.rbHard) {
                level = "Hard";
            }

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("language", language);
            editor.putString("level", level);
            editor.apply();

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("language", language);
            intent.putExtra("level", level);
            startActivity(intent);
        });
    }

    void updateUI(String lang) {
        if (lang.equals("Arabic")) {
            tvSelectLanguage.setText("اختر اللغة");

            tvSelectLevel.setText("اختر المستوى");
            rbEasy.setText("سهل");
            rbMedium.setText("متوسط");
            rbHard.setText("صعب");
            btnStart.setText("ابدأ اللعبة");
        } else {
            tvSelectLanguage.setText("Select Language");
            tvSelectLevel.setText("Select Level");
            rbEasy.setText("Easy");
            rbMedium.setText("Medium");
            rbHard.setText("Hard");
            btnStart.setText("Start Game");
        }
    }
}
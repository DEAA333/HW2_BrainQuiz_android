package com.example.brainquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgLanguage, rgLevel;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgLanguage = findViewById(R.id.rgLanguage);
        rgLevel = findViewById(R.id.rgLevel);
        btnStart = findViewById(R.id.btnStart);

        // تحميل الإعدادات المحفوظة
        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        String savedLang = sp.getString("language", "English");
        String savedLevel = sp.getString("level", "Easy");

        if (savedLang.equals("Arabic")) {
            ((RadioButton) findViewById(R.id.rbArabic)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.rbEnglish)).setChecked(true);
        }

        if (savedLevel.equals("Medium")) {
            ((RadioButton) findViewById(R.id.rbMedium)).setChecked(true);
        } else if (savedLevel.equals("Hard")) {
            ((RadioButton) findViewById(R.id.rbHard)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.rbEasy)).setChecked(true);
        }

        btnStart.setOnClickListener(v -> {
            // قراءة اللغة
            String language = "English";
            if (rgLanguage.getCheckedRadioButtonId() == R.id.rbArabic) {
                language = "Arabic";
            }

            // قراءة المستوى
            String level = "Easy";
            int checkedLevel = rgLevel.getCheckedRadioButtonId();
            if (checkedLevel == R.id.rbMedium) {
                level = "Medium";
            } else if (checkedLevel == R.id.rbHard) {
                level = "Hard";
            }

            // حفظ الإعدادات
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("language", language);
            editor.putString("level", level);
            editor.apply();

            // الانتقال لشاشة اللعبة
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("language", language);
            intent.putExtra("level", level);
            startActivity(intent);
        });
    }
}
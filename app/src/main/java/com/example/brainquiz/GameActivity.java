package com.example.brainquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class GameActivity extends AppCompatActivity {

    TextView tvQuestion, tvLevel, tvScore;
    RadioGroup rgAnswers;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    Button btnNext;

    int score = 0;
    int currentQuestion = 0;

    // أسئلة English
    String[] questionsEasyEn = {"What is the capital of France?", "What color is the sky?", "How many days in a week?"};
    String[][] answersEasyEn = {{"Paris", "London", "Rome", "Berlin"}, {"Blue", "Red", "Green", "Yellow"}, {"7", "5", "10", "6"}};
    int[] correctEasyEn = {0, 0, 0};

    String[] questionsMediumEn = {"What planet is closest to the sun?", "Who wrote Romeo and Juliet?", "What is H2O?"};
    String[][] answersMediumEn = {{"Mercury", "Venus", "Earth", "Mars"}, {"Shakespeare", "Dickens", "Hemingway", "Twain"}, {"Water", "Oxygen", "Hydrogen", "Salt"}};
    int[] correctMediumEn = {0, 0, 0};

    String[] questionsHardEn = {"What is the square root of 144?", "Who discovered gravity?", "What is the speed of light?"};
    String[][] answersHardEn = {{"12", "14", "10", "16"}, {"Newton", "Einstein", "Galileo", "Darwin"}, {"300,000 km/s", "150,000 km/s", "500,000 km/s", "100,000 km/s"}};
    int[] correctHardEn = {0, 0, 0};

    // أسئلة Arabic
    String[] questionsEasyAr = {"ما عاصمة فرنسا؟", "ما لون السماء؟", "كم يوم في الأسبوع؟"};
    String[][] answersEasyAr = {{"باريس", "لندن", "روما", "برلين"}, {"أزرق", "أحمر", "أخضر", "أصفر"}, {"7", "5", "10", "6"}};

    String[] questionsMediumAr = {"أقرب كوكب للشمس؟", "من كتب روميو وجولييت؟", "ما هو H2O؟"};
    String[][] answersMediumAr = {{"عطارد", "الزهرة", "الأرض", "المريخ"}, {"شكسبير", "ديكنز", "همنغواي", "توين"}, {"ماء", "أكسجين", "هيدروجين", "ملح"}};

    String[] questionsHardAr = {"ما الجذر التربيعي لـ 144؟", "من اكتشف الجاذبية؟", "ما سرعة الضوء؟"};
    String[][] answersHardAr = {{"12", "14", "10", "16"}, {"نيوتن", "أينشتاين", "غاليليو", "داروين"}, {"300,000 كم/ث", "150,000 كم/ث", "500,000 كم/ث", "100,000 كم/ث"}};

    String[] questions;
    String[][] answers;
    int[] correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvLevel = findViewById(R.id.tvLevel);
        tvScore = findViewById(R.id.tvScore);
        rgAnswers = findViewById(R.id.rgAnswers);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnNext = findViewById(R.id.btnNext);

        String language = getIntent().getStringExtra("language");
        String level = getIntent().getStringExtra("level");

        tvLevel.setText("Level: " + level);

        // اختيار الأسئلة حسب اللغة والمستوى
        if (language.equals("Arabic")) {
            if (level.equals("Easy")) { questions = questionsEasyAr; answers = answersEasyAr; correct = correctEasyEn; }
            else if (level.equals("Medium")) { questions = questionsMediumAr; answers = answersMediumAr; correct = correctMediumEn; }
            else { questions = questionsHardAr; answers = answersHardAr; correct = correctHardEn; }
        } else {
            if (level.equals("Easy")) { questions = questionsEasyEn; answers = answersEasyEn; correct = correctEasyEn; }
            else if (level.equals("Medium")) { questions = questionsMediumEn; answers = answersMediumEn; correct = correctMediumEn; }
            else { questions = questionsHardEn; answers = answersHardEn; correct = correctHardEn; }
        }

        showQuestion();

        btnNext.setOnClickListener(v -> {
            int selected = rgAnswers.getCheckedRadioButtonId();
            if (selected == -1) {
                Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedIndex = 0;
            if (selected == R.id.rbOption2) selectedIndex = 1;
            else if (selected == R.id.rbOption3) selectedIndex = 2;
            else if (selected == R.id.rbOption4) selectedIndex = 3;

            if (selectedIndex == correct[currentQuestion]) {
                score++;
                Toast.makeText(this, "Correct! ✓", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong! ✗", Toast.LENGTH_SHORT).show();
            }

            tvScore.setText("Score: " + score);
            currentQuestion++;

            if (currentQuestion < questions.length) {
                showQuestion();
            } else {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", questions.length);
                intent.putExtra("language", getIntent().getStringExtra("language"));
                intent.putExtra("level", getIntent().getStringExtra("level"));
                startActivity(intent);
                finish();
            }
        });
    }

    void showQuestion() {
        tvQuestion.setText(questions[currentQuestion]);
        rbOption1.setText(answers[currentQuestion][0]);
        rbOption2.setText(answers[currentQuestion][1]);
        rbOption3.setText(answers[currentQuestion][2]);
        rbOption4.setText(answers[currentQuestion][3]);
        rgAnswers.clearCheck();
    }
}
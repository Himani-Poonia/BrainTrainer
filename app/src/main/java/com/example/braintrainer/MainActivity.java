package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button gameLauncherButton, button0, button1, button2, button3;
    TextView timerTextView, taskTextView, resultTextView, scoreTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int totalQuestions = 0;
    Button playAgainButton;
    ConstraintLayout gameConstraintLayout;

    public void click(View view) {

        gameLauncherButton.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(View.VISIBLE);

    }


    public void chooseAnswer(View view) {

        String correctTag = view.getTag().toString();

        if(correctTag.equals(Integer.toString(locationOfCorrectAnswer))) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }

        totalQuestions++;
        scoreTextView.setText(score + "/" + totalQuestions);

        newQuestion();
    }

    public void newQuestion() {

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        taskTextView = findViewById(R.id.taskTextView);

        taskTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for(int i = 0; i < 4; i++) {

            if(i == locationOfCorrectAnswer) {
                answers.add(a + b);
            }
            else {
                int wrongAnswer = random.nextInt(41);

                if(wrongAnswer == a+b)
                    wrongAnswer = random.nextInt(41);

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view) {

        countTimer();
        score = 0;
        totalQuestions = 0;
        scoreTextView.setText(score + "/" + totalQuestions);
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

    }

    public void countTimer() {

        new CountDownTimer(30000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(Integer.toString( (int) millisUntilFinished / 1000 ) + "s");

            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }

        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLauncherButton = findViewById(R.id.gameLauncherButton);

        gameLauncherButton.setVisibility(View.VISIBLE);

        gameConstraintLayout= findViewById(R.id.gameConstraintLayout);
        gameConstraintLayout.setVisibility(View.INVISIBLE);
        timerTextView = findViewById(R.id.timerTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        resultTextView.setText("");

        newQuestion();
        countTimer();


    }
}
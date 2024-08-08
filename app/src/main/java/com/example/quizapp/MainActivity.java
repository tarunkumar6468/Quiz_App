package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
 import android.app.AlertDialog;
 import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView questionTextView;
    TextView tatolQuestionTextView;
    Button ansA, ansB,ansC,ansD;

    Button btn_submit;
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tatolQuestionTextView = findViewById(R.id.Total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.button1);
        ansB = findViewById(R.id.button2);
        ansC = findViewById(R.id.button3);
        ansD = findViewById(R.id.button4);
        btn_submit = findViewById(R.id.button5);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);



        tatolQuestionTextView.setText("Text question: "+totalQuestion);
        loadNewQuestion();

    }
    private void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer = "";


    }
    private void finishQuiz(){
        String passStatus;
        if(score>=totalQuestion*0.6){
            passStatus = "Passed";
        }
        else {
            passStatus = "Failed";
        }


        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is "+ score + " out of "+totalQuestion)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz() ))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clikedButton = (Button) view;
        if(clikedButton.getId()==R.id.button5){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                    score++;
                }else {
                    clikedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
            else {

            }
        }else {
            selectedAnswer = clikedButton.getText().toString();
            clikedButton.setBackgroundColor(Color.YELLOW);
        }

    }
}
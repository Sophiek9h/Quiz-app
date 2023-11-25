package com.twdc.hbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView numOfQuestions, questions;
    private Button option1, option2, option3, option4;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0, questionsAttempted = 1, currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numOfQuestions = findViewById(R.id.numOfQuestions);
        questions = findViewById(R.id.questions);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        quizModalArrayList = new ArrayList<>();
        random = new Random();
        currentPos = random.nextInt(quizModalArrayList.size() + 1);

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1.getText().toString().trim().toLowerCase())){
                    currentScore++;

                }
                questionsAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });


        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }

                questionsAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });


        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase())){
                    currentScore++;
                    option3.setBackgroundColor(Color.GREEN);
                }

                questionsAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase())){
                    currentScore++;
                    option4.setBackgroundColor(Color.GREEN);
                }

                questionsAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

        getQuizQuestions(quizModalArrayList);

        setDataToViews(currentPos);



    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_buttom_sheet, (LinearLayout)findViewById(R.id.linearScore));
        TextView score = bottomSheetView.findViewById(R.id.score);
        Button restartQuiz = bottomSheetView.findViewById(R.id.restartQuiz);

        Intent i = getIntent();
        String name = i.getStringExtra("name");

        score.setText( name + " You Scored \n" + currentScore + "/10");

        restartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 currentPos = random.nextInt(quizModalArrayList.size());
                  setDataToViews(currentPos);
                  questionsAttempted = 1;
                  currentScore = 0;
                  bottomSheetDialog.dismiss();
            }

        });

        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }


    private void setDataToViews(int currentPos) {
        numOfQuestions.setText("Questions Attempted : "+ questionsAttempted + "/10");

        if (questionsAttempted == 10){
            showBottomSheet();
        }else{
            questions.setText(quizModalArrayList.get(currentPos).getQuestions());
            option1.setText(quizModalArrayList.get(currentPos).getOption1());
            option2.setText(quizModalArrayList.get(currentPos).getOption2());
            option3.setText(quizModalArrayList.get(currentPos).getOption3());
            option4.setText(quizModalArrayList.get(currentPos).getOption4());
        }
        }



    private void getQuizQuestions(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Who was the first man created by God?", "Adam", "Noah", "Abraham", "Moses", "Adam"));
        quizModalArrayList.add(new QuizModal("Which prophet is known for being swallowed by a great fish?", "Elijah", "Isaiah", "Jonah", "Jeremiah", "Jonah"));
        quizModalArrayList.add(new QuizModal(" Which of the Ten Commandments says, \"You shall not steal\"?", "The first commandment", "The fifth commandment", "The seventh commandment", "The eighth commandment", "The eighth commandment"));
        quizModalArrayList.add(new QuizModal("Who was sold into slavery by his brothers but later became a powerful ruler in Egypt?", " Jacob", "Joseph", "Isaac", " Esau", "Joseph"));
        quizModalArrayList.add(new QuizModal("What is the first book of the New Testament?", "Genesis", "Exodus", "Matthew", "Mark", "Matthew"));
        quizModalArrayList.add(new QuizModal("Who was known for his great strength and had his hair cut by Delilah?", "David", "Solomon", "Samson", "Saul", "Samson"));
        quizModalArrayList.add(new QuizModal(" What is the name of the sea that was parted to allow the Israelites to escape from Egypt?", "The Red Sea", "The Mediterranean Sea", "The Dead Sea", "The Jordan River", "The Red Sea"));
        quizModalArrayList.add(new QuizModal(" Who is known for being the wisest king in the Bible and for building the First Temple in Jerusalem?", "King David", "King Solomon", "Kind Saul", "King Herod", "King Solomon"));
        quizModalArrayList.add(new QuizModal("Which apostle is often referred to as \"Doubting Thomas\" because he initially doubted Jesus' resurrection?", "Peter", "James", "John", "Thomas", "Thomas"));
        quizModalArrayList.add(new QuizModal("In the story of the Good Samaritan, who stopped to help a man who had been beaten and left for dead on the side of the road?", "The priest", "A Levite", "A Samaritan", " A Pharisee", "A Samaritan"));

    }
}
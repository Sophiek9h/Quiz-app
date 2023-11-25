package com.twdc.hbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    EditText enterName;
    TextView ready;
    Button startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        enterName = findViewById(R.id.enterName);
        ready = findViewById(R.id.ready);
        startQuiz = findViewById(R.id.startQuiz);

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = enterName.getText().toString();

                Intent i = new Intent(
                        Details.this,
                        MainActivity.class
                );

                i.putExtra("name", name);

                startActivity(i);


            }
        });

    }
}
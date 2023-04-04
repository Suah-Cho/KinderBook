package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.Board;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

public class TeacherHomeActivity extends AppCompatActivity {

    RetrofitClient retrofitClient;
    RetrofitInterface retrofitInterface;
    private TextView postName;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        String meal;

        Intent intent= getIntent();
        meal = intent.getStringExtra("식단");
        System.out.println(meal);
        TextView meal_notice = (TextView) findViewById(R.id.meal_notice);
        meal_notice.setText(meal);

        postName = findViewById(R.id.notice_text);

        postName.setText("");

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();
//        retrofitInterface.getBoard()



    }
}
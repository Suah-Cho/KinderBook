package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.parentsletterproject.R;

public class ParentsHomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_TCM = 101;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_home);


        Button button3 = findViewById(R.id.post);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardListActivity.class);
            startActivity(intent);
        });

        Button button4 = findViewById(R.id.meal1);
        button4.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MealActivity.class);
            startActivity(intent);
        });
        Button button5 = findViewById(R.id.schedule);
        button5.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TestTimeTable.class);
            startActivity(intent);
        });

    }
}
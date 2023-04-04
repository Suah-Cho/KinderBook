package com.example.parentsletterproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parentsletterproject.R;

public class CallActivity extends AppCompatActivity{
    EditText edit_tel;
    Button btn_tel;

    @Override
    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_call);

        btn_tel=findViewById(R.id.btnCall);
        edit_tel=findViewById(R.id.edtNumber);

        //전화번호 표시로 변경해주기
        edit_tel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        btn_tel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tel_number="tel:"+edit_tel.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number));
                startActivity(intent);
            }
        });
    }
}
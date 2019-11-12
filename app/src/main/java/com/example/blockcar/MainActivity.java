package com.example.blockcar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText id_Edit;
    EditText pasw_Edit;

    private Button btn_signup,btn_login;
          @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

       id_Edit=(EditText) findViewById(R.id.id_Edit);
       pasw_Edit=(EditText) findViewById(R.id.password_Edit);

        btn_signup=(Button) findViewById(R.id.sign_button);
        btn_login=(Button) findViewById(R.id.login_button);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, Sign_up.class);
                startActivity(intent);
            }
        });

       btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Board.class);
                startActivity(intent);
            }
        });


    }

}

package com.example.blockcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Check_information_repair  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_info_repair);


        Button gas_btn= (Button) findViewById(R.id.btn_gas);
        Button accident_btn= (Button) findViewById(R.id.btn_accident);



        gas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_history = new Intent(Check_information_repair.this, Check_information_gas.class);
                startActivity(intent_history);
            }

        });

        accident_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_history = new Intent(Check_information_repair.this, Check_information_accident.class);
                startActivity(intent_history);
            }

        });


    }
}

package com.example.blockcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Check_information_accident extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_info_accident);
        Button repair_btn= (Button) findViewById(R.id.btn_repair);
        Button gas_btn= (Button) findViewById(R.id.btn_gas);



        repair_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_history = new Intent(Check_information_accident.this, Check_information_repair.class);
                startActivity(intent_history);
            }

        });

       gas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_history = new Intent(Check_information_accident.this, Check_information_gas.class);
                startActivity(intent_history);
            }

        });



    }
}

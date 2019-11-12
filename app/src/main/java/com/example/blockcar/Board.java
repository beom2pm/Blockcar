package com.example.blockcar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Board extends AppCompatActivity {
    TextView test_id;
    TextView test_pasw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        Button history_of_car = (Button) findViewById(R.id.history_of_car);


        ImageView privacy = (ImageView) findViewById(R.id.setting_button);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Board.this, Privacy.class);
                startActivity(intent3);
            }
        });

        ImageView check_accident = (ImageView) findViewById(R.id.accident_button);
        check_accident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Board.this, Accident.class);
                startActivity(intent);
            }
        });

        ImageView check_gas=(ImageView)findViewById(R.id.gas_button);
        check_gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Board.this, Gas_Record.class);
                startActivity(intent);
            }
        });

        ImageView check_service=(ImageView)findViewById(R.id.repair_button);
        check_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Board.this, Service_Record.class);
                startActivity(intent);
            }
        });

        history_of_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_history = new Intent(Board.this, Check_information_gas.class);
                startActivity(intent_history);
            }

        });

    }
}

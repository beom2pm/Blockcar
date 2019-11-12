package com.example.blockcar;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Gas_Record extends AppCompatActivity {

    int mYear, mMonth,mDay;
    TextView mTxtdate;
    Intent intent;
    String address;
    String title;
    TextView GasText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_record);


        ImageButton select_date= (ImageButton) findViewById(R.id.select_date);
        Button complete_btn=(Button) findViewById(R.id.complete_btn);

        mTxtdate=(TextView) findViewById(R.id.text_date);
        Calendar cal =new GregorianCalendar();
        mYear=cal.get(Calendar.YEAR);
        mMonth=cal.get(Calendar.MONTH);
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();

        GasText = (TextView) findViewById(R.id.gas_station_text);
        intent = getIntent(); //getIntent()로 받을준비
        title = intent.getStringExtra("title");
        GasText.setText(title);


        ImageButton location_arrow = (ImageButton) findViewById(R.id.location_arrow);
        location_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gas_Record.this, Car_repair.class);
                startActivity(intent);
            }
        });



        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( Gas_Record.this,mDateSetListener, mYear,mMonth,mDay).show();
            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_complete = new Intent(Gas_Record.this, Board.class);
                startActivity(intent_complete);
            }
        });

    }


    DatePickerDialog.OnDateSetListener mDateSetListener=
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    mYear=year;
                    mMonth=month;
                    mDay=dayOfMonth;
                    UpdateNow();

                }
            };
    void UpdateNow(){
        mTxtdate.setText(String.format("%d/%d/%d",mYear,mMonth+1,mDay));
    }

}
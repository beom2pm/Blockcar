package com.example.blockcar;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Service_Record extends AppCompatActivity {

    int mYear, mMonth,mDay;
    TextView mTxtdate;

    private final int GET_GALLERY_IMAGE1 = 200;
    private ImageView imageview1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_record);

        Button complete_btn=(Button) findViewById(R.id.complete_btn);
        ImageButton select_date= (ImageButton) findViewById(R.id.select_date);
        mTxtdate=(TextView) findViewById(R.id.text_date);
        Calendar cal =new GregorianCalendar();
        mYear=cal.get(Calendar.YEAR);
        mMonth=cal.get(Calendar.MONTH);
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();


        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Service_Record.this,mDateSetListener, mYear,mMonth,mDay).show();
            }
        });

        imageview1 = (ImageView)findViewById(R.id.image1);
        imageview1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE1);
            }
        });


        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_complete = new Intent(Service_Record.this, Board.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri1 = data.getData();
            imageview1.setImageURI(selectedImageUri1);

        }


    }
    }


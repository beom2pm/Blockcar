package com.example.blockcar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.*;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Callback;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;


import static java.lang.System.in;

public class Register_car extends AppCompatActivity {

    EditText color;
    EditText brand;
    EditText car_name;
    EditText year;
    EditText car_number;
    EditText vin_number;
    EditText car_content;
    private HttpConnection httpConn = HttpConnection.getInstance();
    String userID;
    String color_s;
    String brand_s;
    String car_names;
    String year_s;
    String car_numbers;
    String vin_numbers;
    String car_contents;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_car);
        final String user_id = "test";
        color = (EditText) findViewById(R.id.car_color_edt);
        brand = (EditText) findViewById(R.id.car_make_edt);
        car_name = (EditText) findViewById(R.id.car_model_edt);
        year = (EditText) findViewById(R.id.car_year_edt);
        car_number = (EditText) findViewById(R.id.car_number_edt);
        vin_number = (EditText) findViewById(R.id.car_onlyNum_edt);
        car_content = (EditText) findViewById(R.id.car_content_edt);
        Button register_btn = (Button) findViewById(R.id.complete_register);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = "test";
                color_s = color.getText().toString();
                brand_s = brand.getText().toString();
                car_names = car_name.getText().toString();
                year_s = year.getText().toString();
                car_numbers = car_number.getText().toString();
                vin_numbers = vin_number.getText().toString();
               car_contents = car_content.getText().toString();
               sendData(userID,color_s,brand_s,car_names,year_s,car_numbers,vin_numbers,car_contents);

               Intent intent=new Intent(Register_car.this,Board.class);
               startActivity(intent);

            }
        });
    }

    private void sendData(String userID,String color_s,String brand_s,String car_names,String year_s,String car_numbers,String vin_numbers,String car_contents) {
// 네트워크 통신하는 작업은 무조건 작업스레드를 생성해서 호출 해줄 것!!
        new Thread() {
            public void run() {
// 파라미터 2개와 미리정의해논 콜백함수를 매개변수로 전달하여 호출
                httpConn.requestWebServer("userID", "color_s","brand_s","car_names","year_s","car_numbers","vin_numbers","car_contents",callback);
            }
        }.start();
    }


    private final Callback callback = new Callback() {

        public void onFailure(Call call, IOException e) {
            Log.d("오류", "콜백오류:"+e.getMessage());
        }

        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d("완료", "서버에서 응답한 Body:"+body);
        }
    };

            }

package com.example.blockcar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Sign_up extends AppCompatActivity {
    String name, id, password;
    EditText name_sign, id_sign, password_sign, password_check;
    AlertDialog.Builder alert;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        name_sign = (EditText) findViewById(R.id.name);
        id_sign = (EditText) findViewById(R.id.id_sign);
        password_sign = (EditText) findViewById(R.id.password_sign);
        password_check = (EditText) findViewById(R.id.password_sign_check);

        Button id_check = (Button) findViewById(R.id.id_check);
        id_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id_sign.getText().toString();
                new JSONTask().execute("http://54.180.112.77:3000/post");//AsyncTask 시작시킴
                Log.d("요청", "요청완료 ");
            }
        });

        Button complete_sign = (Button) findViewById(R.id.complete_sign);
        complete_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id_sign.getText().toString();
                name = name_sign.getText().toString();
                password = password_sign.getText().toString();
                new JSONTask().execute("http://54.180.112.77:3001/post");//AsyncTask 시작시킴
                Log.d("요청2", "요청완료2 ");
                Intent intent = new Intent(Sign_up.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public class JSONTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("user_id", id);

                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    //URL url = new URL("http://192.168.25.16:3000/users");
                    URL url = new URL(urls[0]);
                    //연결을 함
                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST");//POST방식으로 보냄
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
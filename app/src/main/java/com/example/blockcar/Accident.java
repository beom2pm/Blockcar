package com.example.blockcar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;

import com.example.blockcar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Accident extends AppCompatActivity {


    int mYear, mMonth,mDay;
    TextView mTxtdate;
    Intent intent;
    String address;
    String title;
    TextView locateText,memo;
    Button complete;
    String JsonResultString;
    Accident.GetData task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accident);

        ImageButton select_date= (ImageButton) findViewById(R.id.select_date);
        mTxtdate=(TextView) findViewById(R.id.text_date);
        Calendar cal =new GregorianCalendar();
        mYear=cal.get(Calendar.YEAR);
        mMonth=cal.get(Calendar.MONTH);
        mDay=cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();

        locateText = (TextView) findViewById(R.id.accident_station_text);
        complete=(Button)findViewById(R.id.complete_btn);
        memo = (TextView) findViewById(R.id.memo);
        intent = getIntent(); //getIntent()로 받을준비
        title = intent.getStringExtra("title");
        locateText.setText(title);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new Accident.GetData();
                task.execute("http://54.180.112.77/accident?car_id=" + "test" + "&explain=" + memo.getText(), "");
            }
        });

        ImageButton location_arrow = (ImageButton) findViewById(R.id.location_arrow);
        location_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accident.this, Location2.class);
                startActivity(intent);
            }
        });


        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Accident.this,mDateSetListener, mYear,mMonth,mDay).show();
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

    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                JsonResultString = result;
                InitializeQuestionData();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = params[1];

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {

                return null;
            }
        }
    }

    public void InitializeQuestionData() {
        String TAG_JSON = "webnautes";

        try {
            JSONObject jsonObject = new JSONObject(JsonResultString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);


        } catch (JSONException e) {
        }
    }
}
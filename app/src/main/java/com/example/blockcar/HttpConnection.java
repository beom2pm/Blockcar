package com.example.blockcar;

import android.view.textclassifier.TextClassification;
import okhttp3.*;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;


public class HttpConnection {

        private OkHttpClient client;
        private static HttpConnection instance = new HttpConnection();
        public static HttpConnection getInstance() {
            return instance;
        }

        private HttpConnection(){ this.client = new OkHttpClient(); }


        /** 웹 서버로 요청을 한다. */
        public void requestWebServer(String userID, String color_s,String brand_s,String car_names,String year_s,String car_numbers,String vin_numbers,String car_contents, Callback callback) {
            RequestBody body = new FormBody.Builder()
                    .add("user_id", userID)
                    .add("color", color_s)
                    .add("brand", brand_s)
                    .add("car_name", car_names)
                    .add("year", year_s)
                    .add("car_number", car_numbers)
                    .add("vin_number", vin_numbers)
                    .add("car_comment", car_contents)
                    .build();
           Request request = new Request.Builder()
                    .url("http://203.245.10.33:8888/car_insert.php")
                    .post(body)
                    .build();
            client.newCall(request).enqueue(callback);
        }

}

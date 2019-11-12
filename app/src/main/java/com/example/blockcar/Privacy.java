package com.example.blockcar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Privacy extends AppCompatActivity {

    public static final String TAG = "Test_Alert_Dialog";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy);


        TextView privacy = (TextView) findViewById(R.id.textView3);

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(Privacy.this);

                ad.setTitle("현재 비밀번호 변경");       // 제목 설정
                ad.setMessage("현재 비밀번호를 입력해주세요.");   // 내용 설정

                final EditText et = new EditText(Privacy.this);
                ad.setView(et);


                // 취소 버튼 설정
                ad.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG,"No Btn Click");
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });


                // 확인 버튼 설정
                ad.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG,"Yes Btn Click");
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

                // 창 띄우기
                ad.show();
            }
        });


        Button logout  = (Button) findViewById(R.id.logout_btn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Privacy.this, MainActivity.class);
                startActivity(intent2);
            }
        });

        Button register_car  = (Button) findViewById(R.id.register_btn);

        register_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Privacy.this, Register_car.class);
                startActivity(intent3);
            }
        });


        Button withdrawal  = (Button) findViewById(R.id.withdraw_btn);
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Blockcar 탈퇴");
        builder.setMessage("Blockcar에서 탈퇴하면 해당 계정의 모든 정보가 삭제되며 다시 복구할 수 없습니다. 정말 탈퇴하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Privacy.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"회원 탈퇴가 완료되었습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"회원 탈퇴를 취소합니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }



}


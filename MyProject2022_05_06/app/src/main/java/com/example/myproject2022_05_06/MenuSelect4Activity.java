//메뉴셀렉트4 엑티비티
package com.example.myproject2022_05_06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuSelect4Activity extends AppCompatActivity {
    Button BtnYes, BtnNo;
    TextView textview;
    TextView don;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_select4);

        BtnYes = findViewById(R.id.Btn_yes);
        BtnNo = findViewById(R.id.Btn_no);
        textview = findViewById(R.id.name);
        don = findViewById(R.id.total_price);

        CheckBox menu1 = (CheckBox) findViewById(R.id.menu1);
        CheckBox menu2 = (CheckBox) findViewById(R.id.menu2);
        CheckBox menu3 = (CheckBox) findViewById(R.id.menu3);
        CheckBox menu4 = (CheckBox) findViewById(R.id.menu4);

        findViewById(R.id.menu1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCheck(menu1, menu2, menu3, menu4);
            }
        });
        findViewById(R.id.menu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCheck(menu1, menu2, menu3, menu4);
            }
        });
        findViewById(R.id.menu3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCheck(menu1, menu2, menu3, menu4);
            }
        });
        findViewById(R.id.menu4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCheck(menu1, menu2, menu3, menu4);
            }
        });


        BtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), reciteActivity.class);
                intent.putExtra("it_check", sendCheck(menu1, menu2, menu3, menu4));
                intent.putExtra("name", textview.getText().toString());
                intent.putExtra("don", don.getText().toString());
                startActivity(intent);
            }
        });
        BtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private String sendCheck(CheckBox menu1, CheckBox menu2, CheckBox menu3, CheckBox menu4){
        String resultText = "";
        int a = 0;
        if (menu1.isChecked()){
            resultText += (menu1.getText().toString()+",");
            a = a+6000;
        }if (menu2.isChecked()){
            resultText += (menu2.getText().toString()+",");
            a = a+6000;
        }
        if (menu3.isChecked()){
            resultText += (menu3.getText().toString()+",");
            a = a+6000;
        }
        if (menu4.isChecked()){
            resultText += menu4.getText().toString();
            a = a+6000;
        }
        String[] A = resultText.split(",");
        don.setText("최종금액 : " + a + "원");
        String B = "";
        for(int i = 0; i<A.length; i++){
            if(i==A.length-1){
                B+=A[i];
            }
            else{
                B+=(A[i] + ",");
            }
        }
        return B;
    }
}
package com.example.myproject2022_05_06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
//import com.google.android.gms.maps.GoogleMap;

public class RoomCreateActivity extends AppCompatActivity {
    String[] items = {"한식집", "중식집", "일식집", "양식집"};
    EditText peopleNum, money;
    TimePicker timePicker;
    String hour, minute;
    Integer delivery;
    Button btnMap, btnCreate, btnCancel;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference databaseReference = database.getReference();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        Spinner spinner = findViewById(R.id.spinner);
        //스피너 값 받기

        peopleNum = findViewById(R.id.peopleNum);
        money = findViewById(R.id.money);
        //인원수 돈 값 받기

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(false);
        Calendar calendar = Calendar.getInstance();
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i1, int i2) {
                hour = String.valueOf(i1);
                minute = String.valueOf(i2);
            }
        });
        hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        minute = String.valueOf(calendar.get(Calendar.MINUTE));
        //타임피커 값 받기

        btnMap = findViewById(R.id.btnMap);
        btnCreate = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);
        // 버튼 선언


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //스피너 선택

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mapLocation.class);
                startActivity(intent);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(spinner.getSelectedItem().toString().equals("한식집")){
                    delivery = 4000;
                }
                if(spinner.getSelectedItem().toString().equals("중식집")){
                    delivery = 4000;
                }
                if(spinner.getSelectedItem().toString().equals("일식집")){
                    delivery = 4000;
                }
                if(spinner.getSelectedItem().toString().equals("양식집")){
                    delivery = 4000;
                }
                addfood(spinner.getSelectedItem().toString(), peopleNum.getText().toString(),
                        hour.toString(), minute.toString(), money.getText().toString(), delivery);
                setResult(RESULT_OK);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    public void addfood(String store, String saram, String hour, String minute, String money, Integer delivery){

        food food = new food(store, saram, hour, minute, money, delivery);

        databaseReference.child("food").push().setValue(food);
    }
}
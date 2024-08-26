package com.example.myproject2022_05_06;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class listActivity extends AppCompatActivity {

    Button btnAdd;
    Integer delivery;
    String gage, time1, time2, don, people;
    private static final int REQUEST_CODE = 000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        final ArrayList<String> items = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        final ListView listview = (ListView) findViewById(R.id.listview1);

        listview.setVisibility(View.INVISIBLE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RoomCreateActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference databaseReference = database.getReference();

                final ListView listview = (ListView) findViewById(R.id.listview1);
                final ArrayList<String> items = new ArrayList<String>();
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
                listview.setAdapter(adapter);

                databaseReference.child("food").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        food group = snapshot.getValue(food.class);

                        assert group != null;
                        gage = group.getStore();
                        people = group.getSaram();
                        time1 = group.getHour();
                        time2 = group.getMinute();
                        don = group.getMoney();
                        delivery = group.getDelivery();

                        items.add(gage + "     /     " + people + "명     /     " + time1 + "시   " +
                                time2 + "분     /     " + don + "원\n\n장소 : 상명대학교 정문");

                        listview.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        if(gage.equals("한식집")){
                            Intent intent = new Intent(listActivity.this, MenuSelect1Activity.class);
                            startActivity(intent);
                        }
                        if(gage.equals("중식집")){
                            Intent intent = new Intent(listActivity.this, MenuSelect2Activity.class);
                            startActivity(intent);
                        }
                        if(gage.equals("일식집")){
                            Intent intent = new Intent(listActivity.this, MenuSelect3Activity.class);
                            startActivity(intent);
                        }
                        if(gage.equals("양식집")){
                            Intent intent = new Intent(listActivity.this, MenuSelect4Activity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
            if(resultCode == RESULT_CANCELED){

                final ArrayList<String> items = new ArrayList<String>();
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
                final ListView listview = (ListView) findViewById(R.id.listview1);
                listview.setVisibility(View.INVISIBLE);
            }
        }
    }
}
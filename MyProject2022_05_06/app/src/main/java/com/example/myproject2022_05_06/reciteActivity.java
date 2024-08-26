//영수증 엑티비티
package com.example.myproject2022_05_06;

import static java.lang.Integer.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class reciteActivity extends AppCompatActivity {

    Button yes, no;
    TextView name, menu, don, qoekf;
    String store, totalmenu;
    Integer delivery;
    String money, people;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        name = findViewById(R.id.name);
        menu = findViewById(R.id.total_menu);
        don = findViewById(R.id.total_price);
        qoekf = findViewById(R.id.delivery);

        Intent intent = getIntent();
        store = intent.getStringExtra("name");
        totalmenu = intent.getStringExtra("it_check");
        money = intent.getStringExtra("don");

        databaseReference.child("food").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                food group = snapshot.getValue(food.class);
                people = group.getSaram();
                delivery = group.getDelivery();

                name.setText(store);
                menu.setText(totalmenu);
                don.setText(money);
                qoekf.setText("배달비 : " + Integer.toString(delivery) + "원");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reciteActivity.this, listActivity.class);
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
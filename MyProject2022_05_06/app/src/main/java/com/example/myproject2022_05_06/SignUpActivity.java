package com.example.myproject2022_05_06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    EditText mEmailText, mPasswordText, mPasswordcheckText, mName;
    Button mregisterBtn,nickCheckButton,pwCheckButton,cancleButton;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //파이어베이스 접근 설정
        firebaseAuth =  FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        mEmailText = findViewById(R.id.signID);
        mPasswordText = findViewById(R.id.signPW);
        mPasswordcheckText = findViewById(R.id.signPW2);
        mregisterBtn = findViewById(R.id.signButton);
        mName = findViewById(R.id.nickName);
        nickCheckButton = findViewById(R.id.nickCheckButton);
        pwCheckButton = findViewById(R.id.pwCheckButton);
        cancleButton = findViewById(R.id.cancelButton);

        nickCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= user.getEmail();
                if(mEmailText.getText().toString().equals(email)){
                    Toast.makeText(SignUpActivity.this, "이메일 중복", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUpActivity.this, "사용 가능한 이메일", Toast.LENGTH_SHORT).show();
                }

            }
        });
        pwCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail();
                if(mPasswordText.getText().toString().equals(mPasswordcheckText.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "비밀번호가 확인되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //가입버튼 클릭리스너   -->  firebase에 데이터를 저장한다.
        mregisterBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //가입 정보 가져오기
                final String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();

                //파이어베이스에 신규계정 등록하기
                firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //가입 성공시
                        if (task.isSuccessful()) {
                            Log.d(TAG, "등록 버튼 " + email + " , " + pwd);
                            final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                            mDialog.setMessage("가입중입니다...");
                            mDialog.show();

                            mDialog.dismiss();

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();
                            String name = mName.getText().toString().trim();

                            //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                            HashMap<Object,String> hashMap = new HashMap<>();

                            hashMap.put("uid",uid);
                            hashMap.put("email",email);
                            hashMap.put("name",name);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);


                            //가입이 이루어져을시 가입 화면을 빠져나감.
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "회원가입에 실패하셨습니다.(다시해주십시오)", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                });
            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
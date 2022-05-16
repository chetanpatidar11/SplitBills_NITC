package com.example.Splitbills_nitc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class login extends AppCompatActivity {
    public Button login2;
    EditText email;
    EditText password;
    TextView forget;
    TextView signup;
    @Override
    public void onStart() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent( login.this ,MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        //do nothing
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login2=findViewById(R.id.login2);
        email=findViewById(R.id.loginName);
        password=findViewById(R.id.LoginPassword);
        forget=findViewById(R.id.textView6);
        signup=findViewById(R.id.textView5);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( login.this ,forgetpassword.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( login.this ,Register.class);
                startActivity(intent);
            }
        });
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String emailSender=email.getText().toString();
                String passwordSender=password.getText().toString();
                if(TextUtils.isEmpty(emailSender)){
                    email.setError("Enter email");
                    email.requestFocus();
                }else if(TextUtils.isEmpty(passwordSender)){
                    password.setError("Enter password");
                    password.requestFocus();
                }else{
                    mAuth.signInWithEmailAndPassword(emailSender, passwordSender).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent=new Intent( login.this ,MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(login.this,"Login success",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(login.this,"Wrong email or password ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });
    }
}
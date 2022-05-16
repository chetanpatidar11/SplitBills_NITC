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

public class Register extends AppCompatActivity {
    EditText email;
    EditText password;
    TextView button;
    public Button button2;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button2 =findViewById(R.id.button2);
        button=findViewById(R.id.textView2);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( Register.this ,login.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                String emailSender=email.getText().toString();
                String passwordSender=password.getText().toString();
                if(TextUtils.isEmpty(emailSender)){
                    email.setError("Enter email");
                    email.requestFocus();
                }else if(TextUtils.isEmpty(passwordSender)){
                    password.setError("Enter password");
                    password.requestFocus();
                }else{
                    mAuth.createUserWithEmailAndPassword(emailSender,passwordSender).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent=new Intent( Register.this ,login.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}
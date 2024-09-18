package com.hoperaiser.ashok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button btnlogin;
    ProgressDialog pd;


    //Firebase

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.edtLoginMobile);
        password=(EditText)findViewById(R.id.edtLoginPassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        pd = new ProgressDialog(this);
        pd.setMessage("Verifying....");


        //Firebase
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        //login

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_str=email.getText().toString();
                String pass_str=password.getText().toString();
                if(email_str.matches("")&& pass_str.matches("")) {

                    Toast.makeText(LoginActivity.this, " Kindly fill All the field to login", Toast.LENGTH_SHORT).show();
                }else {
                    pd.show();

                    mAuth.signInWithEmailAndPassword(email_str, pass_str)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        pd.dismiss();
                                        Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }


            }
        });

//Exisiting User

        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user1=firebaseAuth.getCurrentUser();
                if(user1==null){

                }else{

                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        };


        if(mUser!=null){
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

    }
}
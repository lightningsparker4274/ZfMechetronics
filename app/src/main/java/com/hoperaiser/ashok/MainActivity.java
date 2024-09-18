package com.hoperaiser.ashok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    CardView card1;
    ImageView logout;
    CardView card2;
    //Firebase

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1=(CardView)findViewById(R.id.maincardview);
        logout=(ImageView)findViewById(R.id.logout);
        //card 2
        card2=(CardView)findViewById(R.id.maincardview2);

        //Firebase
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        //logout

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAuth.signOut();
                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });





        //card1
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TroubleshootActivity.class);
                startActivity(i);
                finish();

            }
        });
        //card2
                card2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(getApplicationContext(), com.hoperaiser.ashok.SparepartActivity.class);
                        startActivity(i);
                        finish();

                    }
                }
        );

    }
}
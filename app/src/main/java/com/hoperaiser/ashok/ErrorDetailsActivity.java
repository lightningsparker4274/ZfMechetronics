package com.hoperaiser.ashok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

    public class ErrorDetailsActivity extends AppCompatActivity {


    ImageView back;
    String id,error_Code,error_Des,error_Exp,error_Rem,error_Can;

    TextView ec,ed,ee,er,ecan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_details);

        ec=findViewById(R.id.error_code);
        ed=findViewById(R.id.error_des);
        ee=findViewById(R.id.error_exp);
        er=findViewById(R.id.error_rem);
        ecan=findViewById(R.id.error_can);
        back=(ImageView)findViewById(R.id.menu);



        //back

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TroubleshootActivity.class);
                startActivity(i);
                finish();
            }
        });

        Intent intent=getIntent();
        id=intent.getExtras().getString("id");
        error_Code=intent.getExtras().getString("error");
        error_Des=intent.getExtras().getString("desc");
        error_Exp=intent.getExtras().getString("exp");
        error_Rem=intent.getExtras().getString("rem");
        error_Can=intent.getExtras().getString("msg");

        ec.setText(error_Code);
        ed.setText(error_Des);
        ee.setText(error_Exp);
        er.setText(error_Exp);
        ecan.setText(error_Can);
    }
}
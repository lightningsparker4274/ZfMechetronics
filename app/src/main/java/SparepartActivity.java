package com.hoperaiser.ashok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class SparepartActivity extends AppCompatActivity implements SelectListner  {



    private static final String HI = "https://script.google.com/macros/s/AKfycby_Yuul6929rHJlVTi-ZePedg6bAsVkaOf1WKmCGiSnI3jopc0wMvsHLzWFy00sJXY/exec";


    private RecyclerView rv;
    private List<UserModal> list_data;
    private MyAdapter adapter;
    String error_code,error_des,explanation,remedy,message_can,Id;
    EditText searchview;
    private ProgressDialog pDialog;
    ImageView i1;
    ImageView refersh;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detials);


        i1 = findViewById(R.id.menu);
        searchview =(EditText) findViewById(R.id.searchview);
        refersh=(ImageView)findViewById(R.id.refersh);


        rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


        if (list_data == null)
            list_data = new ArrayList<>();
        adapter = new MyAdapter(list_data, this,this);
        pDialog = new ProgressDialog(SparepartActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);



        //referesh
        refersh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list_data.clear();
                getData();


            }
        });


//back

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        getData();

        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString().trim());
            }


        });

    }

    //filter
    private void filter(String toString) {
        List<UserModal> filteredlist = new ArrayList<>();
        for (UserModal news : list_data) {
            if (news.getError_code().toLowerCase().contains(toString.toLowerCase())) {
                filteredlist.add(news);

            }else {

            }

        }
        adapter.filterlist(filteredlist);
    }


    @Override
    public void onItemClicked(UserModal userModal) {
        Intent i=new Intent(getApplicationContext(),ErrorDetailsActivity.class);
        i.putExtra("id",userModal.getId());
        i.putExtra("error",userModal.getError_code());
        i.putExtra("desc",userModal.getError_description());
        i.putExtra("exp",userModal.getExplanation());
        i.putExtra("rem",userModal.getRemedy());
        i.putExtra("msg",userModal.getMessage_cancel());


        startActivity(i);

    }


    @Override
    public void onBackPressed () {

        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);


    }

    // Fetch Data

    private void getData() {
        pDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    pDialog.show();

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("user");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject ob = array.getJSONObject(i);
                        Id = ob.getString("ID");
                        error_code = ob.getString("Error_Code");
                        error_des = ob.getString("Error_Desciption");
                        explanation = ob.getString("Explanation");
                        remedy=ob.getString("Remedy");
                        message_can=ob.getString("Message_Cancel");


                        if(Id.equals("ID")){

//
                        }else {
                            UserModal ld = new UserModal(Id,error_code,error_des,explanation,remedy,message_can);

                            list_data.add(ld);

                        }
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
//
                rv.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                pDialog.dismiss();
                if (pDialog.isShowing())
                    pDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
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

public abstract class StockActivity extends AppCompatActivity implements SelectListener1  {



    private static final String HI = "https://script.googleusercontent.com/macros/echo?user_content_key=x3MfQua84Bg3fUIwQr4MJ5SYxQYRwTvuqK3gF_DtR2JAmwcWMmVibgdn0hCVlUFapOC6pN5u0m67O707yhvZalGZzOo_A98Gm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnIftCzv99q3at0b6XC1e0ZK2_MtoUumXKjXFcgHFOSLSNyDS3QITIc6ZmwQXo2YlyymnZ-2uAwJ_IpCCFnIxGm3DyjqP4SqZzQ&lib=MogiBReUR_hKz63XTHH0SIx6HlHeBI2fu";


    private RecyclerView rv;
    private List<com.hoperaiser.ashok.UserModels> list_data;
    private com.hoperaiser.ashok.MyAdap adapter;
    String Material_No,Description,Mfr_Part_Nr,Storage_Bin,Stock_Avlbl;
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
        adapter = new com.hoperaiser.ashok.MyAdap(list_data, this,this);
        pDialog = new ProgressDialog(StockActivity.this);
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
        List<com.hoperaiser.ashok.UserModels> filteredlist = new ArrayList<>();
        for (com.hoperaiser.ashok.UserModels news : list_data) {
            if (news.getMaterial_No().toLowerCase().contains(toString.toLowerCase())) {
                filteredlist.add(news);

            }else {

            }

        }
        adapter.filterlist(filteredlist);
    }


    @Override
    public void onItemClicked(com.hoperaiser.ashok.UserModels user) {
        Intent i=new Intent(getApplicationContext(),StockActivity.class);
        i.putExtra("Material_No",user.getMaterial_No());
        i.putExtra("Description",user.getDescription());
        i.putExtra("Mfr-Part_Nr",user.getMfr_Part_Nr());
        i.putExtra("Storage_Bin",user.getStorage_Bin());
        i.putExtra("Stock_Avlbl",user.getStock_Avlbl());
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
                        Material_No = ob.getString("Material_No");
                        Description = ob.getString("Description");
                        Mfr_Part_Nr = ob.getString(" Mfr-Part_Nr");
                        Storage_Bin = ob.getString("Storage_Bin");
                        Stock_Avlbl =ob.getString("Stock_Avlbl");


                        if(Material_No.equals("Material_No")){

//
                        }else {
                            com.hoperaiser.ashok.UserModels Id = new com.hoperaiser.ashok.UserModels(Material_No,Description,Mfr_Part_Nr,Storage_Bin,Stock_Avlbl);

                            list_data.add(Id);

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
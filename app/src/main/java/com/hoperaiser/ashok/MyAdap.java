package com.hoperaiser.ashok;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdap extends RecyclerView.Adapter<MyAdap.ViewHolder> {
    private static List<UserModels> list_data;
    private Context context;
    List<UserModels> newlist;
    SelectListener1 listner;
    public MyAdap(List<UserModels> list_data, Context context,SelectListener1 listner) {
        this.list_data =list_data;
        this.context = context;
        this.newlist=list_data;
        this.listner=listner;
//        this.list_data=new ArrayList<>(newlist);

    }


    public void filterlist(List<UserModels> filteredlist){
        list_data = filteredlist;
        if (list_data.isEmpty())
        {
            Toast.makeText(context, "No Result Matching Your Search....", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModels listData=list_data.get(position);
        holder.txtname.setText(listData.Material_No);

        holder.id.setText(listData.Description);
        holder.maincardview.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_down));
        holder.maincardview.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {listner.onItemClicked(listData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtname,id,distance;
        private CardView maincardview;
        public ViewHolder(View itemView) {
            super(itemView);
//            img=(ImageView)itemView.findViewById(R.id.image_view);
            txtname=(TextView)itemView.findViewById(R.id.idTVFirstName);
            id=(TextView)itemView.findViewById(R.id.idTVLastName);
            distance=(TextView)itemView.findViewById(R.id.idTVEmail);
            maincardview=(CardView)itemView.findViewById(R.id.maincardview);
        }
    }
    public MyAdap(List<UserModels> list_data) {
        this.list_data=list_data;
    }
}
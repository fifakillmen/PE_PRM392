package com.example.pe_prm392.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pe_prm392.Model.User;
import com.example.pe_prm392.R;
import com.example.pe_prm392.UserDetailActivity;
import com.google.gson.Gson;

import java.util.List;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.ViewHolder> {

    List<User> users;
    private LayoutInflater layoutInflater;
    private Context context;

    public ResponseAdapter(List<User> users, Context context) {
        this.users = users;
        this.layoutInflater = LayoutInflater.from(context);
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtvName,txtvEmail;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            txtvName= itemView.findViewById(R.id.txt_name);
            txtvEmail= itemView.findViewById(R.id.txt_email);
            avatar= itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    User user = users.get(position);
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra("user",new Gson().toJson(user));
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view=  layoutInflater.inflate(R.layout.custom_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user= users.get(position);
        String name= user.getFirst_name()+" "+user.getLast_name();
        String email= user.getEmail();
        holder.txtvName.setText(name);
        holder.txtvEmail.setText(email);
        Glide.with(context).load(user.getAvatar()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}

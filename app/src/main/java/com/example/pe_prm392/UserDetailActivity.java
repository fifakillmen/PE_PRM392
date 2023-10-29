package com.example.pe_prm392;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pe_prm392.Model.User;
import com.google.gson.Gson;

public class UserDetailActivity extends AppCompatActivity {
TextView txtv_id,txtv_email,txtv_firstName,txtv_lastName,txtv_avatar;
ImageView imgv_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        String s = (String) getIntent().getSerializableExtra("user");
        User user= new Gson().fromJson(s,User.class);
        txtv_id=findViewById(R.id.txtv_id);
        txtv_email=findViewById(R.id.txtv_email);
        txtv_firstName=findViewById(R.id.txtv_firstName);
        txtv_lastName=findViewById(R.id.txtv_lastName);
        txtv_avatar=findViewById(R.id.txtv_avatar);
        imgv_avatar=findViewById(R.id.imgv_avatar);

        Glide.with(this).load(user.getAvatar()).into(imgv_avatar);


        txtv_id.setText(user.getId());
        txtv_email.setText(user.getEmail());
        txtv_firstName.setText(user.getFirst_name());
        txtv_lastName.setText(user.getLast_name());
        txtv_avatar.setText(user.getAvatar());
    }
}
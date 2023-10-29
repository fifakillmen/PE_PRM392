package com.example.pe_prm392;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.pe_prm392.Model.ResponseModel;
import com.example.pe_prm392.Model.User;
import com.example.pe_prm392.adapter.ResponseAdapter;
import com.example.pe_prm392.configuration.RetrofitServiceConfiguration;
import com.example.pe_prm392.service.ResponseModelService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_view);
        users= new ArrayList<>();
        initializeComponents();

    }

    private void initializeComponents() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RetrofitServiceConfiguration retrofitServiceConfiguration= new RetrofitServiceConfiguration();
        ResponseModelService service= retrofitServiceConfiguration.getRetrofit().create(ResponseModelService.class);


        // spin config
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterSpin=ArrayAdapter.createFromResource(this,R.array.pages, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapterSpin.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpin);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getData(service,Integer.parseInt(parent.getItemAtPosition(position).toString()));

                Log.w("check onItemselected",position+" "+id+" "+Integer.parseInt(parent.getItemAtPosition(position).toString()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // search config
        SearchView searchView= findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               setAdapter(filterUser(query));
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                setAdapter(filterUser(newText));
                return true;
            }
        });

    }
    private void setAdapter(List<User> users){
            ResponseAdapter adapter = new ResponseAdapter(users,MainActivity.this);
            recyclerView.setAdapter(adapter);
    }
    private void getData( ResponseModelService service,int page){
        service.getUsers(page).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel res= response.body();
                users=res.getData();
                setAdapter(users);
                Log.w("data",users.toString());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.w("error at getData","loi o private void getData");
            }
        });

    }
    private List<User> filterUser(String searchText){
        List<User> userfilter= new ArrayList<>();
        for (User u:users) {
            if (u.getEmail().contains(searchText)
                    ||u.getFirst_name().contains(searchText)
                    ||u.getLast_name().contains(searchText)){
                userfilter.add(u);
            }
        }
        return userfilter;
    }
}
package com.example.dsljsontesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private List<Data> listOfItems= null;
    private RecyclerView recyclerView ;
    private MyAdapter myAdapter;
    private Button button;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this::onButtonClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onButtonClickListener(View view) {
        getRetrofitData();
    }

    private void getRetrofitData() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit builder = new Retrofit.Builder()
                .addConverterFactory(DslConverterFactory.create())
                .client(client)
                .baseUrl("http://172.16.95.147:3000/")
                .build()
                ;
        builder.create(WebService.class).getData().enqueue(new Callback<GetData>() {
            @Override
            public void onResponse(Call<GetData> call, Response<GetData> response) {
                listOfItems = response.body().getListData();
                setRecyclerView();
            }

            @Override
            public void onFailure(Call<GetData> call, Throwable t) {

            }
        });
    }

    private void setRecyclerView() {
        Toast.makeText(MainActivity.this,"Toast",Toast.LENGTH_LONG).show();
        myAdapter = new MyAdapter(listOfItems,this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
package com.creanal.lagosjavadevelopers.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.creanal.lagosjavadevelopers.R;
import com.creanal.lagosjavadevelopers.api.Client;
import com.creanal.lagosjavadevelopers.api.Service;
import com.creanal.lagosjavadevelopers.itemAdapter;
import com.creanal.lagosjavadevelopers.model.item;
import com.creanal.lagosjavadevelopers.model.itemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView Disconnected;
    private item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swiper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swiper=(SwipeRefreshLayout)findViewById(R.id.swiper);
        swiper.setColorSchemeResources(android.R.color.holo_green_dark);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(MainActivity.this,"Users Refreshed",Toast.LENGTH_SHORT).show();
            }
                                    }
        );

    }
    private void initViews(){
        pd= new ProgressDialog(this);
        pd.setMessage("loading Github Users");
        pd.setCancelable(false);
        pd.show();
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();

    }
    private void loadJSON(){
        Disconnected = (TextView)findViewById(R.id.disconnected);
        try{
            Client client = new Client();
            Service apiServive = Client.getClient().create(Service.class);
            Call<itemResponse> call =apiServive.getItems();
            call.enqueue(new Callback<itemResponse>() {
                @Override
                public void onResponse(Call<itemResponse> call, Response<itemResponse> response) {
                    List<item> items = response.body().getItems();
                    recyclerView.setAdapter(new itemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swiper.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<itemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Occured while loading Java developers", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}











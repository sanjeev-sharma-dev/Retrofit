package com.mobilityrocks.retrofit.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobilityrocks.retrofit.R;
import com.mobilityrocks.retrofit.adapters.CustomAdapter;
import com.mobilityrocks.retrofit.interfaces.MainInterface;
import com.mobilityrocks.retrofit.pojos.Model;
import com.mobilityrocks.retrofit.retrofit.RetroClass;
import com.mobilityrocks.retrofit.utils.ApiURLs;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements  MainInterface.onApiResponse {

    RecyclerView recyclerView;
    CustomAdapter mAdapter;

    public static final String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * here is create the object of retroClass and pass the interfaces
         */

        new RetroClass(this, this);


        recyclerView=findViewById(R.id.recycler_view);
        mAdapter = new CustomAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        /**
         * here we get the retrofit class object and pass base url
         * and call the api interface
         * and pass the code(any integer value) to get the response of this type
         */

        MainInterface.ApiInterface api = RetroClass.getRetrofit(ApiURLs.url).create(MainInterface.ApiInterface.class);
        Call<List<Model>> call = api.getJsonArrayData();
        RetroClass.callRetrofit(call, 1, this);
    }

    @Override
    public void response(List<Model> models) {
        Log.d(TAG,"onSuccessResponse"+models.size());
        mAdapter.updateList(models);
    }
}

package com.mobilityrocks.retrofit.interfaces;

import com.mobilityrocks.retrofit.pojos.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainInterface {

     interface ApiInterface<T>  {

        @GET("android/downloadcode/arrayfile.json")
        Call<List<Model>> getJsonArrayData();

    }

     interface onApiResponse{
        void response(List<Model> models);
    }

}

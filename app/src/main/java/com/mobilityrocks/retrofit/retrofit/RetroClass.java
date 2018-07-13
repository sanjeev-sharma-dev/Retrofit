package com.mobilityrocks.retrofit.retrofit;

import android.content.Context;
import android.util.Log;

import com.mobilityrocks.retrofit.interfaces.MainInterface;
import com.mobilityrocks.retrofit.pojos.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass<T> {

    public static  final String TAG=RetroClass.class.getSimpleName();
    private T t;

    private static MainInterface.onApiResponse responseOfApi;

    public RetroClass(T t,MainInterface.onApiResponse responseOfApi) {
        this.t = t;
        this.responseOfApi=responseOfApi;

    }

    /**
     * this is used to create a retrofit class object which is known as singlton class that is shared by all other classes to hit the api.
     * @param url you have to pass base url of api
     * @return it will retrun the retrofit object.
     */
    public static Retrofit getRetrofit(String url){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    /**
     * This method is used to get the response(T type) as list of Object type
     * @param call is used to override the retrofit method to get the response
     * @param i is used to get the code in which type of data you wnat to get and set
     * @param context is used to get the Activity reference
     * @param <T> used to get the response as a list of Object type which is easily convert to any type
     */
    public static <T> void callRetrofit(Call<List<T>> call, final int i, final Context context) {

            call.enqueue(new Callback<List<T>>() {
                @Override
                public void onResponse(Call<List<T>> call, Response<List<T>> response) {

                    if(i==1)
                    {
                        List<Model> model= (List<Model>) response.body();
                        responseOfApi.response(model);

                    }

                }
                @Override
                public void onFailure(Call<List<T>> call, Throwable t) {
                    Log.d(TAG,"onFailure"+t.getMessage());
                }
            });

    }

}

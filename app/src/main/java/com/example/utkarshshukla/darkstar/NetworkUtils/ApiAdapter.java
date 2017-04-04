package com.example.utkarshshukla.darkstar.NetworkUtils;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiAdapter {

    public static <A> A createRestAdapter(Class<A> AdapterClass,String baseUrl, Context context){

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(Client.getClientInstance(context))
                .addConverterFactory(GsonConverterFactory.create ())
                .build();

        return restAdapter.create(AdapterClass);


    }


}

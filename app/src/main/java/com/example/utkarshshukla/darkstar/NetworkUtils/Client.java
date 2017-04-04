package com.example.utkarshshukla.darkstar.NetworkUtils;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class Client {

    private static volatile OkHttpClient mClient;


    private Client ( ) {
        mClient = new OkHttpClient ( );
    }

    public static OkHttpClient getClientInstance ( final Context context ) {
        final int cacheSize = 30 * 1024 * 1024; //30MB

        //creating log Interceptor

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor ( );
        logging.setLevel ( HttpLoggingInterceptor.Level.BODY );

        if ( mClient == null ) {
            synchronized ( Client.class ) {
                if ( mClient == null ) {
                    mClient = new OkHttpClient ( ).newBuilder ( )
                            .cache ( new Cache ( context.getApplicationContext ( ).getCacheDir ( ), cacheSize ) )
                            .readTimeout ( 60, TimeUnit.SECONDS )
                            .connectTimeout ( 60, TimeUnit.SECONDS )
                            .addInterceptor ( logging )
                            .build ( );

                }
            }
        }
        return mClient;
    }


}

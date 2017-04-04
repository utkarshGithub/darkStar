package com.example.utkarshshukla.darkstar.Receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.example.utkarshshukla.darkstar.Standard.StandardMethods;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */
public class NetworkChangeReceiver extends WakefulBroadcastReceiver {


    private String INTERNET_FILTER = "internet_connectivity_check";

    @Override
    public void onReceive ( final Context context, final Intent intent ) {
        if ( StandardMethods.isOnline ( context ) ) {
            Intent intent1 = new Intent ( INTERNET_FILTER );
            context.sendBroadcast ( intent1 );
        }

    }
}


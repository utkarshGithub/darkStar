package com.example.utkarshshukla.darkstar.Standard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */

public class StandardMethods {
    public static boolean isOnline ( Context context ) {

        ConnectivityManager cm = ( ConnectivityManager ) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
        NetworkInfo netInfo = cm.getActiveNetworkInfo ( );
        //should check null because in airplane mode it will be null
        return ( netInfo != null && netInfo.isConnected ( ) );
    }
}

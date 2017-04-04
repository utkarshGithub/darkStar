package com.example.utkarshshukla.darkstar.NetworkUtils;

import com.example.utkarshshukla.darkstar.RetrofitPOJO.ResponsePOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */

public interface EndPoint {

    @POST ( "api/chat/" )
    Call< ResponsePOJO > sendMessage ( @Query ( value = "apiKey", encoded = true ) String apiKey,
                                      @Query ( value = "message", encoded = true ) String message,
                                      @Query ( value = "chatBotID", encoded = true ) Integer chatBotID,
                                      @Query ( value = "externalID", encoded = true ) String externalID );

    @GET ( "api/chat/" )
    Call< ResponsePOJO > receiveMessage ( @Query ( value = "apiKey", encoded = true ) String apiKey,
                                      @Query ( value = "message", encoded = true ) String message,
                                      @Query ( value = "chatBotID", encoded = true ) Integer chatBotID,
                                      @Query ( value = "externalID", encoded = true ) String externalID );

}

package com.example.utkarshshukla.darkstar.Main;

import com.activeandroid.query.Select;
import com.example.utkarshshukla.darkstar.DatabaseUtils.MessageTable;
import com.example.utkarshshukla.darkstar.NetworkUtils.ApiAdapter;
import com.example.utkarshshukla.darkstar.NetworkUtils.EndPoint;
import com.example.utkarshshukla.darkstar.RetrofitPOJO.ResponsePOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Utkarsh Shukla on 03/04/17.
 */

public class MainHelper {
    String baseUrl = "http://www.personalityforge.com/";
    String apiKey = "6nt5d1nJHkqbkphe";
    Integer chatBotID = 63906;
    String externalID = "chirag1";

    public void sendMessage ( final String messsage, final MainActivity mainActivity, final MainController mainController, final String timeStamp ) {

        ApiAdapter.createRestAdapter ( EndPoint.class, baseUrl, mainActivity ).
                sendMessage ( apiKey, messsage, chatBotID, externalID ).enqueue ( new Callback< ResponsePOJO > ( ) {
            @Override
            public void onResponse ( Call< ResponsePOJO > call, Response< ResponsePOJO > response ) {
                if ( response.body ( ).getSuccess ( ) == 1 ) {
                    callForReceiveMessage ( messsage, mainActivity, mainController );
                    setDeliveryStatusToDeleivered ( timeStamp );
                }
            }

            @Override
            public void onFailure ( Call< ResponsePOJO > call, Throwable t ) {

            }
        } );
    }

    private void setDeliveryStatusToDeleivered ( String timeStamp ) {
        MessageTable messageTable = new Select ( ).from ( MessageTable.class ).
                where ( "messagefrom = ?", "doc" ).and ( "messageto = ?", "bot" ).and ( "messagetimeStamp = ?", timeStamp ).executeSingle ( );
        if ( messageTable != null ) {
            messageTable.isDelivered = "1";
            messageTable.save ( );
        }

    }

    private void callForReceiveMessage ( final String messsage, MainActivity mainActivity, final MainController mainController ) {
        ApiAdapter.createRestAdapter ( EndPoint.class, baseUrl, mainActivity ).
                receiveMessage ( apiKey, messsage, chatBotID, externalID ).enqueue ( new Callback< ResponsePOJO > ( ) {
            @Override
            public void onResponse ( Call< ResponsePOJO > call, Response< ResponsePOJO > response ) {
                if ( response.body ( ).getSuccess ( ) == 1 ) {

                    mainController.addReciveMessageToList ( messsage );
                }
            }

            @Override
            public void onFailure ( Call< ResponsePOJO > call, Throwable t ) {

            }
        } );
    }
}

package com.example.utkarshshukla.darkstar.Main;

import android.os.Handler;
import android.os.Looper;

import com.activeandroid.query.Select;
import com.example.utkarshshukla.darkstar.DatabaseUtils.MessageTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utkarsh Shukla on 03/04/17.
 */

public class MainController {
    private final MainHelper mainHelper;
    private final MainModel mainModel;
    private final MainActivity mainActivity;
    private final ArrayList< ChatMessage > messageList;
    private final ChatAdpter chatAdater;

    public MainController ( MainActivity mainActivity, MainHelper mainHelper, MainModel mainModel,
                            ArrayList< ChatMessage > messageList, ChatAdpter chatAdater ) {
        this.mainHelper = mainHelper;
        this.mainModel = mainModel;
        this.mainActivity = mainActivity;
        this.messageList = messageList;
        this.chatAdater = chatAdater;
    }

    public void addSentMessagettoList ( ArrayList< ChatMessage > messageList, String messsage, String timeStamp ) {

        mainHelper.sendMessage ( messsage, mainActivity, this, timeStamp );
        mainModel.addSentMessageToDb ( messsage, mainActivity, this, timeStamp );

    }

    public void addReciveMessageToList ( final String messsage ) {
        mainModel.addReceiveMessageToTable ( messsage );
        new Handler ( Looper.getMainLooper ( ) ).post ( new Runnable ( ) {
            @Override
            public void run ( ) {
                messageList.add ( new ChatMessage ( messsage, "", System.currentTimeMillis ( ) + "", "doc", "bot" ) );
                chatAdater.notifyDataSetChanged ( );

            }
        } );
    }

    public void onResume ( ) {
        new Handler ( Looper.getMainLooper ( ) ).post ( new Runnable ( ) {
            @Override
            public void run ( ) {
                messageList.clear ( );
                chatAdater.notifyDataSetChanged ( );
                List< MessageTable > messageTableList = new Select ( ).from ( MessageTable.class )
                        .orderBy ( "messagetimeStamp ASC" ).execute ( );
                for ( MessageTable dataBaseMessageList : messageTableList ) {
                    String status;
                    if ( dataBaseMessageList.isDelivered.equals ( "1" ) ) {
                        status = "sent";
                    } else {
                        status = "not sent";
                    }
                    messageList.add ( new ChatMessage ( dataBaseMessageList.messageBody, status,
                            dataBaseMessageList.messagetimeStamp, dataBaseMessageList.messageto, dataBaseMessageList.messagefrom ) );
                    chatAdater.notifyDataSetChanged ( );
                    if ( dataBaseMessageList.isDelivered.equals ( "0" ) ) {
                        String timeStamp = System.currentTimeMillis ( ) + "";
                        mainHelper.sendMessage ( dataBaseMessageList.messageBody, mainActivity, MainController.this, timeStamp );
                    }
                }
            }
        } );
    }
}

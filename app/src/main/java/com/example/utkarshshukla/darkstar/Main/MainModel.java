package com.example.utkarshshukla.darkstar.Main;

import com.example.utkarshshukla.darkstar.DatabaseUtils.MessageTable;

/**
 * Created by Utkarsh Shukla on 03/04/17.
 */

public class MainModel {
    public void addSentMessageToDb ( String messsage, MainActivity mainActivity, MainController mainController, String timeStamp ) {
        MessageTable messageTable = new MessageTable ( );
        messageTable.messagefrom = "doc";
        messageTable.messageto = "bot";
        messageTable.isDelivered = "0";
        messageTable.messagetimeStamp = timeStamp;
        messageTable.messageBody = messsage;
        messageTable.save ( );

    }

    public void addReceiveMessageToTable ( String messsage ) {
        MessageTable messageTable = new MessageTable ( );
        messageTable.messagefrom = "bot";
        messageTable.messageto = "doc";
        messageTable.isDelivered = "1";
        messageTable.messagetimeStamp = System.currentTimeMillis ( ) + "";
        messageTable.messageBody = messsage;
        messageTable.save ( );
    }
}

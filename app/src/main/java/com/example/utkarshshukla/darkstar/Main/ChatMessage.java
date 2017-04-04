package com.example.utkarshshukla.darkstar.Main;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */

class ChatMessage {
    public final String status;
    public final String message;
    public final String time;
    public final String to;
    public final String from;


    public ChatMessage ( String message, String status, String time, String to, String from ) {
        this.message = message;
        this.status = status;
        this.time = time;
        this.to = to;
        this.from = from;
    }
}

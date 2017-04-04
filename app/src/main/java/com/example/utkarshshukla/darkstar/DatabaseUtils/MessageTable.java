package com.example.utkarshshukla.darkstar.DatabaseUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */
@Table ( name =  "MessageTable")
public class MessageTable extends Model {
    @Column(name = "isDelivered")
    public String isDelivered;
    @Column (name = "messageBody")
    public  String messageBody;
    @Column (name = "messagefrom")
    public  String messagefrom;
    @Column (name  = "messageto")
    public  String messageto;
    @Column (name = "messagetimeStamp")
    public  String messagetimeStamp;
}

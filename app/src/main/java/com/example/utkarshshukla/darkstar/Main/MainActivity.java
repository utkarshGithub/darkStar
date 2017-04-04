package com.example.utkarshshukla.darkstar.Main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.utkarshshukla.darkstar.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView ( R.id.chat_msg_list )
    RecyclerView recyclerView;
    @BindView ( R.id.btn_sendmsg )
    ImageButton sendButton;
    @BindView ( R.id.edit_sendmsg )
    EditText editSendMessage;


    private MainController mainController;
    private ArrayList< ChatMessage > messageList;
    private ChatAdpter chatAdater;
    private String TAG = getClass ( ).getName ( );

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        ButterKnife.bind ( this );
        sendButton.setOnClickListener ( this );
        messageList = new ArrayList< ChatMessage>() ;
        chatAdater = new ChatAdpter ( messageList, this );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager ( this );
        linearLayoutManager.setOrientation ( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager ( linearLayoutManager );
        recyclerView.setAdapter ( chatAdater );
        mainController = new MainController ( this, new MainHelper ( ), new MainModel ( ), messageList, chatAdater );
    }

    @Override
    public void onClick ( View v ) {
        int id = v.getId ( );
        switch ( id ) {
            case ( R.id.btn_sendmsg ):
                final String messsage = editSendMessage.getEditableText ( ).toString ( );
                final String time = System.currentTimeMillis ()+"";
                editSendMessage.getText ( ).clear ( );
                if ( !messsage.equalsIgnoreCase ( "" ) ) {
                    new Handler ( Looper.getMainLooper ( ) ).post ( new Runnable ( ) {
                        @Override
                        public void run ( ) {

                            messageList.add ( new ChatMessage ( messsage,"sent",time,"bot","doc" ) );
                            chatAdater.notifyDataSetChanged ( );
                            mainController.addSentMessagettoList ( messageList, messsage,time );
                        }
                    } );
                }
        }
    }

    @Override
    protected void onResume ( ) {
        super.onResume ( );
        mainController.onResume();
    }
}

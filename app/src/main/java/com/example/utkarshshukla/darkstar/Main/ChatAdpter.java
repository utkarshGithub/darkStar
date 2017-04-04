package com.example.utkarshshukla.darkstar.Main;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utkarshshukla.darkstar.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Utkarsh Shukla on 04/04/17.
 */

class ChatAdpter extends RecyclerView.Adapter< ChatAdpter.MessageViewHolder > {
    private final ArrayList< ChatMessage > messageList;
    private final MainActivity mainActivity;
    private int SEND = 0;
    private int RECEIVE = 1;

    public ChatAdpter ( ArrayList< ChatMessage > messageList, MainActivity mainActivity ) {
        this.messageList = messageList;
        this.mainActivity = mainActivity;
    }

    @Override
    public MessageViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.chat_message_layout, parent, false );
        return new MessageViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( MessageViewHolder holder, int position ) {
        holder.chatMessage.setText ( messageList.get ( position ).message );
        if (  getItemViewType ( position ) == SEND ) {
            holder.chatLayout.setGravity ( Gravity.RIGHT );
            holder.chatMessage.setBackgroundResource ( R.drawable.blue_chat_bubble );
        } else {
            holder.chatLayout.setGravity ( Gravity.LEFT );
            holder.chatMessage.setBackgroundResource ( R.drawable.grey_chat_bubble );
        }

    }

    @Override
    public int getItemViewType ( int position ) {
        if ( messageList.get ( position ).from.equals ( "doc" ) ) {
            return SEND;

        } else {
            return RECEIVE;
        }
    }

    @Override
    public int getItemCount ( ) {
        return messageList.size ( );
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView ( R.id.message )
        TextView chatMessage;
        @BindView ( R.id.chat_layout )
        LinearLayout chatLayout;
        @BindView ( R.id.status )
        TextView status;

        public MessageViewHolder ( View itemView ) {
            super ( itemView );
            ButterKnife.bind ( this, itemView );

        }
    }
}

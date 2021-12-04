package com.example.accessibilityservice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accessibilityservice.R

class MessageListAdapter(
    private var context: Context,
    private var messageList: List<MessageList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.messeage_item,parent,false)
        return Message_ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mHolder = holder as Message_ViewHolder
        holder.itemNo.setText((position +1).toString())
        holder.message.setText(messageList.get(position).courseMessage)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    private class Message_ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemNo: TextView
        var message: TextView

        init {
            itemNo = itemView.findViewById(R.id.countNo);
            message = itemView.findViewById(R.id.message);
        }


    }


}
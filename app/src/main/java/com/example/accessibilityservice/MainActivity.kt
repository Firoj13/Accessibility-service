package com.example.accessibilityservice

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accessibilityservice.adapter.DataBaseHelper
import com.example.accessibilityservice.adapter.MessageList
import com.example.accessibilityservice.adapter.MessageListAdapter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    var messageList: List<MessageList> = ArrayList()
    var empty: List<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        getMessageList()
    }

    private fun getMessageList() {

        val gson: Gson = Gson()

        val pref:SharedPreferences = getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
        var sharedMessage: String = pref.getString("key","").toString();
        Log.e("show", "the list size: " + sharedMessage)
//        val type: Type = TypeToken<List<MessageList>>() {}.getType();
        if ( !sharedMessage.equals("")){
            val turnsType = object : TypeToken<List<MessageList>>() {}.type
            messageList = gson.fromJson(sharedMessage,turnsType)
        }

        val recycler_view: RecyclerView = findViewById(R.id.recycle_view)
        var layoutManager: RecyclerView.LayoutManager? = null

        Log.e("show ","array size :"+messageList.size)
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = MessageListAdapter(this,messageList)
    }

    private fun findView() {
        var marqueText: TextView
        marqueText = findViewById(R.id.permission_description);
        marqueText.setSelected(true);
    }


    private fun checkPermission() {
        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
//        startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
    }

    fun permission(view: View) {
        checkPermission()
    }
}
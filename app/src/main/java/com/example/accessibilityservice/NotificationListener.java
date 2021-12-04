package com.example.accessibilityservice;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.accessibilityservice.adapter.DataBaseHelper;
import com.example.accessibilityservice.adapter.MessageList;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NotificationListener extends NotificationListenerService {

    private static final String TAG = "NotificationListener";
    private static final String WA_PACKAGE = "com.whatsapp";
    List<MessageList> messageList = new ArrayList<>();

    @Override
    public void onListenerConnected() {
        Log.i(TAG, "Notification Listener connected");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (!sbn.getPackageName().equals(WA_PACKAGE)) return;

        Notification notification = sbn.getNotification();
        Bundle bundle = notification.extras;

        String from = bundle.getString(NotificationCompat.EXTRA_TITLE);
        String message = bundle.getString(NotificationCompat.EXTRA_TEXT);

        SharedPreferences prefs = getSharedPreferences("SHARED_PREFS_FILE", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<MessageList>>(){}.getType();
        String preivousMessage = prefs.getString("key","");
        messageList = gson.fromJson(preivousMessage,type);

        Log.i(TAG, "From: " + from);
        Log.e(TAG, "Message: " + message);
        messageList.add(new MessageList(message));
//        messageList.add(message);

        SharedPreferences.Editor editor = prefs.edit();
        String messages = gson.toJson(messageList);
        editor.putString("key",  messages);
        editor.commit();

    }


}

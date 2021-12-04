package com.example.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.util.ArrayList;
import java.util.List;


public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "MyAccessibilityService";
    AccessibilityServiceInfo info = new AccessibilityServiceInfo();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event == null){
            return;
        }
        String packageName = event.getPackageName().toString();
        Log.e(TAG,"app package name: "+packageName);
        PackageManager packageManager = this.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName,0);
            CharSequence charSequence = packageManager.getApplicationLabel(applicationInfo);
            Log.e(TAG,"app name is: "+charSequence);
            Log.e(TAG,"app text is: "+event.getText());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG,"app name is: error");
        }

        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED ) {
            Log.e("Notification","Recieved notification");
            if (event.getPackageName().toString().equals("com.whatsapp")){
                StringBuilder message = new StringBuilder();
                Parcelable data = event.getParcelableData();
                if (data instanceof Notification) {
                    Log.d("Notification","Recieved notification");
                    Notification notification = (Notification) data;
                    Log.d("Notification","ticker: " + notification.tickerText);
                    Log.d("Notification", "notification: "+ event.getText());
                }
                if (!event.getText().isEmpty()) {
                    for (CharSequence subText : event.getText()) {
                        message.append(subText);
                        Log.e(TAG,"whats app message: "+subText);

                    }

                    // Message from +12345678

                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG,"show the interrupt:error");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();


//        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
//                AccessibilityEvent.TYPE_VIEW_FOCUSED;

        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED |
                AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;

        // Set the type of feedback your service will provide.
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.packageNames = new String[]
                {"com.whatsapp"};
        info.notificationTimeout = 100;

        this.setServiceInfo(info);

    }
}

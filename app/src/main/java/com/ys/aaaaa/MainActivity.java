package com.ys.aaaaa;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startNotificationListenService();
    }

    // 发送通知
    public void sendNotice(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // android 3.0之前
            n = new Notification(R.mipmap.ic_launcher, "title",
                    System.currentTimeMillis());
        } else {
            // android 3.0之后
            n = new Notification.Builder(MainActivity.this)
                    .setSmallIcon(R.mipmap.ic_launcher).setTicker("title")
                    .setContentTitle("content title")
                    .setContentText("content text").setSubText("sub text")
                    .setWhen(System.currentTimeMillis()).build();
        }
        manager.notify(0, n);
    }

    // 跳转服务通知的权限界面
    public void settingsNotice(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Intent intent = new Intent(
                    "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "手机的系统不支持此功能", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // 启动监听消息通知服务
    private void startNotificationListenService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Intent intent = new Intent(MainActivity.this,
                    MyNotificationListenService.class);
            startService(intent);
        } else {
            Toast.makeText(MainActivity.this, "手机的系统不支持此功能", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}

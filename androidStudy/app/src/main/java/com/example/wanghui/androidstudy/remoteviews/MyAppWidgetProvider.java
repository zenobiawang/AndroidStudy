package com.example.wanghui.androidstudy.remoteviews;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/28.
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    public static String CLICK_DOWN = "com.example.androidStudy.action.Click";
    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(CLICK_DOWN)){
            Toast.makeText(context, "click it", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.common_full_open_on_phone);
                    AppWidgetManager manager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i ++){
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.iv_widget, rotateBitmap(context, srcbBitmap, degree));
                        Intent clickIntent = new Intent();
                        clickIntent.setAction(CLICK_DOWN);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, 0);
                        remoteViews.setOnClickPendingIntent(R.id.iv_widget, pendingIntent);
                        manager.updateAppWidget(new ComponentName(context.getPackageName(), MyAppWidgetProvider.class.getName()), remoteViews);
                        SystemClock.sleep(30);
                    }

                }
            }).start();
        }
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcbBitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap bitmap = Bitmap.createBitmap(srcbBitmap, 0, 0, srcbBitmap.getWidth(), srcbBitmap.getHeight(), matrix, true);
        return bitmap;
    }
}

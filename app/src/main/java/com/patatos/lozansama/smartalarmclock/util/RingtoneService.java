package com.patatos.lozansama.smartalarmclock.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities.AlarmList;

public class RingtoneService extends Service {

    private boolean isRunning;
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), AlarmList.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("Alarm is sounding!")
                .setContentText("Turn off!")
                .setSmallIcon(R.drawable.ic_add_alarm)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        String state = intent.getExtras().getString("extra");

        assert state != null;
        switch (state) {
            case "no":
                startId = 0;
                break;
            case "yes":
                startId = 1;
                break;
            default:
                startId = 0;
                break;
        }


        if(!this.isRunning && startId == 1) {
            Log.e("not sound ", "start");

            mediaPlayer = MediaPlayer.create(this, R.raw.black);
            mediaPlayer.start();

            mNM.notify(0, mNotify);
            this.isRunning = true;

        }
        else if (!this.isRunning && startId == 0){
            Log.e("not sound ", "end");

            this.isRunning = false;
        }

        else if (this.isRunning && startId == 1){
            Log.e("is sound ", "start");

            this.isRunning = true;
        }
        else {
            Log.e("is sound ", " end");

            mediaPlayer.stop();
            mediaPlayer.reset();
            this.isRunning = false;
        }

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.isRunning = false;
    }





}

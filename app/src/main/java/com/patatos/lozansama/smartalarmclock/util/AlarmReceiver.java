package com.patatos.lozansama.smartalarmclock.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by L on 21/06/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentService = new Intent(context, RingtoneService.class);
        context.startService(intentService);
    }
}

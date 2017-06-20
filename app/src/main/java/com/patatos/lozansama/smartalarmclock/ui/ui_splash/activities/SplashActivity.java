package com.patatos.lozansama.smartalarmclock.ui.ui_splash.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities.AlarmList;
import com.patatos.lozansama.smartalarmclock.ui.ui_login.activities.RegistrationActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_layout);

        initRealm();

        new CountDownTimer(1000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                    intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                    finish();

            }
        }.start();

    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
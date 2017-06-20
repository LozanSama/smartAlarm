package com.patatos.lozansama.smartalarmclock.ui.ui_add_modified_alarm.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.patatos.lozansama.smartalarmclock.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddModifiedAlarm extends AppCompatActivity {

    @BindView(R.id.et_name_alarm)
    EditText etNameAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_modified_alarm_activity_layout);
        ButterKnife.bind(this);

        etNameAlarm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etNameAlarm.setFocusable(true);
                etNameAlarm.setFocusableInTouchMode(true);
                return false;
            }
        });
    }
}

package com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list;

import com.patatos.lozansama.smartalarmclock.data.domain.AlarmUser;

public interface OnItemClickInterface {
    void onItemClick(AlarmUser alarmUser, int position);
    void onItemLongClick(int position);
    void onSwitchChanged(AlarmUser alarmUser, int position);
}

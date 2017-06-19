package com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.dto.AlarmsUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<AlarmsUser> data;

    public AlarmAdapter(List<AlarmsUser> data) {
        this.data = data;
    }

    public static class AlarmViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_days_alarm)
        TextView tvDays;
        @BindView(R.id.tv_name_alarm)
        TextView tvName;
        @BindView(R.id.tv_hour)
        TextView tvHour;

        public AlarmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTitular(AlarmsUser alarmsUser) {
            tvDays.setText(alarmsUser.getDays());
            tvName.setText(alarmsUser.getDays());
            tvHour.setText(alarmsUser.getDays());
        }
    }


    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.items_rv_alarm_layout, viewGroup, false);

        AlarmViewHolder tvh = new AlarmViewHolder(itemView);
        return tvh;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder viewHolder, int position) {
        AlarmsUser item = data.get(position);

        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
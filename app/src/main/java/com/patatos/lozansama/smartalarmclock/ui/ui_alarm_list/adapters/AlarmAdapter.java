package com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;
import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.domain.AlarmUser;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.OnItemClickInterface;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities.AlarmList;

import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<AlarmUser> data;
    private OnItemClickInterface listener;

    public AlarmAdapter(List<AlarmUser> data, AlarmList listener) {
        this.data = data;
        this.listener = listener;
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_temperature_alarm)
        TextView tvTemperature;
        @BindView(R.id.tv_name_alarm)
        TextView tvName;
        @BindView(R.id.tv_hour)
        TextView tvHour;
        @BindView(R.id.switch_on_off)
        Switch switchOnOff;
        @BindView(R.id.cv_cardView)
        CardView cardView;


        public AlarmViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTitular(final AlarmUser alarmsUser) {
            if (alarmsUser.getTemperature()) {
                tvTemperature.setText(R.string.temperature_on);
            } else {
                tvTemperature.setText(R.string.temperature_off);
            }

            tvName.setText(alarmsUser.getName());
            tvHour.setText(alarmsUser.getHour() + ":" + alarmsUser.getMinute());
            switchOnOff.setChecked(alarmsUser.isTurnOn());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(alarmsUser, getAdapterPosition());
                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    data.remove(alarmsUser);
                    listener.onItemLongClick(getAdapterPosition());
                    notifyDataSetChanged();
                    return true;
                }
            });

            switchOnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alarmsUser.setTurnOn(!alarmsUser.isTurnOn());
                    listener.onSwitchChanged(alarmsUser, getAdapterPosition());
                }
            });
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
        AlarmUser item = data.get(position);

        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
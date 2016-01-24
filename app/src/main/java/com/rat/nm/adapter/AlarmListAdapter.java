package com.rat.nm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rat.networkmanager.R;
import com.rat.nm.entity.model.Alarm;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 告警列表adapter
 */
public class AlarmListAdapter extends BaseAdapter {
    private Context context;
    private List<Alarm> list;
    private ViewHolder viewHolder;

    public AlarmListAdapter(Context context, List<Alarm> list) {
        this.context = context;
        this.list = list;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void modifyData(List<Alarm> ls, boolean isClean) {
        if (isClean) {
            this.list.clear();
            this.list = ls;
        } else {
            for (Alarm p : ls) {
                this.list.add(p);
            }
        }
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_alarm_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.indexBtn = (Button) convertView.findViewById(R.id.indexBtn);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            viewHolder.statusTV = (TextView) convertView.findViewById(R.id.statusTV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Alarm alarm = list.get(position);
        if (null == alarm)
            return convertView;
        int resourceId = 0;
//        int status = alarm.getStatus();
//        String statusStr = "";
//        int color = 0;
//        if (Alarm.LEVEL_1 == status) {
//            statusStr = "LEVEL_1";
//            color = R.color.red;
//        } else if (Alarm.LEVEL_2 == status) {
//            statusStr = "LEVEL_2";
//            color = R.color.blue;
//        } else if (Alarm.LEVEL_3 == status) {
//            statusStr = "LEVEL_3";
//            color = R.color.gray;
//        } else if (Alarm.LEVEL_4 == status) {
//            statusStr = "LEVEL_4";
//            color = R.color.white;
//        }
//        viewHolder.indexBtn.setText(String.valueOf(position));
//        viewHolder.nameTV.setText(alarm.getName());
//        viewHolder.statusTV.setText(statusStr);
//        viewHolder.statusTV.setTextColor(context.getResources().getColor(color));
        return convertView;
    }

    private class ViewHolder {
        private Button indexBtn;
        private TextView nameTV;
        private TextView statusTV;
    }
}
package com.rat.snowkids.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.snowkids.snowkids.R;
import com.rat.snowkids.entity.model.Alarm;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 告警详情adapter
 */
public class AlarmDetailAdapter extends BaseAdapter {
    private Context context;
    private List<Alarm> list;
    private ViewHolder viewHolder;

    public AlarmDetailAdapter(Context context, List<Alarm> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.view_alarm_detail_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iconIV = (ImageView) convertView.findViewById(R.id.iconIV);
            viewHolder.contentTV = (TextView) convertView.findViewById(R.id.contentTV);
            viewHolder.timeTV = (TextView) convertView.findViewById(R.id.timeTV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Alarm alarm = list.get(position);
        if (null == alarm)
            return convertView;
        int resourceId = 0;
//        int status = alarm.getTimeEnd();
//        if (Alarm.LEVEL_1 == status)
//            resourceId = R.mipmap.alarm_detail_warn1;
//        else if (Alarm.LEVEL_2 == status)
//            resourceId = R.mipmap.alarm_detail_warn2;
//        else if (Alarm.LEVEL_3 == status)
//            resourceId = R.mipmap.alarm_detail_warn3;
//        else if (Alarm.LEVEL_4 == status)
//            resourceId = R.mipmap.alarm_detail_warn4;
        viewHolder.iconIV.setBackgroundResource(resourceId);
//        viewHolder.contentTV.setText(alarm.getContent());
//        viewHolder.timeTV.setText(alarm.getTime());
        return convertView;
    }

    private class ViewHolder {
        private ImageView iconIV;
        private TextView contentTV;
        private TextView timeTV;
    }
}
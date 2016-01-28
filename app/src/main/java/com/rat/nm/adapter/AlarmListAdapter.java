package com.rat.nm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rat.networkmanager.R;
import com.rat.nm.entity.enums.AlarmType;
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
    public ImageLoader imageLoader;

    public AlarmListAdapter(Context context, List<Alarm> list) {
        this.context = context;
        this.list = list;
        this.imageLoader = ImageLoader.getInstance();
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
            for (Alarm d : ls) {
                this.list.add(d);
            }
        }
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_alarm_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iconIV = (ImageView) convertView.findViewById(R.id.iconIV);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            viewHolder.contentTV = (TextView) convertView.findViewById(R.id.contentTV);
            viewHolder.desTV = (TextView) convertView.findViewById(R.id.desTV);
            viewHolder.statusIV = (ImageView) convertView.findViewById(R.id.statusIV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Alarm alarm = list.get(position);
        if (null == alarm)
            return convertView;
        viewHolder.nameTV.setText(context.getString(R.string.device_colon) + alarm.getDeviceName());
        viewHolder.contentTV.setText("[" + alarm.getTimeStart() + "] - [" + alarm.getTimeEnd() + "]");
        viewHolder.desTV.setText(alarm.getLog());
        String type = alarm.getType();
        if (AlarmType.INFO.getMessage().equals(type))
            viewHolder.statusIV.setBackgroundResource(R.mipmap.alarm_detail_warn3);
        else if (AlarmType.ALARM.getMessage().equals(type))
            viewHolder.statusIV.setBackgroundResource(R.mipmap.alarm_detail_warn2);
        else if (AlarmType.FAULT.getMessage().equals(type))
            viewHolder.statusIV.setBackgroundResource(R.mipmap.alarm_detail_warn1);
        else
            viewHolder.statusIV.setBackgroundResource(0);
        return convertView;
    }

    private class ViewHolder {
        private ImageView iconIV;
        private TextView nameTV;
        private TextView contentTV;
        private TextView desTV;
        private ImageView statusIV;
    }
}
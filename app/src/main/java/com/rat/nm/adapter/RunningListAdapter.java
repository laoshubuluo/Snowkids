package com.rat.nm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rat.networkmanager.R;
import com.rat.nm.entity.Device;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 运行设备列表adapter
 */
public class RunningListAdapter extends BaseAdapter {
    private Context context;
    private List<Device> list;
    private ViewHolder viewHolder;

    public RunningListAdapter(Context context, List<Device> list) {
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

    public void modifyData(List<Device> ls, boolean isClean) {
        if (isClean) {
            this.list.clear();
            this.list = ls;
        } else {
            for (Device d : ls) {
                this.list.add(d);
            }
        }
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_running_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.indexBtn = (Button) convertView.findViewById(R.id.indexBtn);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            viewHolder.statusTV = (TextView) convertView.findViewById(R.id.statusTV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Device device = list.get(position);
        if (null == device)
            return convertView;
        int status = device.getStatus();
        String statusStr = "";
        int color = 0;
        if (Device.NORMAL == status) {
            statusStr = "NORMAL";
            color = R.color.blue;
        } else if (Device.STOP == status) {
            statusStr = "STOP";
            color = R.color.gray;
        } else if (Device.ERROR == status) {
            statusStr = "ERROR";
            color = R.color.red;
        }
        viewHolder.indexBtn.setText(String.valueOf(position));
        viewHolder.nameTV.setText(device.getName());
        viewHolder.statusTV.setText(statusStr);
        viewHolder.statusTV.setTextColor(context.getResources().getColor(color));
        return convertView;
    }

    private class ViewHolder {
        private Button indexBtn;
        private TextView nameTV;
        private TextView statusTV;
    }
}
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
import com.rat.nm.entity.enums.RunningStatus;
import com.rat.nm.entity.model.Device;
import com.rat.nm.util.ImageUtil;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 设备列表adapter
 */
public class DeviceListAdapter extends BaseAdapter {
    private Context context;
    private List<Device> list;
    private ViewHolder viewHolder;
    public ImageLoader imageLoader;

    public DeviceListAdapter(Context context, List<Device> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.view_device_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iconIV = (ImageView) convertView.findViewById(R.id.iconIV);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            viewHolder.desTV = (TextView) convertView.findViewById(R.id.desTV);
            viewHolder.statusIV = (ImageView) convertView.findViewById(R.id.statusIV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Device device = list.get(position);
        if (null == device)
            return convertView;
        viewHolder.nameTV.setText(device.getName4Show());
        viewHolder.desTV.setText(device.getNameInEN());
        imageLoader.displayImage(device.getImageUrl(), viewHolder.iconIV, ImageUtil.getImageOptions());
        String runningStatus = device.getRunningStatus();
        if (RunningStatus.ONLINE.getMessage().equals(runningStatus))
            viewHolder.statusIV.setBackgroundResource(R.drawable.shape_circle_solid_green);
        else if (RunningStatus.OFFLINE.getMessage().equals(runningStatus))
            viewHolder.statusIV.setBackgroundResource(R.drawable.shape_circle_solid_red);
        else
            viewHolder.statusIV.setBackgroundResource(0);
        return convertView;
    }

    private class ViewHolder {
        private ImageView iconIV;
        private TextView nameTV;
        private TextView desTV;
        private ImageView statusIV;
    }
}
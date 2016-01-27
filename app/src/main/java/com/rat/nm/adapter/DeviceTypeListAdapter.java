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
import com.rat.nm.entity.model.DeviceType;
import com.rat.nm.util.ImageUtil;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 设备列表adapter
 */
public class DeviceTypeListAdapter extends BaseAdapter {
    private Context context;
    private List<DeviceType> list;
    private ViewHolder viewHolder;
    public ImageLoader imageLoader;


    public DeviceTypeListAdapter(Context context, List<DeviceType> list) {
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

    public void modifyData(List<DeviceType> ls, boolean isClean) {
        if (isClean) {
            this.list.clear();
            this.list = ls;
        } else {
            for (DeviceType dt : ls) {
                this.list.add(dt);
            }
        }
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_device_type_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iconIV = (ImageView) convertView.findViewById(R.id.iconIV);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            viewHolder.desTV = (TextView) convertView.findViewById(R.id.desTV);
            viewHolder.statusIV = (ImageView) convertView.findViewById(R.id.statusIV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final DeviceType deviceType = list.get(position);
        if (null == deviceType)
            return convertView;
        viewHolder.nameTV.setText(deviceType.getName());
        viewHolder.desTV.setText(deviceType.getDescribe());
        imageLoader.displayImage(deviceType.getImageUrl(), viewHolder.iconIV, ImageUtil.getImageOptions());
        return convertView;
    }

    private class ViewHolder {
        private ImageView iconIV;
        private TextView nameTV;
        private TextView desTV;
        private ImageView statusIV;
    }
}
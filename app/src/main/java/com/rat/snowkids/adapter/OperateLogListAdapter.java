package com.rat.snowkids.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.snowkids.snowkids.R;
import com.rat.snowkids.entity.model.OperateLog;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 操作日志列表adapter
 */
public class OperateLogListAdapter extends BaseAdapter {
    private Context context;
    private List<OperateLog> list;
    private ViewHolder viewHolder;
    public ImageLoader imageLoader;

    public OperateLogListAdapter(Context context, List<OperateLog> list) {
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

    public void modifyData(List<OperateLog> ls, boolean isClean) {
        if (isClean) {
            this.list.clear();
            this.list = ls;
        } else {
            for (OperateLog d : ls) {
                this.list.add(d);
            }
        }
        notifyDataSetChanged();
    }

    public List<OperateLog> getList() {
        return list;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_operate_log_list_item, null);
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
        final OperateLog operateLog = list.get(position);
        if (null == operateLog)
            return convertView;
        viewHolder.nameTV.setText(context.getString(R.string.device_colon) + operateLog.getDeviceName() + "    " + context.getString(R.string.user_colon) + operateLog.getUserName());
        viewHolder.contentTV.setText(operateLog.getType());
        viewHolder.desTV.setText(operateLog.getTime());
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
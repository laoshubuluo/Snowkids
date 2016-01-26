package com.rat.nm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rat.networkmanager.R;
import com.rat.nm.entity.model.OperateLog;

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

    public OperateLogListAdapter(Context context, List<OperateLog> list) {
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

    public void modifyData(List<OperateLog> ls, boolean isClean) {
        if (isClean) {
            this.list.clear();
            this.list = ls;
        } else {
            for (OperateLog p : ls) {
                this.list.add(p);
            }
        }
        notifyDataSetChanged();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_operate_log_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.indexBtn = (Button) convertView.findViewById(R.id.indexBtn);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.nameTV);
            viewHolder.statusTV = (TextView) convertView.findViewById(R.id.statusTV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final OperateLog operateLog = list.get(position);
        if (null == operateLog)
            return convertView;
        int resourceId = 0;
//        int status = operateLog.getStatus();
//        String statusStr = "";
//        int color = 0;
//        if (OperateLog.LEVEL_1 == status) {
//            statusStr = "LEVEL_1";
//            color = R.color.red;
//        } else if (OperateLog.LEVEL_2 == status) {
//            statusStr = "LEVEL_2";
//            color = R.color.blue;
//        } else if (OperateLog.LEVEL_3 == status) {
//            statusStr = "LEVEL_3";
//            color = R.color.gray;
//        } else if (OperateLog.LEVEL_4 == status) {
//            statusStr = "LEVEL_4";
//            color = R.color.white;
//        }
//        viewHolder.indexBtn.setText(String.valueOf(position));
//        viewHolder.nameTV.setText(operateLog.getName());
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
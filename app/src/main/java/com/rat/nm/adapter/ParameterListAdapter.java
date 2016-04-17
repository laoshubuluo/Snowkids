package com.rat.nm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rat.networkmanager.R;
import com.rat.nm.entity.model.Parameter;
import com.rat.nm.util.StringUtils;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/09/14
 * introduce : 参数列表adapter
 */
public class ParameterListAdapter extends BaseAdapter {
    private Context context;
    private List<Parameter> list;
    private ViewHolder viewHolder;

    public ParameterListAdapter(Context context, List<Parameter> list) {
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

    public void modifyData(List<Parameter> ls, boolean isClean) {
        if (isClean) {
            this.list.clear();
            this.list = ls;
        } else {
            for (Parameter p : ls) {
                this.list.add(p);
            }
        }
        notifyDataSetChanged();
    }

    public List<Parameter> getList() {
        return list;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_parameter_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.keyTV = (TextView) convertView.findViewById(R.id.keyTV);
            viewHolder.valueTV = (TextView) convertView.findViewById(R.id.valueTV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Parameter parameter = list.get(position);
        if (null == parameter)
            return convertView;
        viewHolder.keyTV.setText(parameter.getKey());
        parameter.setValue(StringUtils.isNullOrBlank(parameter.getValue()) ? "-" : parameter.getValue());
        viewHolder.valueTV.setText(parameter.getValue());
        return convertView;
    }

    private class ViewHolder {
        private TextView keyTV;
        private TextView valueTV;
    }
}
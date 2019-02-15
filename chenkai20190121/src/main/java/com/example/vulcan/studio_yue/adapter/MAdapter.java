package com.example.vulcan.studio_yue.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vulcan.studio_yue.R;
import com.example.vulcan.studio_yue.bean.PerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends BaseAdapter {

    private Context context;
    private List<PerBean.DataBean> list = new ArrayList<PerBean.DataBean>();

    public MAdapter(Context context, List<PerBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.la, null);
            holder.ivs = convertView.findViewById(R.id.ivs);
            holder.tes = convertView.findViewById(R.id.tes);
            holder.tee = convertView.findViewById(R.id.tee);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getImage_url(),holder.ivs);
        holder.tes.setText(list.get(position).getTitle());
        holder.tee.setText(list.get(position).getContent());

        return convertView;
    }


        class ViewHolder {
            ImageView ivs;
            TextView tes, tee;
        }
    }

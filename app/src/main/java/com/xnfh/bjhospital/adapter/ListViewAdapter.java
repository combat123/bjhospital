package com.xnfh.bjhospital.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xnfh.bjhospital.R;
import com.xnfh.bjhospital.bean.FindBean;
import com.xnfh.bjhospital.weight.CircleImageView;

import java.util.List;

/**
 * Created by wangxuewei on 2017/10/20.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    List<FindBean.InfoBean.RowsBean> rows;
    public ListViewAdapter(Context context, List<FindBean.InfoBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;

    }
    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Object getItem(int i) {
        return rows.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            view = View.inflate(context, R.layout.item_list_recommend,null);
            holder = new ViewHolder();
            holder.circleImageView = view.findViewById(R.id.circleImg);
            holder.tv_name = view.findViewById(R.id.tv_name_in);
            holder.tv_descripe = view.findViewById(R.id.tv_describe_in);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        FindBean.InfoBean.RowsBean rowsBean = rows.get(i);
        String imgAdd = rowsBean.getPic();
        Glide.with(context)
                .load(imgAdd)
                .error(R.mipmap.ic_launcher)
                .into(holder.circleImageView);
        holder.tv_descripe.setText(rowsBean.getArticleTitle());
        holder.tv_name.setText(rowsBean.getName());
        return view;
    }

    class ViewHolder {
        CircleImageView circleImageView;
        TextView tv_name;
        TextView tv_descripe;
    }
}

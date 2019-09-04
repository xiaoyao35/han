package com.bawei.hancongcong0903.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.hancongcong0903.ImageAsync;
import com.bawei.hancongcong0903.R;
import com.bawei.hancongcong0903.model.ShopBean;

import java.util.ArrayList;

/**
 * author: 韩聪聪
 * data: 2019/9/3 09:9:52
 * function:
 */
public class HomeAdapter extends BaseAdapter {
    private ArrayList<ShopBean.Data> list;
    private Context context;

    public HomeAdapter(ArrayList<ShopBean.Data> list, Context context) {
        this.list = list;
        this.context = context;
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
        //声明
        ViewHolder holder;
        //判断
        if (convertView==null){
            //加载布局
            convertView=View.inflate(context,R.layout.day01,null);
            //实例化
            holder=new ViewHolder();
            //获取数据
            holder.text1=convertView.findViewById(R.id.text1);
            holder.text2=convertView.findViewById(R.id.text2);
            holder.image1=convertView.findViewById(R.id.image1);
            //保存数据
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //设置数据
        holder.text1.setText(list.get(position).getGoods_name());
        holder.text2.setText(list.get(position).getCurrency_price());
        new ImageAsync(list.get(position).getGoods_thumb(),holder.image1).execute();
        return convertView;
    }
    //创建外部类
    class ViewHolder{
        TextView text1,text2;
        ImageView image1;
    }
}

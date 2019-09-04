package com.bawei.hancongcong0903;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bawei.hancongcong0903.adapter.HomeAdapter;
import com.bawei.hancongcong0903.model.ShopBean;
import com.bawei.hancongcong0903.utils.NetUtil;
import com.bwei.xlistview.XlistView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String wangluoqingqiu;
    public static final String TAG="MainActivity";
    private XlistView xlistView;
    int page=1;
    private ArrayList<ShopBean.Data> dest=new ArrayList<>();
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        xlistView = findViewById(R.id.xlist);
        sp = getSharedPreferences("congif", Context.MODE_PRIVATE);
        //网络判断
        if (NetUtil.getInstance().getWangluopanduan(MainActivity.this)){
            Toast.makeText(MainActivity.this, "网络链接成功", Toast.LENGTH_SHORT).show();
            new getWangluoqingqiu().execute();
        }else{
            Toast.makeText(MainActivity.this, "网络链接失败", Toast.LENGTH_SHORT).show();
            String key = sp.getString("key", "");
            Gson gson = new Gson();
            ShopBean shopBean = gson.fromJson(key, ShopBean.class);
            ArrayList<ShopBean.Data> data = shopBean.getData();
            dest.addAll(data);
            xlistView.setAdapter(new HomeAdapter(dest,MainActivity.this));
        }
        //上下拉刷新
        xlistView.setPullRefreshEnable(true);
        xlistView.setPullLoadEnable(true);
        xlistView.setXListViewListener(new XlistView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                dest.clear();
                new getWangluoqingqiu().execute();
                xlistView.stopRefresh();
            }
            @Override
            public void onLoadMore() {
                page++;
                new getWangluoqingqiu().execute();
                xlistView.stopLoadMore();
            }
        });
    }
    //创建一个网络请求的Async类
    public class getWangluoqingqiu extends AsyncTask<Integer,Integer,String> {
        String path="http://blog.zhaoliang5156.cn/api/shop/shop"+page+".json";
        @Override
        protected String doInBackground(Integer... integers) {
            try {
                wangluoqingqiu = NetUtil.getInstance().getWangluoqingqiu(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return wangluoqingqiu;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "onPostExecute: "+s);
            //存储
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("key",s);
            edit.commit();
            //解析
            Gson gson = new Gson();
            ShopBean shopBean = gson.fromJson(s, ShopBean.class);
            ArrayList<ShopBean.Data> data = shopBean.getData();
            dest.addAll(data);
            //设置适配器
            xlistView.setAdapter(new HomeAdapter(dest,MainActivity.this));
        }
    }
}

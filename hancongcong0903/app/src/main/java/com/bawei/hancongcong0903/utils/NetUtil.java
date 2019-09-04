package com.bawei.hancongcong0903.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * author: 韩聪聪
 * data: 2019/9/3 09:9:19
 * function:
 */
public class NetUtil {
    //创建懒汉式
    //创建私有静态变量
    private static NetUtil netUtil;
    private ConnectivityManager connectivityManager;
    private URL url;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] bytes;
    private Bitmap bitmap;

    //创建私有构造方法
    private NetUtil(){

    }
    //创建对外使用的方法
    public static synchronized NetUtil getInstance(){
        //判断
        if (netUtil==null){
            //实例化
            netUtil=new NetUtil();
        }
        return netUtil;
    }
    //创建网络判断的方法
    public boolean getWangluopanduan(Context context){
        //获取管理类
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //使用方法
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //判断
        if (activeNetworkInfo!=null){
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
    //创建网络请求的方法
    public String getWangluoqingqiu(String path) throws IOException {
        //创建URL
        url = new URL(path);
        //打开网络
        connection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        connection.setRequestMethod("GET");
        //设置超时链接
        connection.setConnectTimeout(5*1000);
        //设置读取超时链接
        connection.setReadTimeout(5*1000);
        //判断是否请求成功
        if (connection.getResponseCode()==200){
            //创建字节输入流
            inputStream = connection.getInputStream();
            //创建数组输出流
            byteArrayOutputStream = new ByteArrayOutputStream();
            //创建数组
            bytes = new byte[1024];
            //创建变量
            int len;
            //创建循环
            while((len=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,len);
            }
            //关闭流
            inputStream.close();
        }
        return byteArrayOutputStream.toString();
    }
    //创建网络请求图片的方法
    public Bitmap getWangluoTupian(String image) throws IOException {
        //创建URL
        URL url = new URL(image);
        //打开网络
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        //设置请求方式
        connection.setRequestMethod("GET");
        //设置超时链接
        connection.setConnectTimeout(5*1000);
        //设置读取超时链接
        connection.setReadTimeout(5*1000);
        //判断是否请求成功
        if (connection.getResponseCode()==200){
            //创建字节输入流
            InputStream inputStream = connection.getInputStream();
            //使用图片方法保存图片
            bitmap = BitmapFactory.decodeStream(inputStream);
        }
        return bitmap;
    }
}

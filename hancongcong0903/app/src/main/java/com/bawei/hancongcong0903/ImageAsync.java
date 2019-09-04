package com.bawei.hancongcong0903;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bawei.hancongcong0903.model.ShopBean;
import com.bawei.hancongcong0903.utils.NetUtil;

import java.io.IOException;

/**
 * author: 韩聪聪
 * data: 2019/9/3 10:10:29
 * function:
 */
public class ImageAsync extends AsyncTask<Integer,Integer,Bitmap> {
    private String path;
    private ImageView imageView;
    private Bitmap wangluoTupian;

    public ImageAsync(String path, ImageView imageView) {
        this.path = path;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Integer... integers) {
        try {
            wangluoTupian = NetUtil.getInstance().getWangluoTupian(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wangluoTupian;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}

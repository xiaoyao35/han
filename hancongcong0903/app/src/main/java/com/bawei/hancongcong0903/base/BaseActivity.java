package com.bawei.hancongcong0903.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * author: 韩聪聪
 * data: 2019/9/3 10:10:39
 * function:
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
}

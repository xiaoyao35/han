package com.bawei.hancongcong0903.model;

import java.util.ArrayList;

/**
 * author: 韩聪聪
 * data: 2019/9/3 09:9:48
 * function:
 */
public class ShopBean {
    private ArrayList<ShopBean.Data> data;

    public ShopBean(ArrayList<Data> data) {
        this.data = data;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "data=" + data +
                '}';
    }

    //创建一个内部类
    public class Data{
        private String goods_name;
        private String currency_price;
        private String goods_thumb;

        public Data(String goods_name, String currency_price, String goods_thumb) {
            this.goods_name = goods_name;
            this.currency_price = currency_price;
            this.goods_thumb = goods_thumb;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getCurrency_price() {
            return currency_price;
        }

        public void setCurrency_price(String currency_price) {
            this.currency_price = currency_price;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "goods_name='" + goods_name + '\'' +
                    ", currency_price='" + currency_price + '\'' +
                    ", goods_thumb='" + goods_thumb + '\'' +
                    '}';
        }
    }
}

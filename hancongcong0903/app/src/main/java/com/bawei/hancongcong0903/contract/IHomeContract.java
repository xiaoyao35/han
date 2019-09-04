package com.bawei.hancongcong0903.contract;

/**
 * author: 韩聪聪
 * data: 2019/9/3 10:10:42
 * function:
 */
public interface IHomeContract {
    //interface view
    interface Iview{
        void HomeSuccess(String data);
        void HomeFailure(String e);
    }
    //interface model
    interface Imodel{
        void setHomeData(String path,ImodelCallback imodelCallback);
        interface ImodelCallback{

        }
    }
    //interface presenter
    interface Ipresenter{
        void attachView(IHomeContract.Iview iview);
        void detachView();
        void presenter(String path);
    }
}

package com.irenshi.personneltreasure.base;

/**
 * Created by mike.li on 2018/4/27.
 */

public class BaseContract {
    public interface BaseView{
        boolean isActive();
        void showProgressDialog();
        void showProgressDialog(boolean isCancel, String message);
        void closeProgressDialog();
    }
    public interface BasePresenter{
    }
}

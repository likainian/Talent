package com.irenshi.personneltreasure.util;

import com.alibaba.fastjson.JSONObject;
import com.irenshi.personneltreasure.application.PersonnelTreasureApplication;
import com.irenshi.personneltreasure.bean.ErrorEntity;
import com.irenshi.personneltreasure.newappp.data.httpservice.business.ApiServiceUtils;
import com.irenshi.personneltreasure.newappp.data.httpservice.business.IPostRequestCallBack;
import com.irenshi.personneltreasure.newappp.data.requestvo.RequestVo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mike.li on 2018/6/6.
 */

public class UnReadCountPresenter {
    public static void getUnReadBacklog(){
        //会刷新未读数，并刷新列表
        ApiServiceUtils.requestPostDataFromService(PersonnelTreasureApplication.getInstance().getAppComponent(),
                new RequestVo(com.irenshi.personneltreasure.newappp.util.ConstantUtil.HTTP_BACKLOG_HOTCOUNT, null), new IPostRequestCallBack() {
                    @Override
                    public void onGetResponseBody(String responseBodyString) {
                        JSONObject jsonObject = JSONObject.parseObject(responseBodyString);
                        Integer count = jsonObject.getInteger("count");
                        Observable.just(count).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                UnReadCountCache.getInstance().saveUnReadCount(UnReadCountCache.backlog,integer);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                LogUtil.i(throwable.getMessage());
                            }
                        });

                    }

                    @Override
                    public void onStartOnMainThread(Disposable disposable) {

                    }

                    @Override
                    public void onError(ErrorEntity error) {
                        LogUtil.i(error.getMessage());
                    }
                });
    }
}

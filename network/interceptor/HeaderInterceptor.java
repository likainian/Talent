package com.irenshi.personneltreasure.network.interceptor;

import android.support.annotation.NonNull;

import com.irenshi.personneltreasure.application.SharedPreferencesHelper;
import com.irenshi.personneltreasure.util.EmptyUtil;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mike.li on 2018/4/17.
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //加入请求头
        Request.Builder builder = chain.request().newBuilder();
        builder.headers(Headers.of(SharedPreferencesHelper.getInstance().getHeaders()));
        Request request = builder.build();
        //response的header更新
        Response response = chain.proceed(request);
        if(response!=null){
            Headers headers = response.headers();
            String newSession = headers.get("userSession");
            if(newSession!=null&&!newSession.equals(SharedPreferencesHelper.getInstance().getUserSession())){
                String newStaffId = headers.get("staffId");
                String companyId = headers.get("companyId");
                String userId = headers.get("userId");
                if (!EmptyUtil.isEmpty(newSession)) {
                    SharedPreferencesHelper.getInstance().setUserSession(newSession);
                }
                if (!EmptyUtil.isEmpty(companyId)) {
                    SharedPreferencesHelper.getInstance().setCompanyId(companyId);
                }
                if (!EmptyUtil.isEmpty(newStaffId)) {
                    SharedPreferencesHelper.getInstance().setStaffId(newStaffId);
                }
                if (!EmptyUtil.isEmpty(userId)) {
                    SharedPreferencesHelper.getInstance().setPhoneNumber(userId);
                }
            }
        }
        return response;
    }
}

package com.irenshi.personneltreasure.util;


import android.os.Environment;

import com.irenshi.personneltreasure.application.SharedPreferencesHelper;

import java.io.File;

/**
 * Created by mike.li on 2018/5/15.
 */

public class ConstantUtil {
    public static String URL_BASE_URL_RELEASE = "http://www.ihr360.com/api/";//线上baseUrl
    public static String URL_COMPANY_NAME_DEBUG = "http://pay-dev.ihr360.com/companyBaseUrl/getUrl";//验证公司名称debug
    public static String URL_COMPANY_NAME_RELEASE = "https://pay.ihr360.com/companyBaseUrl/getUrl";//验证公司名称线上
    public static String URL_WEB_VIEW_HELP = "https://help.ihr360.com/FAQ_m_login.html";//帮助的H5

    public static String HTTP_ACCOUNT_TYPE = "account/mobileType/v1";//获得电话类型，中国，新加坡
    public static String HTTP_LOGIN = "account/login/v7";//登陆
    public static String HTTP_SHOW_SHAPE_DYNAMIC = "account/showLoginVerifyCode/v1";//是否显示登陆验证码
    public static String HTTP_LOGIN_CODE = "account/generateVerifyCode/v1.do?type=LOGIN";//首页登陆验证码
    public static String HTTP_HOME_MODEL_CONFIG = "appModelConfigApp/getAppModelConfig/v4";//审批，申请，应用
    public static String HTTP_VERSION = "common/version/v1";//版本是否最新
    public static String HTTP_ACTIVE = "account/active/v1";//进入app激活
    public static String HTTP_ROSTER = "roster/applyList/v1";//花名册H5
    public static final String HTTP_COMPANY_CAROUSEL = "company/carousel/v2"; // 轮播图
    public static final String HTTP_CHECK_APP_PHOTO_IS_APPROVED = "account/avatar/isApproved/v1";// 头像是否审批通过

    public static String getImageUrl(String imageId){
        return getBaseUrl()+"image/"+imageId+".jpg";
    }
    public static String getHeaderUrl(String headerId){
        return getBaseUrl()+"image/person/"+headerId+".jpg";
    }

    public static String getBaseUrl() {
        return SharedPreferencesHelper.getInstance().getString(SharedPreferencesHelper.SHARED_HTTP_BASE_URL,ConstantUtil.URL_BASE_URL_RELEASE);
    }
    public static void setBaseUrl(String baseUrl) {
        SharedPreferencesHelper.getInstance().putString(SharedPreferencesHelper.SHARED_HTTP_BASE_URL,baseUrl);
    }
    public static String getCameraPhonePath(){
        return FileUtil.createPathAndFile(Environment.getExternalStorageDirectory().getAbsolutePath()+
                File.separator+"irenshi"+File.separator+"picture"+File.separator+System.currentTimeMillis()+".jpg");
    }
    public static String getSavePicturePath(String path){
        path = MD5Util.toMD5(path);
        if(path==null)path = String.valueOf(System.currentTimeMillis());
        return FileUtil.createPathAndFile(Environment.getExternalStorageDirectory().getAbsolutePath()+
                File.separator+"irenshi"+File.separator+"cache"+File.separator+path+".jpg");
    }
}

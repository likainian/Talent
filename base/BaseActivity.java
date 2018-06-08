package com.irenshi.personneltreasure.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.irenshi.personneltreasure.R;
import com.irenshi.personneltreasure.dialog.LoadingProgressDialog;
import com.irenshi.personneltreasure.util.CommonUtil;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.BaseView{

    private LoadingProgressDialog loadingProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setStatusBarColor(@ColorInt int color){
        //21以上版本设置颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }
    public void showStatusBar() {
        View decorView = getWindow().getDecorView();
        if (decorView == null) return;
        decorView.setFitsSystemWindows(true);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
    public void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        if (decorView == null) return;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        decorView.setFitsSystemWindows(false);
        //21以上版本设置颜色，19版本以上状态栏透明，19以下不可设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    protected void addFragment(@IdRes int layoutFragmentId, Fragment fragment) {
        if (null == fragment) {
            return;
        }
        getSupportFragmentManager().beginTransaction().add(layoutFragmentId, fragment).commit();
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(false, CommonUtil.getString(R.string.text_loading));
    }

    @Override
    public void showProgressDialog(boolean isCancel,String message) {
        loadingProgressDialog = new LoadingProgressDialog(this, isCancel, message);
        loadingProgressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        if (loadingProgressDialog!=null&&loadingProgressDialog.isShowing())loadingProgressDialog.dismiss();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.splash_fade_in, R.anim.splash_fade_out);
    }

}

package com.irenshi.personneltreasure.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.irenshi.personneltreasure.application.PersonnelTreasureApplication;

/**
 * Created by mike.li on 2018/5/4.
 */

public class KeyBoardUtil {
    public interface KeyBoardStatusListener{
        void onKeyBoardStateChanged(boolean isShowKeyBoard, final int keyBoardTop);
    }
    public static void register(final Activity activity, final KeyBoardStatusListener keyBoardStatusListener){
        final View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                //视图变化
                int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);
                int keyBoardTop = DeviceUtil.getDisplayHeight(activity) - heightDiff;
                //加虚拟键的高度
                Resources res = activity.getResources();
                int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    int result = res.getDimensionPixelSize(resourceId);
                    keyBoardTop = keyBoardTop+result;
                }
                if (heightDiff > 100) {
                    keyBoardStatusListener.onKeyBoardStateChanged(true,keyBoardTop);
                } else {
                    keyBoardStatusListener.onKeyBoardStateChanged(false,keyBoardTop);
                }
            }
        });
    }
    public static void showKeyBoard(View view){
        InputMethodManager imm = (InputMethodManager) PersonnelTreasureApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
        }
    }
    public static void hideKeyBoard(View view){
        InputMethodManager imm = (InputMethodManager) PersonnelTreasureApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 强制隐藏软键盘
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

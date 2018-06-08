package com.irenshi.personneltreasure.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.irenshi.personneltreasure.application.PersonnelTreasureApplication;

/**
 * Created by mike.li on 2018/5/15.
 */

public class CommonUtil {
    public static String getString(@StringRes int id) {
        return PersonnelTreasureApplication.getInstance().getString(id);
    }

    public static int getColor(@ColorRes int colorId) {
        return ContextCompat.getColor(PersonnelTreasureApplication.getInstance(),colorId);
    }
    public static Drawable getDrawable(@DrawableRes int drawableId) {
        return ContextCompat.getDrawable(PersonnelTreasureApplication.getInstance(),drawableId);
    }
}

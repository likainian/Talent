package com.irenshi.personneltreasure.util;

import android.widget.EditText;

import java.util.List;

/**
 * Created by mike.li on 2018/4/27.
 */

public class EmptyUtil {

    public static boolean isEmpty(String string){
        return string==null||string.trim().length()==0;
    }
    public static boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length()==0;
    }
    public static boolean isEmpty(List list){
        return list==null||list.size()==0;
    }
}

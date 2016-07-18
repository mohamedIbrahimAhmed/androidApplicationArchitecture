package com.example.rxandroid.common.validation;

import android.text.TextUtils;

/**
 * Created by mohamed.ibrahim on 7/18/2016.
 */

public class ValidationHelper {
    private static final String NOT_EMPTY = "@\"^(?!\\s*$).+\"";
    private static final String NUMBER_ONLY = "[0-9]+";


    public static boolean isEmpty(String what) {
        return TextUtils.isEmpty(what);
    }

    public static boolean isNumber(String what) {
        return !TextUtils.isEmpty(what) && TextUtils.isDigitsOnly(what);
    }

}

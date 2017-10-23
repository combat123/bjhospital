package com.xnfh.bjhospital.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangxuewei on 2017/10/23.
 */
public class SharePrefUtil {
    private Context context;
    private static SharePrefUtil sharePrefUtil;
    private SharedPreferences sharedPreferences;


    private SharePrefUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
    }
    public static SharePrefUtil getInstance(Context context) {
        synchronized (SharePrefUtil.class) {
            if(sharePrefUtil == null) {
                sharePrefUtil = new SharePrefUtil(context);
            }
            return sharePrefUtil;
        }
    }

    public void saveStringData(String key,String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,value);
        edit.apply();
    }

    public String getStringData(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveIntegerData(String key,Integer value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key,value);
        edit.apply();
    }

    public Integer getIntegerData(String key) {
        return sharedPreferences.getInt(key,0);
    }

    public void saveBooleanData(String key,boolean value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,value);
        edit.apply();
    }

    public boolean getBooleanData(String key) {
        return sharedPreferences.getBoolean(key,false);
    }


}

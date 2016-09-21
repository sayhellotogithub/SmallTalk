package com.iblogstreet.smalltalk.utils;

import android.util.Log;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.utils
 *  @文件名:   LogUtils
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 18:05
 *  @描述：    TODO
 */
public class LogUtils {
    private static final String TAG = "LogUtils";
    private static boolean isDebug=true;
    public static void logD(String tag,String msg){
        if(isDebug)
            Log.d(tag,msg);
    }
}

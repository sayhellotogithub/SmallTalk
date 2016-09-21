package com.iblogstreet.smalltalk.utils;

import android.content.Context;
import android.os.Handler;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.utils
 *  @文件名:   UiUtils
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 18:52
 *  @描述：    TODO
 */
public class UiUtils {
    private static final String TAG = "UiUtils";
    private static Context  mContext;
    private static Handler mHandler;
    public  static void init(Context context){
       mContext=context.getApplicationContext();
        mHandler=new Handler();
    }
    public static void post(Runnable runnable){
        mHandler.post(runnable);
    }
    public static void postDelayed(Runnable runnable,long time){
        mHandler.postDelayed(runnable,time);
    }
}

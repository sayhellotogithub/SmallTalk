package com.iblogstreet.smalltalk.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;

import com.iblogstreet.smalltalk.R;
import com.iblogstreet.smalltalk.base.BaseActivity;
import com.iblogstreet.smalltalk.utils.UiUtils;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.ui.activity
 *  @文件名:   SplashActivity
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 18:46
 *  @描述：    Spalsh界面
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    ProgressDialog mPb;
    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        mPb.show();
        UiUtils.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPb.dismiss();
                finish();
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        },3000);
    }

    @Override
    protected void initView() {
        mPb = makeProgressDialog("正在加载中", "请稍候");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void processClick(View view) {

    }
}

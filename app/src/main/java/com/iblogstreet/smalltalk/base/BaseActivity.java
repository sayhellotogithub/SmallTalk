package com.iblogstreet.smalltalk.base;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.iblogstreet.smalltalk.R;
import com.iblogstreet.smalltalk.utils.LogUtils;

import butterknife.ButterKnife;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.base
 *  @文件名:   BaseActivity
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 17:50
 *  @描述：    基类的Activity
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "BaseActivity";
     FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initBack();
        initData();
        initEvent();
        initFragmentManager();

    }
    private void initBack(){
        View mBack=findViewById(R.id.back);
        if(mBack!=null){
            mBack.setOnClickListener(this);
        }
    }

    private void initFragmentManager(){
        mFm=getFragmentManager();
    }

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            default:
                processClick(view);
                break;
        }
    }

    /**
     * 处理点击事件
     * @param view
     */
    protected abstract void processClick(View view);

    protected  void toast(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void logD(String msg){
       LogUtils.logD(getClass().getName(),msg);
    }
    protected ProgressDialog makeProgressDialog(String title,String content){
        ProgressDialog pb=new ProgressDialog(this);
        pb.setMessage(content);
        pb.setTitle(title);
        return pb;
    }

}

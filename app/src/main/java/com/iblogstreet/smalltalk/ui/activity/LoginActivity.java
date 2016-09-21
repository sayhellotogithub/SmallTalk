package com.iblogstreet.smalltalk.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iblogstreet.smalltalk.R;
import com.iblogstreet.smalltalk.base.BaseActivity;
import com.iblogstreet.smalltalk.utils.StringUtils;

import butterknife.Bind;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.ui.activity
 *  @文件名:   LoginActivity
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 18:58
 *  @描述：    登录的Activity
 */
public class LoginActivity
        extends BaseActivity
        implements TextView.OnEditorActionListener
{
    private static final String TAG = "LoginActivity";
    @Bind(R.id.btn_login)
    Button   mBtnLogin;
    @Bind(R.id.tv_register)
    TextView mTvRegister;
    @Bind(R.id.et_username)
    EditText mEtUsername;
    @Bind(R.id.et_pwd)
    EditText mEtPwd;

    @Override
    protected void initEvent() {
        mBtnLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mEtUsername.setOnEditorActionListener(this);
        mEtPwd.setOnEditorActionListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void processClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                onLogin();
                break;
            case R.id.tv_register:
                gotoRegister();
                break;
        }
    }

    private void gotoRegister() {
        finish();
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    private void onLogin() {
        //隐藏输入法
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mEtUsername.getWindowToken(), 0);
        if(!checkUserName()){

        }else if(!checkPwd()){

        }
        else {
            //登录，发启网络请求
            //如果成功，则跳转到主页，否则提示失败
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //输入用户名后，点击next
        if(v.getId()==R.id.et_username&&actionId== EditorInfo.IME_ACTION_NEXT){
            if (checkUserName()) { return false; }

        }
        else if(v.getId()==R.id.et_pwd&&actionId==EditorInfo.IME_ACTION_GO){
            if (checkPwd()) { return false; }
        }
        return true;
    }

    private boolean checkPwd() {//如果不为空
        String pwd=mEtPwd.getText().toString().trim();
        if(TextUtils.isEmpty(pwd)){
            toast("密码不能为空");
        }
        else if(!StringUtils.checkPwd(pwd)){
            toast("密码只能由数字组合，且长度范围（3-20）");
        }else{
            return true;
        }
        return false;
    }

    private boolean checkUserName() {//这里作判断
        //如果不为空
        String userName=mEtUsername.getText().toString().trim();
        if(TextUtils.isEmpty(userName)){
            toast("用户名不能为空");
        }
        else if(!StringUtils.checkUserName(userName)){
            toast("用户名必须以字母开头，且只能以字母和数字组合，长度范围为3-20");
        }else{
            return true;
        }
        return false;
    }
}

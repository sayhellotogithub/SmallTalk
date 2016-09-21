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

import com.hyphenate.chat.EMClient;
import com.iblogstreet.smalltalk.R;
import com.iblogstreet.smalltalk.base.BaseActivity;
import com.iblogstreet.smalltalk.table.Account;
import com.iblogstreet.smalltalk.utils.StringUtils;

import butterknife.Bind;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.ui.activity
 *  @文件名:   RegisterActivity
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 19:22
 *  @描述：    注册Activity
 */
public class RegisterActivity
        extends BaseActivity
        implements TextView.OnEditorActionListener
{
    private static final String TAG = "RegisterActivity";
    @Bind(R.id.et_username)
    EditText mEtUsername;
    @Bind(R.id.et_pwd)
    EditText mEtPwd;
    @Bind(R.id.btn_register)
    Button   mBtnRegister;
    @Bind(R.id.tv_login)
    TextView mTvLogin;

    @Override
    protected void initEvent() {
        mBtnRegister.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
        mEtPwd.setOnEditorActionListener(this);
        mEtUsername.setOnEditorActionListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void processClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                onRegister();
                break;
            case R.id.tv_login:
                gotoLogin();
                break;
        }
    }

    private void gotoLogin() {
        finish();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    private void onRegister() {
        //隐藏输入法
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mEtUsername.getWindowToken(), 0);
        if(!checkUserName()){

        }else if(!checkPwd()){

        }
        else {
            //登录，发启网络请求
            //如果成功，则跳转到主页，否则提示失败
            final Account p2 = new Account();
            p2.setName(mEtUsername.getText().toString().trim()+"");
            p2.setPwd(mEtPwd.getText().toString().trim()+"");
            p2.save(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    toast("添加数据成功，返回objectId为："+p2.getObjectId());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //数据添加成功，要创建环信账号
                            //注册失败会抛出HyphenateException
                            try {
                                EMClient.getInstance().createAccount("aaa", "12323");//同步方法
                                //环信注册成功
                                toast("环信注册成功");

                            } catch (Exception e) {
                                e.printStackTrace();

                                //出现异常，注册失败
                                toast("环信注册失败");
                                p2.delete(RegisterActivity.this, p2.getObjectId(), new DeleteListener() {
                                    @Override
                                    public void onSuccess() {
                                        toast("Bmob删除用户成功");
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        toast("Bmob删除用户失败");
                                    }
                                });
                            }
                        }
                    }).start();


                }

                @Override
                public void onFailure(int i, String s) {
                    toast("创建数据失败：" + s);
                }
            });
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

package com.iblogstreet.smalltalk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 *  @项目名：  SmallTalk 
 *  @包名：    com.iblogstreet.smalltalk.base
 *  @文件名:   BaseFragment
 *  @创建者:   Army
 *  @创建时间:  2016/9/16 18:08
 *  @描述：Fragment的基类
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    protected abstract int getLayoutId();
}

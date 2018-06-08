package com.irenshi.personneltreasure.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mike.li on 2018/5/7.
 */

public abstract class BaseFragment extends Fragment implements BaseContract.BaseView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }
    protected abstract @LayoutRes int getLayout();

    @Override
    public boolean isActive() {
        return !this.isDetached()&&this.isAdded();
    }

    protected void addChildFragment(Fragment fragment, @IdRes int layoutFragmentId) {
        if (null == fragment) {
            return;
        }
        this.getChildFragmentManager().beginTransaction().add(layoutFragmentId, fragment).commit();
    }

    @Override
    public void showProgressDialog() {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.showProgressDialog();
    }

    @Override
    public void showProgressDialog(boolean isCancel, String message) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.showProgressDialog(isCancel,message);
    }

    @Override
    public void closeProgressDialog() {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.closeProgressDialog();
    }
}

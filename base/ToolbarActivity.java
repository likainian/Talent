package com.irenshi.personneltreasure.base;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irenshi.personneltreasure.R;

public abstract class ToolbarActivity extends BaseActivity{

    private ImageView mToolbarLeft;
    private TextView mToolbarMiddle;
    private TextView mToolbarRight;
    private RelativeLayout mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolbarView();
    }

    protected abstract int getLayoutId();

    protected void initToolbarView() {
        mToolbarLeft = (ImageView) findViewById(R.id.toolbar_left);
        mToolbarMiddle = (TextView) findViewById(R.id.toolbar_middle);
        mToolbarRight = (TextView) findViewById(R.id.toolbar_right);
        mToolbar = (RelativeLayout) findViewById(R.id.toolbar);
        if(mToolbarLeft!=null)mToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setToolbarMiddleText(String title){
        if(mToolbarMiddle!=null)mToolbarMiddle.setText(title);
    }
    public void setToolbarLeftResource(@DrawableRes int resId){
        if(mToolbarLeft!=null)mToolbarLeft.setImageResource(resId);
    }
    public void setToolbarRightText(String title){
        if(mToolbarRight!=null)mToolbarRight.setText(title);
    }
    public void setToolbarBackgroundColor(@ColorInt int color){
        if(mToolbar!=null)mToolbar.setBackgroundColor(color);
    }
    public void showToolbar(){
        if(mToolbar!=null)mToolbar.setVisibility(View.VISIBLE);
    }
    public void hideToolbar(){
        if(mToolbar!=null)mToolbar.setVisibility(View.GONE);
    }
}

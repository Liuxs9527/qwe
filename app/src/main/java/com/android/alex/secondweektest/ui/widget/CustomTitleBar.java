package com.android.alex.secondweektest.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.alex.secondweektest.R;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public class CustomTitleBar extends LinearLayout implements View.OnClickListener {
    private int mColor;
    OnCallBackLisenter onCallBackLisenter;

    //接口回调
    public interface OnCallBackLisenter{
        public void onCallBack();
    };

    public void setOnCallBackLisenter(OnCallBackLisenter onCallBackLisenter){
        this.onCallBackLisenter = onCallBackLisenter;
    }
    OnMenuCallBackLisenter onMenuCallBackLisenter;

    //接口回调
    public interface OnMenuCallBackLisenter{
        public void onMenuCallBack();
    };

    public void setOnMenuCallBackLisenter(OnMenuCallBackLisenter onMenuCallBackLisenter){
        this.onMenuCallBackLisenter = onMenuCallBackLisenter;
    }



    public CustomTitleBar(Context context) {
        super(context);
    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View rootView = LayoutInflater.from(context).inflate(R.layout.title_bar_item, this);
        TextView tv_custom_title = rootView.findViewById(R.id.tv_custom_title);
        ImageView iv_back = rootView.findViewById(R.id.iv_back);
        ImageView iv_menu = rootView.findViewById(R.id.iv_menu);
        iv_back.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (index){
                case R.styleable.CustomTitleBar_mColor:
                    mColor = typedArray.getColor(i, Color.GREEN);
                    tv_custom_title.setTextColor(mColor);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onCallBackLisenter.onCallBack();
                break;
            case R.id.iv_menu:
                onMenuCallBackLisenter.onMenuCallBack();
                break;
        }
    }
}

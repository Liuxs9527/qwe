package com.android.alex.secondweektest.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.android.alex.secondweektest.R;
import com.android.alex.secondweektest.data.beans.DetailBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public class GoodsAdapter extends BaseQuickAdapter<DetailBean.DataBean.ListBean, BaseViewHolder> {
    public GoodsAdapter(int layoutResId, @Nullable List<DetailBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goods_title, item.getName());
        //Glide加载显示图片
        ImageView iv_goods_icon = helper.getView(R.id.iv_goods_icon);
        Glide.with(mContext).load(item.getIcon()).into(iv_goods_icon);
    }
}

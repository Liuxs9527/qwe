package com.android.alex.secondweektest.ui.adapter;

import android.support.annotation.Nullable;

import com.android.alex.secondweektest.R;
import com.android.alex.secondweektest.data.beans.CategoryBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public class CategoryAdapter extends BaseQuickAdapter<CategoryBean.DataBean, BaseViewHolder> {
    public CategoryAdapter(int layoutResId, @Nullable List<CategoryBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean.DataBean item) {
        helper.setText(R.id.tv_category, item.getName());
    }
}

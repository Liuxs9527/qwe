package com.android.alex.secondweektest.di.presenter;

import com.android.alex.secondweektest.di.contract.ICategoryContract;
import com.android.alex.secondweektest.di.model.CategoryModel;

import java.lang.ref.SoftReference;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public class CategoryPresenter implements ICategoryContract.IPresenter<ICategoryContract.IView> {
    ICategoryContract.IView categoryView;
    private SoftReference<ICategoryContract.IView> softReference;
    private ICategoryContract.IModel model;

    @Override
    public void attachView(ICategoryContract.IView categoryView) {
        this.categoryView = categoryView;
        //防止内存泄漏
        softReference = new SoftReference<>(categoryView);
        //获取M层
        model = new CategoryModel();
    }

    @Override
    public void detachView(ICategoryContract.IView categoryView) {
        softReference.clear();
    }

    @Override
    public void requestCategoryData() {
        model.containCategoryData(new ICategoryContract.IModel.OnCallBack() {
            @Override
            public void onCallBack(String reponseData) {
                categoryView.showCategoryData(reponseData);
            }
        });
    }

    @Override
    public void requestDetailData(int cid) {
        model.containDetailData(cid,new ICategoryContract.IModel.OnCallBack() {
            @Override
            public void onCallBack(String reponseData) {
                categoryView.showDetailData(reponseData);
            }
        });
    }
}

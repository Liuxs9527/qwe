package com.android.alex.secondweektest.di.contract;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public interface ICategoryContract {
    //V层
    public interface IView {
        //展示分类信息
        public void showCategoryData(String reponseData);

        //展示详情数据
        public void showDetailData(String reponseData);
    }

    //P层
    public interface IPresenter<IView> {
        //绑定
        public void attachView(IView categoryView);

        //解绑
        public void detachView(IView categoryView);

        //请求分类数据
        public void requestCategoryData();

        //请求详情
        public void requestDetailData(int cid);
    }

    //M层
    public interface IModel {
        //请求详情数据
        public void containCategoryData(OnCallBack onCallBack);

        //请求详情数据
        public void containDetailData(int cid, OnCallBack onCallBack);

        //回调
        public interface OnCallBack {
            public void onCallBack(String reponseData);
        }
    }
}

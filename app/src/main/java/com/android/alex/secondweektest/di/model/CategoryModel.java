package com.android.alex.secondweektest.di.model;

import com.android.alex.secondweektest.data.Constant;
import com.android.alex.secondweektest.data.utils.OKHttpUtil;
import com.android.alex.secondweektest.di.contract.ICategoryContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Alex
 * @date 2019/1/7.
 * GitHub：https://github.com/wangshuaialex
 */
public class CategoryModel implements ICategoryContract.IModel {
    @Override
    public void containCategoryData(final OnCallBack onCallBack) {
        OKHttpUtil
                .getInstance()
                .get(Constant.CATEGORY_URL, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //错误的数据
                        String errorMsg = e.getMessage();
                        onCallBack.onCallBack(errorMsg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //请求得到的数据
                        String reponseData = response.body().string();
                        //回调
                        onCallBack.onCallBack(reponseData);
                    }
                });
    }

    @Override
    public void containDetailData(int cid, final OnCallBack onCallBack) {
        OKHttpUtil.getInstance().get(Constant.DETAIL_URL + "?cid=" + cid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //错误的数据
                String errorMsg = e.getMessage();
                onCallBack.onCallBack(errorMsg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求得到的数据
                String reponseData = response.body().string();
                //回调
                onCallBack.onCallBack(reponseData);
            }
        });
    }
}

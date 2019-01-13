package com.android.alex.secondweektest.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.alex.secondweektest.R;
import com.android.alex.secondweektest.data.beans.CategoryBean;
import com.android.alex.secondweektest.data.beans.DetailBean;
import com.android.alex.secondweektest.di.contract.ICategoryContract;
import com.android.alex.secondweektest.di.presenter.CategoryPresenter;
import com.android.alex.secondweektest.ui.adapter.CategoryAdapter;
import com.android.alex.secondweektest.ui.adapter.DetailAdapter;
import com.android.alex.secondweektest.ui.widget.CustomTitleBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity implements ICategoryContract.IView {

    @BindView(R.id.rv_categroy)
    RecyclerView rvCategroy;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.ctb_title_bar)
    CustomTitleBar ctbTitleBar;
    private ICategoryContract.IPresenter presenter;
    private CategoryAdapter categoryAdapter;
    private Context context;
    private List<CategoryBean.DataBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        //创建P层
        presenter = new CategoryPresenter();
        //触发请求动作
        presenter.attachView(this);
        presenter.requestCategoryData();
        ctbTitleBar.setOnCallBackLisenter(new CustomTitleBar.OnCallBackLisenter() {
            @Override
            public void onCallBack() {
                CategoryActivity.this.finish();
            }
        });
        ctbTitleBar.setOnMenuCallBackLisenter(new CustomTitleBar.OnMenuCallBackLisenter() {
            @Override
            public void onMenuCallBack() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ctbTitleBar, "alpha", 0.1f, 0.8f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
            }
        });
    }

    @Override
    public void showCategoryData(final String reponseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                CategoryBean categoryBean = gson.fromJson(reponseData, CategoryBean.class);
                //数据源
                beanList = categoryBean.getData();
                //布局管理器
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rvCategroy.setLayoutManager(manager);
                //适配器
                categoryAdapter = new CategoryAdapter(R.layout.category_item, beanList);
                rvCategroy.setAdapter(categoryAdapter);
                categoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        //获取请求的参数cid
                        int cid = beanList.get(position).getCid();
                        //点击子条目时动作触发，请求详情条目
                        presenter.requestDetailData(cid);
                    }
                });
            }
        });

    }

    @Override
    public void showDetailData(final String reponseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                DetailBean detailBean = gson.fromJson(reponseData, DetailBean.class);
                List<DetailBean.DataBean> detailBeanData = detailBean.getData();
                //布局管理器
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rvDetail.setLayoutManager(manager);
                //设置适配器
                DetailAdapter detailAdapter = new DetailAdapter(R.layout.detail_item, detailBeanData);
                rvDetail.setAdapter(detailAdapter);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }


}

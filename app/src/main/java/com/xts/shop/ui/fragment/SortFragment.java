package com.xts.shop.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.xts.shop.R;
import com.xts.shop.bean.CataLogItemBean;
import com.xts.shop.bean.CatalogBean;
import com.xts.shop.net.ApiService;
import com.xts.shop.view.MySelftGridView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SortFragment extends Fragment {


    private VerticalTabLayout mTablayout;
    private ImageView mImageTop;
    private TextView mTitle;
    private TextView mTitleType;
    private LinearLayout mContent;

    public static SortFragment newInstance() {
        SortFragment fragment = new SortFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_sort, container, false);

        initView(inflate);
        initData();
        initRightData();


        return inflate;
    }

    /**
     * 获取右边数据
     */
    private void initRightData() {
        Retrofit build = new Retrofit.Builder().baseUrl(ApiService.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = build.create(ApiService.class);
        apiService.getRightDataList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CataLogItemBean>() {
                    @Override
                    public void accept(CataLogItemBean catalogBean) throws Exception {


                        initRightView(  catalogBean);

                    }
                });


    }

    private void initRightView(CataLogItemBean catalogBean) {


        CataLogItemBean.DataBean.CurrentCategoryBean currentCategory = catalogBean.getData().getCurrentCategory();

        Glide.with(getActivity()).load(currentCategory.getWap_banner_url()).into(mImageTop);
        mTitle.setText(currentCategory.getFront_name());
        mTitleType.setText(catalogBean.getData().getCurrentCategory().getName());

        MySelftGridView mySelftGridView = new MySelftGridView(getActivity());
        mySelftGridView.initMyGridView(catalogBean.getData().getCurrentCategory().getSubCategoryList());

        mContent.addView(mySelftGridView);
    }

    @SuppressLint("CheckResult")
    private void initData() {

        Retrofit build = new Retrofit.Builder().baseUrl(ApiService.sBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = build.create(ApiService.class);
        apiService.getLeftDataList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CatalogBean>() {
                    @Override
                    public void accept(CatalogBean catalogBean) throws Exception {

                        List<CatalogBean.DataBean.CategoryListBean> categoryList = catalogBean.getData().getCategoryList();

                        mTablayout.setTabAdapter(new TabAdapter() {
                            @Override
                            public int getCount() {
                                return categoryList.size();
                            }

                            @Override
                            public TabView.TabBadge getBadge(int position) {
                                return null;
                            }

                            @Override
                            public TabView.TabIcon getIcon(int position) {
                                return null;
                            }

                            @Override
                            public TabView.TabTitle getTitle(int position) {
                                CatalogBean.DataBean.CategoryListBean categoryListBean = categoryList.get(position);
                                return new  TabView.TabTitle.Builder().setContent(categoryListBean.getName()).build();
                            }

                            @Override
                            public int getBackground(int position) {
                                return 0;
                            }
                        });
                    }
                });

    }

    private void initView(@NonNull final View itemView) {
        mTablayout = (VerticalTabLayout) itemView.findViewById(R.id.tablayout);
        mImageTop = (ImageView) itemView.findViewById(R.id.top_image);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mTitleType = (TextView) itemView.findViewById(R.id.type_title);
        mContent = (LinearLayout) itemView.findViewById(R.id.content);

        mTablayout.addTab(new QTabView(getActivity()));
        mTablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {

            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });


    }


}

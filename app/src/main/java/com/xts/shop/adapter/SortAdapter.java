package com.xts.shop.adapter;

import android.graphics.Color;

import com.xts.shop.bean.CatalogBean;

import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;

public class SortAdapter implements TabAdapter {

    List<CatalogBean.DataBean.CategoryListBean> categoryList;
    public SortAdapter(List<CatalogBean.DataBean.CategoryListBean> categoryList) {
        this.categoryList = categoryList;

    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {


        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {
        return new QTabView.TabTitle.Builder()
                .setContent(categoryList.get(position).getName())
                .setTextColor(Color.BLUE, Color.BLACK)
                .build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }
}

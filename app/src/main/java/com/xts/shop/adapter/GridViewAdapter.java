package com.xts.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xts.shop.R;
import com.xts.shop.bean.CataLogItemBean;
import com.xts.shop.bean.CatalogBean;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    Context context;
    List<CataLogItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> categoryList = new ArrayList<>();

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    private static final String TAG = "GridViewAdapter";
    public void setCategoryList(List<CataLogItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> categoryList) {
        if (null==categoryList){

            Log.d(TAG, "setCategoryList: -------------null--------------");
            return;
        }
        this.categoryList.clear();
        this.categoryList .addAll(categoryList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CataLogItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBeanX = categoryList.get(i);

        View root = LayoutInflater.from(context).inflate(R.layout.grid_item,null);
        ImageView img = root.findViewById(R.id.imageView);
        TextView title = root.findViewById(R.id.title);
        Glide.with(context).load(subCategoryListBeanX.getWap_banner_url()).into(img);

        title.setText(subCategoryListBeanX.getName());
        return root;
    }

    public void setNUll() {
        this.categoryList.clear();
        notifyDataSetChanged();
    }
}

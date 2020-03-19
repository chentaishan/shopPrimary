package com.xts.shop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.xts.shop.R;
import com.xts.shop.bean.CataLogItemBean;

import java.util.List;

public class MySelftGridView extends LinearLayout
{

    private LinearLayout linearLayout;

    public MySelftGridView(Context context) {
        super(context);
        init();
    }

    public MySelftGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySelftGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init() {

        setOrientation(VERTICAL);
    }

    /**
     * 列数
     */
    int columnuNum = 3;

    public void initMyGridView(List<CataLogItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean>  itemList){


        for (int i = 0; i < itemList.size(); i++) {

            CataLogItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBean = itemList.get(i);

            View item = LayoutInflater.from(getContext()).inflate(R.layout.sort_item,null);
            ImageView img  = item.findViewById(R.id.img);
            TextView subTitle  = item.findViewById(R.id.sub_title);
            Glide.with(getContext()).load(subCategoryListBean.getWap_banner_url()).into(img);
            subTitle.setText(subCategoryListBean.getName());

            LinearLayout.LayoutParams layoutParams =  new  LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            // 为了item 等分
            layoutParams.weight= 1;
            item.setLayoutParams(layoutParams);


            if (i%3==0){

                linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(HORIZONTAL);
                linearLayout.addView(item);


                addView(linearLayout);
            }else{


                linearLayout.addView(item);
            }
        }

    }

}

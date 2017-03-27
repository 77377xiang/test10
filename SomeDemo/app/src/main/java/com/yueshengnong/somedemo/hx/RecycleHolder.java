package com.yueshengnong.somedemo.hx;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by PersonalFolder on 17/2/9.
 */
public class RecycleHolder extends RecyclerView.ViewHolder {

    /** 用于存储当前item当中的View */
    private SparseArray<View> mViews;
    private Context mContext;

    public RecycleHolder(Context context,View itemView) {
        super(itemView);
        this.mContext=context;
        mViews = new SparseArray<View>();
    }

    public <T extends View> T findView(int ViewId) {
        View view = mViews.get(ViewId);
        //集合中没有，则从item当中获取，并存入集合当中
        if (view == null) {
            view = itemView.findViewById(ViewId);
            mViews.put(ViewId, view);
        }
        return (T) view;
    }

    public RecycleHolder setText(int viewId, String text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleHolder setText(int viewId, int text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleHolder setImageResource(int viewId, int ImageId) {
        ImageView image = findView(viewId);
        image.setImageResource(ImageId);
        return this;
    }

    public RecycleHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView= findView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }
    public RecycleHolder setImageNet(int viewId, String url) {
        ImageView imageView= (ImageView) findView(viewId);
        //使用你所用的网络框架等,这里使用imageloader
        Glide.with(mContext).load(url).dontAnimate().into(imageView);//控件
        return this;
    }
}

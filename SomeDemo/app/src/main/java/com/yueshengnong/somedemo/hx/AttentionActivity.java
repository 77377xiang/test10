package com.yueshengnong.somedemo.hx;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yueshengnong.somedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PersonalFolder on 17/3/7.
 */
public class AttentionActivity extends AppCompatActivity {

    private XRecyclerView attentionRv;
    private RecyclerAdapter<Attention> attentionAdapter;
    private ImageView backIv;
    private TextView titleTv;
    private List<Attention> attentionList;
    private String img = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1488858167&di=0a6d7668c9ef53ee75873f540b2e6600&src=http://pic.35pic.com/normal/00/55/11/1363360_173955077_2.jpg";


//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                Attention attention;
//                for (int i = 0; i < 3; i++) {
//                    attention = new Attention();
//                    attention.setImgUrl(img);
//                    attention.setTitle("西溪北苑" + i + "号楼");
//                    attention.setLocaiton("浙江杭州市");
//                    attention.setEara("面积1" + i + "0平方");
//                    if (i % 3 == 0) {
//                        attention.setTime("一拍中");
//                    } else {
//                        attention.setTime("开拍时间:2017年3月7日" + i + ":30");
//                    }
//                    attentionList.add(attention);
//                }
//            }
//            if (msg.what == 2) {
//                Attention attention;
//                for (int i = 0; i < 6; i++) {
//                    attention = new Attention();
//                    attention.setImgUrl(img);
//                    attention.setTitle("西溪北苑" + i + "号楼");
//                    attention.setLocaiton("浙江杭州市");
//                    attention.setEara("面积1" + i + "0平方");
//                    if (i % 3 == 0) {
//                        attention.setTime("一拍中");
//                    } else {
//                        attention.setTime("开拍时间:2017年3月7日" + i + ":30");
//                    }
//                    attentionList.add(attention);
//                }
//            }
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        initData();
        initView();


    }

    private void initData() {
        attentionList = new ArrayList<>();
        Attention attention;
        for (int i = 0; i < 10; i++) {
            attention = new Attention();
            attention.setImgUrl(img);
            attention.setTitle("西溪北苑" + i + "号楼");
            attention.setLocaiton("浙江杭州市");
            attention.setEara("面积1" + i + "0平方");
            if (i % 3 == 0) {
                attention.setTime("一拍中");
            } else {
                attention.setTime("开拍时间:2017年3月7日" + i + ":30");
            }
            attentionList.add(attention);
        }


    }

    private void initView() {
        attentionRv = (XRecyclerView) findViewById(R.id.attention_rv);
        backIv = (ImageView) findViewById(R.id.head_back);
        titleTv = (TextView) findViewById(R.id.head_title);
        titleTv.setText("特别关注");
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        attentionRv.setLayoutManager(new LinearLayoutManager(this));
        //这句就是添加我们自定义的分隔线
        attentionRv.addItemDecoration(new LineDecoration(this, LineDecoration.VERTICAL_LIST));
        attentionRv.setAdapter(attentionAdapter = new RecyclerAdapter<Attention>(this, attentionList, R.layout.item_attention) {
            @Override
            public void convert(RecycleHolder holder, Attention data, int position) {
                holder.setImageNet(R.id.attention_iv, data.getImgUrl());
                holder.setText(R.id.attention_title, data.getTitle());
                holder.setText(R.id.attention_location, data.getLocaiton());
                holder.setText(R.id.attention_eara, data.getEara());
                holder.setText(R.id.attention_time, data.getTime());
            }
        });


        attentionRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        attentionList.clear();
//                        for (int i = 0; i < 15; i++) {
//                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
//                        }
                        Attention attention;
                        for (int i = 0; i < 16; i++) {
                            attention = new Attention();
                            attention.setImgUrl(img);
                            attention.setTitle("西溪北苑" + i + "号楼");
                            attention.setLocaiton("浙江杭州市");
                            attention.setEara("面积1" + i + "0平方");
                            if (i % 3 == 0) {
                                attention.setTime("一拍中");
                            } else {
                                attention.setTime("开拍时间:2017年3月7日" + i + ":30");
                            }
                            attentionList.add(attention);
                        }


                        attentionAdapter.notifyDataSetChanged();
                        attentionRv.refreshComplete();
                    }

                }, 3000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        for (int i = 0; i < 15; i++) {
//                            listData.add("item" + (i + listData.size()));
//                        }
                        Attention attention;
                        for (int i = 0; i < 16; i++) {
                            attention = new Attention();
                            attention.setImgUrl(img);
                            attention.setTitle("西溪北苑" + i + "号楼");
                            attention.setLocaiton("浙江杭州市");
                            attention.setEara("面积1" + i + "0平方");
                            if (i % 3 == 0) {
                                attention.setTime("一拍中");
                            } else {
                                attention.setTime("开拍时间:2017年3月7日" + i + ":30");
                            }
                            attentionList.add(attention);
                        }
                        attentionAdapter.notifyDataSetChanged();
                        attentionRv.loadMoreComplete();
                    }
                }, 3000);


            }

        });


    }
}

package com.yueshengnong.somedemo.hx;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yueshengnong.somedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PersonalFolder on 17/3/8.
 */
public class AuctionActivity extends AppCompatActivity {

    private RecyclerView auctionRecordRv,auctionCountRv,auctionDetailRv;
    private List<Record> recordList;
    private List<Record> recordCountList;
    private List<Detail> detailList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        initData();
        initToolBar();
        initView();

    }

    private void initToolBar() {
        ImageView backIv = (ImageView) findViewById(R.id.head_back);
        TextView titleTv = (TextView) findViewById(R.id.head_title);
        titleTv.setText("郭靖私服");
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        auctionRecordRv= (RecyclerView) findViewById(R.id.auction_record_rv);
        auctionCountRv= (RecyclerView) findViewById(R.id.auction_count_rv);
        auctionDetailRv= (RecyclerView) findViewById(R.id.auction_detail_rv);
        auctionRecordRv.setLayoutManager(new LinearLayoutManager(this));
        auctionCountRv.setLayoutManager(new LinearLayoutManager(this));
        auctionDetailRv.setLayoutManager(new LinearLayoutManager(this));
        auctionRecordRv.setAdapter(new RecyclerAdapter<Record>(this,recordList,R.layout.item_auction_1) {
            @Override
            public void convert(RecycleHolder holder, Record data, int position) {
                holder.setText(R.id.auction_status,data.getStatus());
                holder.setText(R.id.auction_code,data.getCode());
                holder.setText(R.id.auction_price,data.getPrice());
                holder.setText(R.id.auction_time,data.getTime());
            }
        });

        auctionCountRv.setAdapter(new RecyclerAdapter<Record>(this,recordCountList,R.layout.item_auction_1) {
            @Override
            public void convert(RecycleHolder holder, Record data, int position) {
                ((TextView)holder.findView(R.id.auction_status)).setTextColor(Color.BLACK);
                holder.setText(R.id.auction_status,data.getStatus());
                holder.setText(R.id.auction_code,data.getCode());
                holder.setText(R.id.auction_price,data.getPrice());
                holder.setText(R.id.auction_time,data.getTime());
            }
        });


        auctionDetailRv.setAdapter(new RecyclerAdapter<Detail>(this,detailList,R.layout.item_auction_2) {
            @Override
            public void convert(RecycleHolder holder, Detail data, int position) {
                holder.setImageNet(R.id.auction_2_iv,data.getImgUrl());
                holder.setText(R.id.auction_2_tv,data.getText());
            }
        });
    }

    private void initData() {
        recordList=new ArrayList<>();
        Record record1;
        for (int i = 0; i <2 ; i++) {
            record1=new Record();
            record1.setStatus("领先");
            record1.setCode("A1534");
            record1.setPrice(i+",200,3000");
            record1.setTime("2017/3/8/ 10:38");
            recordList.add(record1);
        }

        recordCountList=new ArrayList<>();
        Record record2;
        for (int i = 0; i <2 ; i++) {
            record2=new Record();
            record2.setStatus("一拍");
            record2.setCode("流拍");
            record2.setPrice(i+",200,3000");
            record2.setTime("2017/3/8/ 10:38");
            recordCountList.add(record2);
        }

        detailList=new ArrayList<>();
        Detail detail;
        for (int i = 0; i < 3; i++) {
            detail=new Detail();
            detail.setImgUrl("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=c253602d791ed21b66c929e59d6cddae/b151f8198618367a9f738e022a738bd4b21ce573.jpg");
            detail.setText("这是一份2017年1，2月份发布的25个最佳安卓库的列表，你应该会喜欢，虽然是按顺序排列的，但排名不分先后。让我们开始吧！");
            detailList.add(detail);
        }


    }
}

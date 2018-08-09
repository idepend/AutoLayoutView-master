package com.auto.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.widget.data.AutoData;
import com.auto.widget.view.AutoLayoutView;
import com.auto.widget.view.HorizontalScollTabHost;
import com.auto.widget.view.WarpLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private HorizontalScollTabHost tabHost;
    private List<String> mList;
    private String strTiele[] = new String[]{"头条", "推荐", "关注", "视频", "Java SE", "Android", "Html5", "我不喜欢"};
    private static String[] testName = {"日历", "360", "三国", "消除", "播放器",
            "游戏", "清理大师", "跑酷", "壁纸", "单机斗地主",
            "捕鱼达人3", "雷电2014(雷霆版)", "打车", "输入法"};
    private String title;

    //
    private WarpLinearLayout warpLinearLayout;

    private Button btn;

    private AutoLayoutView layoutView;
    private List<AutoData> dataList;
    private String autoName[] = new String[]{"降龙", "黯然", "左右", "七十", "拈花", "蛤蟆", "吸星", "打狗", "醉拳"};
//    String atuoUrl[]=new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(R.id.tabhost);
        mList = new ArrayList<>();
        for (int i = 0; i < strTiele.length; i++) {
            mList.add(strTiele[i]);
        }

        tabHost.diaplay(mList);

        initWarpData();

        initAutoIcon();
    }

    private void initWarpData() {
        btn = (Button) findViewById(R.id.btn);
        warpLinearLayout = (WarpLinearLayout) findViewById(R.id.warpLinearLayout);

        for (int j = 0; j < 11; j++) {
            int n = new Random().nextInt(10) + 5;
            StringBuffer stringBuffer = new StringBuffer();
            Random random = new Random();
            Log.i(TAG, "n=" + n);
            for (int i = 0; i < n; i++) {
                stringBuffer.append((char) (65 + random.nextInt(26)));
                Log.i(TAG, "StringBuffer=" + stringBuffer.toString());
            }
            final TextView tv = new TextView(MainActivity.this);
            tv.setText(stringBuffer.toString() + "000");
            tv.setBackgroundResource(R.drawable.shape_text_border);
            tv.setTextColor(getResources().getColor(R.color.black));
            tv.setPadding(10, 10, 10, 10);
            warpLinearLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "$ - " + tv.getText().toString() + " - $", Toast.LENGTH_SHORT).show();
                }
            });
        }


        /**
         * 单个添加
         */
        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = new Random().nextInt(10) + 5;
                StringBuffer stringBuffer = new StringBuffer();
                Random random = new Random();
                for (int i = 0; i < n; i++) {
                    stringBuffer.append((char) (65 + random.nextInt(26)));
                }
                TextView tv = new TextView(MainActivity.this);
                tv.setText(stringBuffer.toString() + "000");
                tv.setBackgroundResource(R.drawable.shape_text_border);
                tv.setPadding(10, 10, 10, 10);
                warpLinearLayout.addView(tv);
            }
        });*/

    }


    private void initAutoIcon() {
        layoutView = findViewById(R.id.autoView);
        dataList = new ArrayList<AutoData>();
        for (int i = 0; i < autoName.length; i++) {
            AutoData autoData = new AutoData();
            autoData.setName(autoName[i]);
            autoData.setUrl("http://qcloudimg.ichongxin.com/menuicon/qiguantubiao/yimiao.png");
            dataList.add(autoData);
        }

        layoutView.addData(dataList);

        layoutView.setOnFlowLayoutListener(new AutoLayoutView.FlowLayoutListener() {
            @Override
            public void onItemClick(View view, int poition) {
                Toast.makeText(MainActivity.this, "--> " + dataList.get(poition).getName() + " <--", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    private final int SWITCH_PAGE = 0x00123;
//
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case SWITCH_PAGE:
//                    //接收dialog点击以后得返回数据
//                    title = msg.getData().getString("type_name");
//                    Log.i(TAG, "handleMessage: " + title);
//                    break;
//            }
//        }
//    };
}

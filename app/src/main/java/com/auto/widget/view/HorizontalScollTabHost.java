package com.auto.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.widget.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by $USER_NAME on 2018/8/9 0009.
 * author : tomz  email:ibepend@126.com
 * 滑动添加的控件
 */
public class HorizontalScollTabHost extends LinearLayout implements ViewPager.OnPageChangeListener {

    private final static String TAG = "HorizontalScollTabHost";

    private Context mContext;

    private int mBgColor;
    private int mTextSize;

    private List<String> list;
    private List<Fragment> fragmentList;
    private List<TextView> topViews;
    private int count;

    private LinearLayout mMenuLayout;


    private HorizontalScrollView hscrollview;
    private ViewPager viewpager;

    public HorizontalScollTabHost(Context context) {
        this(context, null);
    }

    public HorizontalScollTabHost(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScollTabHost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        init(context, attrs);
    }


    /**
     * 初始化自定义属性和view
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScollTabhost);

        mBgColor = typedArray.getColor(R.styleable.HorizontalScollTabhost_top_background, 0x20999999);
        mTextSize = (int) typedArray.getDimension(R.styleable.HorizontalScollTabhost_textSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        typedArray.recycle();

        initView();


    }

    /**
     * 初始化view
     */
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.horizontal_scroll_tabhost, this, true);

        hscrollview = (HorizontalScrollView) view.findViewById(R.id.horizontalScrollView);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager_fragment);
        viewpager.addOnPageChangeListener(this);

        mMenuLayout = (LinearLayout) view.findViewById(R.id.layout_menu);

    }


    /**
     * 供调用者调用，保证数据独立
     *
     * @param list
     * @param fragments
     */
    public void diaplay(List<String> list, List<Fragment> fragments, Handler handler) {
        this.list = list;
        this.count = list.size();
        this.fragmentList = fragments;
        this.mHandler = handler;
        topViews = new ArrayList<>(count);
        drawUi();
    }


    public void diaplay(List<String> list, Handler handler) {
        this.list = list;
        this.count = list.size();
        this.mHandler = handler;
        topViews = new ArrayList<>(count);
        drawUi();
    }

    public void diaplay(List<String> list) {
        this.list = list;
        this.count = list.size();
        topViews = new ArrayList<>(count);
        drawUi();
    }

    /**
     * 绘制页面所有元素
     */
    private void drawUi() {

        drawHorizontal();


    }


    boolean isFalst = false;
    TextView[] textViews;
    int finalI;
    private Bundle bundle;
    private final int SELCT_SUCCESS = 0x00123;
    private Handler mHandler;
    private int typeId = 0;
    private List<Integer> listNum = new ArrayList<Integer>();

    /**
     * 绘制横向滑动菜单
     */
    private void drawHorizontal() {
//        mMenuLayout.setBackgroundColor(mBgColor);
        textViews = new TextView[count];

        for (int i = 0; i < count; i++) {
            final String menu = list.get(i);
            final TextView tv = (TextView) View.inflate(mContext, R.layout.news_top_tv_item, null);
            textViews[i] = tv;
            //这行代码很重要，
            textViews[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
//            textViews[i].setTextSize(15);
            textViews[i].setText(menu + " ");
            textViews[i].setTag(i);
            finalI = i;

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(15, 10, 0, 10);
            tv.setLayoutParams(lp);


            mMenuLayout.addView(tv);

            topViews.add(tv);

        }

        for (int j = 0; j < textViews.length; j++) {
            textViews[j].setTag(textViews);
            textViews[j].setOnClickListener(new LableClickListener());
        }

        //默认设置第一项为选中（news_top_tv_item.xml里面， android:textColor引用 drawable的selector）
        topViews.get(0).setSelected(true);
        textViews[0].setBackgroundResource(R.drawable.shape_border_nor);
        textViews[0].setTextColor(Color.parseColor("#E95D5C"));
    }

    /**
     * 这个是点击返回当前信息
     */
    class LableClickListener implements View.OnClickListener {

        public LableClickListener() {
        }

        @Override
        public void onClick(View v) {
            TextView[] textViews = (TextView[]) v.getTag();
            TextView tv = (TextView) v;
            for (int i = 0; i < textViews.length; i++) {
                //让点击的标签背景变成橙色，字体颜色变为白色
                if (tv.equals(textViews[i])) {
                    textViews[i].setBackgroundResource(R.drawable.shape_border_nor);
                    textViews[i].setTextColor(Color.parseColor("#E95D5C"));
                    Toast.makeText(mContext, tv.getText().toString() + "", Toast.LENGTH_SHORT).show();
                    //传递属性选择后的商品数据
//                    Message message = new Message();
//                    bundle = new Bundle();
//                    bundle.putString("type_name", tv.getText().toString());
//                    message.setData(bundle);
//                    message.what = SELCT_SUCCESS;
//                    mHandler.sendMessage(message);
                } else {
                    //其他标签背景变成白色，字体颜色为黑色
                    textViews[i].setBackgroundResource(R.drawable.shape_border_select);
                    textViews[i].setTextColor(Color.parseColor("#666666"));
                }
            }

            //点击移动到当前fragment
            viewpager.setCurrentItem(finalI);
            //点击让文字居中
            moveItemToCenter(tv);
        }

    }

    /**
     * 移动view对象到中间
     *
     * @param tv
     */
    private void moveItemToCenter(TextView tv) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int[] locations = new int[2];
        tv.getLocationInWindow(locations);
        int rbWidth = tv.getWidth();
        hscrollview.smoothScrollBy((locations[0] + rbWidth / 2 - screenWidth / 2),
                0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mMenuLayout != null && mMenuLayout.getChildCount() > 0) {

            for (int i = 0; i < mMenuLayout.getChildCount(); i++) {
                if (i == position) {
                    mMenuLayout.getChildAt(i).setSelected(true);
                } else {
                    mMenuLayout.getChildAt(i).setSelected(false);
                }
            }
        }

        //移动view，水平居中
        moveItemToCenter(topViews.get(position));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}

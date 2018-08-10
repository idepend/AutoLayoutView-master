package com.auto.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by $USER_NAME on 2018/8/10 0010.
 * author : tomz  email:ibepend@126.com
 */
public class MagicArrayActivity extends AppCompatActivity {

    private static final String TAG = "MagicArrayActivity";
    private TextView jzTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juzheng_actitity);
        jzTv = findViewById(R.id.juzhengTv);
        initData();
    }

    /**
     * 魔方矩阵 算法
     */
    private void initData() {
        /*
        1.把1放在第一行的最中间
        2.每个数字向右上角填充
        3.如果往右已经是最大数了，就从最左边重新继续
        4.如果往上已经是最大数了，就从最下边重新继续
        5.如果遇到行数的整数倍，则下一个数直接放到该数的下面
        */
        /*
        1.声明一个n*n
        二维数组
        2.声明一个int类型的变量记录每个元素递增的值，每次自加即可
        3.需要一个嵌套循环来填充二维数组
        3.1.把横向的索引认为x，x=n/2
        3.2.把纵向的所应认为y，y=0
        3.3.在循环中，先把x、y坐标上的值填充，然后计算下一个坐标
        */
        int n = 5;
        int[][] array = new int[n][n];
        int counter = 1;//自加的计数器
        int x = n / 2;
        int y = 0;
        //二维数组，需要用两层的嵌套循环来完成比较简单
        for (int i = 0; i < n * n; i++) {
            //根据坐标填充值
            array[y][x] = counter;
            //计算下一个坐标的位置
            if (counter % n == 0) {
                //如果counter是n的整数倍，下一个坐标是在当前数字的下面
                y++;
            } else {
                x++;
                y--;
                if (y < 0) {
                    //如果y超出范围，把y设置成最大
                    y = n - 1;
                }
                if (x == n) {
                    //如果x超出范围，把x设置成最小
                    x = 0;
                }
            }
            //使用完以后计数器需要自加
            counter++;
        }
        for (int[] row : array) {
            for (int i : row) {

                Log.i(TAG, "initData: " + i + "\t");
            }

        }
    }

}

package com.cyy.user.percentagecircleview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cyy.user.percentagecircle.View.PercentageCircle;

public class MainActivity extends AppCompatActivity {
    private PercentageCircle percentageCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentageCircle = (PercentageCircle) findViewById(R.id.sample_percent_circle);
        init();
    }

    private void init() {
        //可以动态的设置自定义view的属性。也可以在xml中设置属性。
        //设置被选中的数量。
        percentageCircle.setSumSelect(20);
        /**
         * 1：可以直接在代码中动态设置。
         * 2：动态设置的会把 xml中设置的 数量和文字覆盖掉。
         */
        //设置显示的数量。
        percentageCircle.setNumShow(30+"");
        //设置单位文字
        percentageCircle.setUnit("口数");
        //可以动态设置属性.会覆盖xml中设置的属性。
        percentageCircle.setUnSelectColor(Color.BLACK);
        percentageCircle.setSelectColor(Color.RED);
        percentageCircle.setUnitColor(Color.GREEN);
        percentageCircle.setmNumColor(Color.MAGENTA);
    }
}

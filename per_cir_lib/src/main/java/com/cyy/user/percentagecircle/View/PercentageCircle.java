package com.cyy.user.percentagecircle.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.cyy.user.percentagecirclelibrary.R;


/**
 * Created by ${到了我的周末}
 * on 2017-11-01.
 */

public class PercentageCircle extends View {
    //初始化画笔
    private Paint mPaint;

    //设置没有被选中的颜色。
    private int mSelectColor;

    //设置选中后的颜色。
    private int mUnSelectColor;

    //设置数字的颜色
    private int mNumColor;

    //设置单位的颜色
    private int mUnitColor;

    //设置单位字体的大小。
    private float mUnitTextSize;

    //设置数目字体的大小。
    private float mNumTextSize;

    //总的数目
    private int mSumLine;

    //旋转的角度
    private float mDrgees;

    //dp转换为px。。line的长度
    private float mLineLength;

    //line的宽度。
    private float mLineWidth;

    //初始化被选中的数目
    private int mSelectLine = 0;

    //设置控件的单位
    private String mUnit;
    //设置控件的单位
    private String mNumShow;


    //设置字体的范围
    private Rect mUnitRect;
    //设置字体的范围
    private Rect mNumShowRect;

    public PercentageCircle(Context context) {
        this(context, null);
    }

    public PercentageCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentageCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //属性集合

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PercentageCircle);

        //设置没被选中的默认的颜色为灰色。
        mUnSelectColor = array.getColor(R.styleable.PercentageCircle_unselect_color, Color.GRAY);

        //设置默认被选中的颜色为蓝色。
        mSelectColor = array.getColor(R.styleable.PercentageCircle_select_color, Color.BLUE);

        //设置字体和单位的默认的颜色为灰色。
        mNumColor = array.getColor(R.styleable.PercentageCircle_num_color, Color.BLACK);
        mUnitColor = array.getColor(R.styleable.PercentageCircle_unit_color, Color.BLACK);

        //设置字体的大小和默认值
        mUnitTextSize = array.getDimensionPixelOffset(R.styleable.PercentageCircle_unit_text_size, (int) dp2px(18));
        mNumTextSize = array.getDimensionPixelOffset(R.styleable.PercentageCircle_num_text_size, (int) dp2px(30));

        //设置总的数目
        mSumLine = array.getInt(R.styleable.PercentageCircle_all_line_num, 36);

        //单位的文本
        mUnit = array.getString(R.styleable.PercentageCircle_unit_text);
        mNumShow = array.getString(R.styleable.PercentageCircle_num_text);

        //设置直线的宽度
        mLineWidth = array.getDimensionPixelOffset(R.styleable.PercentageCircle_line_width, (int) dp2px(2));
        //长度
        mLineLength = array.getDimensionPixelOffset(R.styleable.PercentageCircle_line_length, (int) dp2px(10));
        array.recycle();


        //实例化画笔
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //设置宽度
        mPaint.setStrokeWidth(mLineWidth);

        //设置数目字体的范围，用来得到 字体的宽和高。
        mNumShowRect = new Rect();
        //设置单位字体的范围。
        mUnitRect = new Rect();
    }

    //测量控件的大小，以为是一个圆，所以用最小的数字作为他们的直径的长度。
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(size, size);
    }

    //绘制控件
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置每个间隔的旋转的角度.等于360/总数目的个数。
        mDrgees = 360 / mSumLine;
        Log.i("111", mDrgees + "hha");

        canvas.save();
        //设置被选中的直线的颜色
        mPaint.setColor(mSelectColor);
        for (int i = 0; i < mSelectLine; i++) {
            canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, mLineLength, mPaint);
            canvas.rotate(mDrgees, getWidth() / 2, getHeight() / 2);
        }
        //设置未被选中的直线的颜色。
        mPaint.setColor(mUnSelectColor);
        for (int i = 0; i < mSumLine - mSelectLine; i++) {
            canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, mLineLength, mPaint);
            canvas.rotate(mDrgees, getWidth() / 2, getHeight() / 2);
        }

        //设置数量字体的颜色
        mPaint.setColor(mNumColor);
        if (!TextUtils.isEmpty(mNumShow)) {
            mPaint.setTextSize(mNumTextSize);
            mPaint.getTextBounds(mNumShow, 0, mNumShow.length(), mNumShowRect);
        }
        if(!TextUtils.isEmpty(mNumShow)){
            canvas.drawText(mNumShow, (getWidth() / 2 - mNumShowRect.width() / 2), (getHeight() / 2 + mNumShowRect.height() / 2)
                    , mPaint);
        }
//        canvas.drawText(mNumShow, (getWidth() / 2 - mNumShowRect.width() / 2), (getHeight() / 2 + mNumShowRect.height() / 2)
//                , mPaint);
        Log.i("111",mNumShow +"---"+ (getWidth() / 2 - mNumShowRect.width() / 2) + "===" + (getHeight() / 2 + mNumShowRect.height() / 2));

        //设置单位字体的颜色。
        mPaint.setColor(mUnitColor);
        if (!TextUtils.isEmpty(mUnit)) {
            mPaint.setTextSize(mUnitTextSize);
            mPaint.getTextBounds(mUnit, 0, mUnit.length(), mUnitRect);
        }
        if(!TextUtils.isEmpty(mUnit)){
            canvas.drawText(mUnit, getWidth() / 2 - mUnitRect.width() / 2, getHeight() / 2 + mNumShowRect.height() / 2
                    + mUnitRect.height() / 2 + dp2px(20), mPaint);
        }


        canvas.restore();
    }

    //设置被选中的数目。
    public void setSumSelect(int num) {
        if (num > mSumLine) {
            num = mSumLine;
        }
        if (num < 0) {
            num = 0;
        }
        this.mSelectLine = num;
        invalidate();
    }

    //设置单位文字
    public void setUnit(String unit) {
        if (!TextUtils.isEmpty(unit)) {
            this.mUnit = unit;
        }
        invalidate();
    }

    //设置单位颜色
    public void setUnitColor(int color) {
        if (!TextUtils.isEmpty(mUnit)) {
            this.mUnitColor = color;
        }
        invalidate();
    }

    //设置单位字体大小
    public void setUnitTextSize(int size) {
        if (!TextUtils.isEmpty(mUnit)) {
            this.mUnitTextSize = size;
        }
        invalidate();
    }

    //设置数量的文字
    public void setNumText(String numText) {
        if (!TextUtils.isEmpty(numText)) {
            this.mNumShow = numText;
        }
        invalidate();
    }

    //设置没有被选中的颜色
    public void setUnSelectColor(int color) {
        this.mUnSelectColor = color;
    }

    //设置被选中的颜色
    public void setSelectColor(int color) {
        this.mSelectColor = color;
    }

    //设置数量颜色
    public void setmNumColor(int color) {
        if (!TextUtils.isEmpty(String.valueOf(color))) {
            this.mNumColor = color;
        }
        invalidate();
    }

    //设置数量字体大小
    public void setNumTextSize(int size) {
        if (!TextUtils.isEmpty(String.valueOf(size))) {
            this.mNumTextSize = size;
        }
        invalidate();
    }

    //设置数目
    public void setNumShow(String show) {
        if (!TextUtils.isEmpty(show)) {
            this.mNumShow = show;
        }
        invalidate();
    }

    //设置直线的宽度。
    public void setLineWidth(int width) {
        this.mLineWidth = width;
    }

    //设置直线的高度。
    public void setLineLength(int length) {
        this.mLineLength = length;
    }

    //dp转化为px
    private float dp2px(float dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}

package com.rat.snowkids.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.snowkids.snowkids.R;

/**
 * Created by ouhimehime on 16/6/15.
 * --------自定义控件-------
 */
public class CustomView extends View {
    //画笔
    private Paint paint;
    private RectF oval;

    //圆弧颜色
    private int roundColor;
    //进度颜色
    private int progressColor;
    //文字内容
    private boolean textIsShow;
    //字体大小
    private float textSize = 14;
    //文字颜色
    private int textColor;
    //最大进度
    private int max = 1000;
    //当前进度
    private int progress = 300;
    //圆弧宽度
    private int roundWidth = 30;

    private int viewWidth; //宽度--控件所占区域

    private float nowPro = 0;//用于动画

    private ValueAnimator animator;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs, context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs, context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(attrs, context);
    }

    private void initAttrs(AttributeSet attr, Context context) {
        TypedArray array = context.obtainStyledAttributes(attr, R.styleable.CustomView);

        roundColor = array.getColor(R.styleable.CustomView_roundColor, context.getResources().getColor(R.color.gray_from));//环形颜色
        progressColor = array.getColor(R.styleable.CustomView_progressColor, context.getResources().getColor(R.color.red_from));//进度颜色
        textIsShow = array.getBoolean(R.styleable.CustomView_textIsShow, true);//文字
        textSize = array.getDimension(R.styleable.CustomView_textSize, 100);//文字大小
        textColor = array.getColor(R.styleable.CustomView_textColor, context.getResources().getColor(R.color.red));//文字颜色
        roundWidth = array.getInt(R.styleable.CustomView_roundWidth, 70);//圆环宽度

        array.recycle();

        //动画
        animator = ValueAnimator.ofFloat(0, progress);
        animator.setDuration(1800);
        animator.setInterpolator(new OvershootInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                nowPro = Float.parseFloat(String.valueOf(animation.getAnimatedValue()));
                postInvalidate();
            }
        });
        animator.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST) {//可获得最大空间
            setMeasuredDimension(widthMeasureSpec, (widthSpecSize / 2) + (int) (Math.cos(20) * (widthSpecSize / 2)));
        } else if (widthMeasureSpec == MeasureSpec.EXACTLY) {//一般指精确值
            setMeasuredDimension(widthMeasureSpec, (widthSpecSize / 2) + (int) (Math.cos(20) * (widthSpecSize / 2)));
        } else {
            setMeasuredDimension(widthMeasureSpec, (viewWidth / 2) + (int) (Math.cos(20) * (viewWidth / 2)));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;//得到宽度以此来计算控件所占实际大小

        //计算画布所占区域
        oval = new RectF();
        oval.left = roundWidth + getPaddingLeft();
        oval.top = roundWidth + getPaddingTop();
        oval.right = viewWidth - roundWidth - getPaddingRight();
        oval.bottom = viewWidth - roundWidth - getPaddingBottom();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画外环
        paint = new Paint();
        // 增加渐变颜色
//        LinearGradient shader = new LinearGradient(0, 50, viewWidth / 2, 50, new int[]{Color.GR, Color.GREEN, Color.RED}, new float[]{0, 0.5f, 1}, Shader.TileMode.MIRROR);
//        paint.setShader(shader);
        paint.setAntiAlias(true);                        //设置画笔为无锯齿
        paint.setColor(roundColor);                      //设置画笔颜色
        paint.setStrokeWidth(roundWidth);                //线宽
        paint.setStyle(Paint.Style.STROKE);              //空心
        canvas.drawArc(oval, 160, 220, false, paint);    //绘制圆弧

        // 画文字
        if (textIsShow) {
            paint.setColor(textColor);
            paint.setStrokeWidth(0);
            paint.setTypeface(Typeface.DEFAULT);
            paint.setTextSize(textSize * 2);
            float textWidth = paint.measureText((int) ((nowPro / (float) max) * 100) + "%");
            canvas.drawText((int) ((nowPro / (float) max) * 100) + "%", viewWidth / 2 - textWidth / 2, viewWidth / 2, paint);
        }

        // 画进度层
        // 增加渐变颜色
//        shader = new SweepGradient(90, 90, Color.YELLOW, Color.RED);
//        paint.setShader(shader);
        paint.setColor(progressColor);
        paint.setStrokeWidth(roundWidth - 20);
        canvas.drawArc(oval, 160, 220 * nowPro / max, false, paint);  //绘制圆弧
    }


    private int getDefaultHeight() {
        return 0;
    }

    private int getDefaultWidth() {
        return 0;
    }


    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public boolean getText() {
        return textIsShow;
    }

    public void setText(boolean text) {
        this.textIsShow = text;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
package zhuazhu.science.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import zhuazhu.science.R;

/**
 * @author zhuazhu
 **/
public class BezierView extends View {
    /**
     * 圆弧半径
     */
    private float mArcRadius;
    /**
     * 圆弧的高度
     */
    private float mArcRadiusHeight;
    /**
     * 上拱贝塞尔曲线的控制点x坐标
     */
    private float mCenterPointX;
    /**
     * 曲线的颜色
     */
    private int mLineColor;
    /**
     * 曲线的高度
     */
    private float mLineHeight;
    /**
     * 曲线下方的背景色
     */
    private int mArcBackground;

    private float mAngle = 25;
    /**
     * 余弦
     */
    private double mCos;
    /**
     * 正切
     */
    private double mTan;
    private Paint mPaint;
    private Paint mPaintBg;
    private float mCenterX;
    private float mHeight;
    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
        initPaint();
        mCos = Math.cos(Math.toRadians(mAngle));
        mTan = Math.tan(Math.toRadians(mAngle));
        mArcRadiusHeight = (float) (mArcRadius*mTan);
        mCenterPointX = (float) (mArcRadius/(mCos*mCos*4)-mArcRadius);
    }
    private void initAttr(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.BezierView, defStyleAttr, 0);
        mArcRadius = typedArray.getDimensionPixelSize(R.styleable.BezierView_arcRadius,200);
        mLineColor = typedArray.getColor(R.styleable.BezierView_lineColor,ContextCompat.getColor(context, R.color.c_999));
        mLineHeight = typedArray.getDimensionPixelSize(R.styleable.BezierView_lineHeight,2);
        mArcBackground = typedArray.getColor(R.styleable.BezierView_arcBackground,ContextCompat.getColor(context, R.color.c_fff));
    }
    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineHeight);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaintBg = new Paint();
        mPaintBg.setColor(mArcBackground);
        mPaintBg.setStrokeWidth(mLineHeight);
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = (float)w/2;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mLineHeight/2);
        drawBackground(canvas);

        canvas.drawLine(-mCenterX,0,-mArcRadius,0,mPaint);
        canvas.drawLine(mArcRadius,0,mCenterX,0,mPaint);

        Path path = new Path();
        path.moveTo(-mArcRadius,0);
        path.quadTo(mCenterPointX,0,-mArcRadius/2,mArcRadiusHeight/2);
        path.moveTo(-mArcRadius/2,mArcRadiusHeight/2);
        path.cubicTo(-mArcRadius/5,mArcRadiusHeight*1.3f,mArcRadius/5,mArcRadiusHeight*1.3f,mArcRadius/2,mArcRadiusHeight/2);
        path.moveTo(mArcRadius/2,mArcRadiusHeight/2);
        path.quadTo(-mCenterPointX,0,mArcRadius,0);
        canvas.drawPath(path,mPaint);

    }

    /**
     * 画背景色
     */
    private void drawBackground(Canvas canvas){
        canvas.drawRect(-mCenterX,0,-mArcRadius+1,mHeight,mPaintBg);
        canvas.drawRect(mArcRadius-1,0,mCenterX,mHeight,mPaintBg);

        Path path1 = new Path();
        path1.moveTo(-mArcRadius,0);
        path1.quadTo(mCenterPointX,0,-mArcRadius/2,mArcRadiusHeight/2);
        path1.lineTo(-mArcRadius/2,mHeight);
        path1.lineTo(-mArcRadius,mHeight);
        path1.lineTo(-mArcRadius,0);
        path1.close();
        canvas.drawPath(path1,mPaintBg);

        Path path2 = new Path();
        path2.moveTo(-mArcRadius/2,mArcRadiusHeight/2);
        path2.cubicTo(-mArcRadius/5,mArcRadiusHeight*1.3f,mArcRadius/5,mArcRadiusHeight*1.3f,mArcRadius/2,mArcRadiusHeight/2);
        path2.lineTo(mArcRadius/2,mHeight);
        path2.lineTo(-mArcRadius/2,mHeight);
        path2.lineTo(-mArcRadius/2,mArcRadiusHeight/2);
        path2.close();
        canvas.drawPath(path2,mPaintBg);

        Path path3 = new Path();
        path3.moveTo(mArcRadius/2,mArcRadiusHeight/2);
        path3.quadTo(-mCenterPointX,0,mArcRadius,0);
        path3.lineTo(mArcRadius,mHeight);
        path3.lineTo(mArcRadius/2,mHeight);
        path3.lineTo(mArcRadius/2,mArcRadiusHeight/2);
        path3.close();
        canvas.drawPath(path3,mPaintBg);
    }
}

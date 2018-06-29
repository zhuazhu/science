package zhuazhu.science.widget;

import android.content.Context;
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
    private float mArcRadius = 200;
    private float mAngle = 20;
    /**
     * 余弦
     */
    private double mCos;
    /**
     * 正切
     */
    private double mTan;
    private Paint mPaint;
    private float mCenterX;
    private float mCenterY;
    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(context, R.color.c_999));
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mCos = Math.cos(Math.toRadians(mAngle));
        mTan = Math.tan(Math.toRadians(mAngle));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = (float)w/2;
        mCenterY = (float)h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY);
        canvas.drawLine(-mCenterX,0,-mArcRadius,0,mPaint);
        canvas.drawLine(mArcRadius,0,mCenterX,0,mPaint);
        //画辅助线
//        canvas.drawLine(-mArcRadius,0,mArcRadius,0,mPaint);
//        canvas.drawLine(0,0,0,mCenterY,mPaint);
//
//        canvas.drawLine(-mArcRadius,0,-mArcRadius*2/3,mArcRadius/6,mPaint);
//        canvas.drawLine(-mArcRadius*2/3,mArcRadius/6,-mArcRadius/2,mArcRadius/2,mPaint);
//
//        canvas.drawLine(-mArcRadius/2,mArcRadius/2,-mArcRadius/5,mArcRadius,mPaint);
//        canvas.drawLine(-mArcRadius/5,mArcRadius,mArcRadius/5,mArcRadius,mPaint);
//        canvas.drawLine(mArcRadius/5,mArcRadius,mArcRadius/2,mArcRadius/2,mPaint);
//
//        canvas.drawLine(mArcRadius/2,mArcRadius/2,mArcRadius*2/3,mArcRadius/6,mPaint);
//        canvas.drawLine(mArcRadius*2/3,mArcRadius/6,mArcRadius,0,mPaint);
        float heightY = (float) (mArcRadius*mTan);
        float x1 = (float) (mArcRadius/(mCos*mCos*4)-mArcRadius);
        Path path = new Path();
        path.moveTo(-mArcRadius,0);
        path.quadTo(x1,0,-mArcRadius/2,heightY/2);
        path.moveTo(-mArcRadius/2,heightY/2);
        path.cubicTo(-mArcRadius/5,heightY*1.5f,mArcRadius/5,heightY*1.5f,mArcRadius/2,heightY/2);
        path.moveTo(mArcRadius/2,heightY/2);
        path.quadTo(-x1,0,mArcRadius,0);
        canvas.drawPath(path,mPaint);
    }
}

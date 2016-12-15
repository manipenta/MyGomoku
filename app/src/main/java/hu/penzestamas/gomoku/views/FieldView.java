package hu.penzestamas.gomoku.views;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import hu.penzestamas.gomoku.R;

import static android.R.attr.width;

/**
 * Created by penzes.tamas on 2016.10.10..
 */

public class FieldView extends View {

    public static final int STATE_ARC = 2;
    public static final int STATE_X = 1;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float line;
    private float hline;
    private float angle = 0f;
    private int state = 0;
    private RectF mRect = new RectF();
    private float strikeWidth = 20;
    private int strikeXColor;
    private int strikeArcColor;

    private float padding;


    public FieldView(Context context) {
        super(context);
        initializeView(context,null);
    }



    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context,attrs);
    }

    public FieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        if (w > h) {
            int temp = w / 2 - h / 2;
            int temp2 = w / 2 + h / 2;
            mRect.set(temp + padding, padding, temp2-padding, h-padding);
        }
        else
        {
            mRect.set(padding, (h / 2 - w / 2) +padding, w-padding, (h / 2 + w / 2)-padding);
        }
        hline = w - getPaddingTop();
        line = getPaddingTop();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(state == STATE_X){
            canvas.drawLine(padding,padding,line,line,paint);
            canvas.drawLine(getMeasuredWidth()-padding,padding,hline,getMeasuredHeight()-hline,paint);

        } else if(state == STATE_ARC){
            canvas.drawArc(mRect,0f,angle,false,paint);
        }
    }

    private void initializeView(Context context, AttributeSet set) {

        padding = getPaddingTop();
        TypedArray a = context.obtainStyledAttributes(set,R.styleable.FieldView,0,0);
        strikeWidth = a.getDimension(R.styleable.FieldView_strikeWidth,20);
        strikeXColor = a.getColor(R.styleable.FieldView_strikeXColor,Color.RED);
        strikeArcColor = a.getColor(R.styleable.FieldView_strikeArcColor,Color.BLUE);
        a.recycle();

    }

    private void initializeXView(){
        paint.setColor(strikeXColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strikeWidth);


    }

    private void initializeArcView(){
        paint.setColor(strikeArcColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strikeWidth);

    }

    public void startDrawLines(){
        float target = getLayoutParams().height -getPaddingTop();
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator anim = ObjectAnimator.ofFloat(this,"line",(float)getPaddingTop(),target);
        ObjectAnimator an = ObjectAnimator.ofFloat(this,"hline",target, getPaddingTop());
        anim.setDuration(1000);
        an.setDuration(1000);
        set.play(anim).before(an);
        set.start();
    }

    public void startDrawArc(){
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.arcanimator);
        animator.setTarget(this);
        animator.start();
    }

    public void animateField(int state){
        if(state == STATE_X){
            startDrawLines();
        } else if(state == STATE_ARC){
            startDrawArc();
        }
    }



    public float getLine() {
        return line;
    }

    public void setLine(float line) {
        this.line = line;
        invalidate();
    }

    public float getStrikeWidth() {
        return strikeWidth;
    }

    public void setStrikeWidth(float strikeWidth) {
        this.strikeWidth = strikeWidth;
    }

    public float getHline() {
        return hline;
    }

    public void setHline(float hline) {
        this.hline = hline;
        invalidate();
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        invalidate();
    }

    public int getState() {
        return state;
    }



    public void setState(int state) {
        this.state = state;
        if(state == STATE_X){
            initializeXView();
        } else if(state == STATE_ARC){
            initializeArcView();
        }
    }

    public void animateState(int state){
        setState(state);
        animateField(state);
    }
}

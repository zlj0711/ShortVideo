package com.example.shortvideo;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class MyVideo extends VideoView {
    private GestureDetector mGesture;
    private OnDoubleClickListener onDoubleClickListener;
    private int doubleFlag = 0;
    public static final String TAG = "zlj";

    public int getDoubleFlag() {
        return doubleFlag;
    }

    public void setDoubleFlag(int doubleFlag) {
        this.doubleFlag = doubleFlag;
    }
    interface OnDoubleClickListener{
        void onDoubleClick(View view);
    }
    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener){
        this.onDoubleClickListener = onDoubleClickListener;
    };
    public MyVideo(Context context) {
        super(context);
    }
    public MyVideo(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        mGesture = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (onDoubleClickListener != null) {
                    onDoubleClickListener.onDoubleClick(MyVideo.this);
                }
                doubleFlag = 1;
                return true;
            }
        });
    }
    public MyVideo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //touch事件传给onTouchEvent()
        mGesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}

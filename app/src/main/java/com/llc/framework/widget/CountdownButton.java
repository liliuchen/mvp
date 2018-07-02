package com.llc.framework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;

import com.llc.framework.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 *@package  com.crm.framework.widget
 *@fileName CountdownButton
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 倒计时
 *@company
 */
public class CountdownButton extends Button {
    private Handler handler = new Handler();
    private int count = 60;
    private String startText = "GetCode", endText = "Reget", postfix = "(s)";
    private boolean isCountDowning = false;

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getStartText() {
        return startText;
    }

    public void setStartText(String startText) {
        this.startText = startText;
    }

    public String getEndText() {
        return endText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (isCountDowning) return;
        this.count = count;
    }

    public CountdownButton(Context context) {
        super(context);
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CountdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountdownButton);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.CountdownButton_countdown) {
                int count = typedArray.getInteger(attr, 0);
                if (count > 0) {
                    this.count = count;
                }
            } else if (attr == R.styleable.CountdownButton_startText) {
                String startText = typedArray.getString(attr);
                if (!TextUtils.isEmpty(startText)) this.startText = startText;
            } else if (attr == R.styleable.CountdownButton_endText) {
                String endText = typedArray.getString(attr);
                if (!TextUtils.isEmpty(endText)) this.endText = endText;
            } else if (attr == R.styleable.CountdownButton_postfix) {
                String postfix = typedArray.getString(attr);
                if (!TextUtils.isEmpty(postfix)) this.postfix = postfix;
            }
        }
        typedArray.recycle();
        setText(startText);
    }

    public void startDown() {
        if (isCountDowning) return;
        setClickable(false);
        new Timer().schedule(new TimerTask() {
            int tmp = count;

            @Override
            public void run() {
                if (tmp <= 0) {
                    this.cancel();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            setText(endText);
                        }
                    });
                    setClickable(true);
                    isCountDowning = false;
                    return;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setTextWithPostfix(tmp + "");
                        tmp--;
                    }
                });
            }
        }, 0, 1000);

    }
    public void setTextWithPostfix(String text) {
        super.setText(text + postfix);
    }
}

package com.zanvent.mathview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MathView extends WebView {
    private String text;
    private String textColor;
    private float textSize;
    private Scale pixelScaleType;
    private volatile boolean pageLoaded;
    private OnLoadListener onLoadListener;
    private static final String textColorDefault = "#000000";
    private static final int textSizeDefault = 16;

    public enum Scale {
        SCALE_DP,
        SCALE_SP
    }

    public MathView(Context context) {
        super(context);
        init();
    }

    public MathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setBackgroundColor(Color.TRANSPARENT);
        this.pageLoaded = false;
        pixelScaleType = Scale.SCALE_DP;
        setText("");
        setTextSize(textSizeDefault);
        setTextColor(textColorDefault);
        // enable javascript
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setJavaScriptEnabled(true);

        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        getSettings().setAppCacheEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        // disable click
        setClickable(false);
        setLongClickable(false);
        getSettings().setUseWideViewPort(true);
        loadUrl("file:///android_asset/mathscribe/view.html");
        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pageLoaded = true;
                updateText();
                updateTextColor();
                updateTextSize();
                if (onLoadListener != null)
                    onLoadListener.onLoad();
            }
        });
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
    }

    public void setText(String text) {
        this.text = text;
        updateText();
    }

    public void setTextColor(String textColor) {
        if (textColor != null && !textColor.trim().isEmpty()) {
            this.textColor = textColor;
            updateTextColor();
        } else {
            setTextColor(textColorDefault);
        }
    }

    public void setTextSize(int textSize) {

        if (textSize > 0) {
            if (pixelScaleType == Scale.SCALE_DP) {
                this.textSize = Util.convertDpToPixels(getContext(), textSize);
            } else if (pixelScaleType == Scale.SCALE_SP) {
                this.textSize = Util.convertSpToPixels(getContext(), textSize);
            }
            updateTextSize();
        } else {
            setTextSize(textSizeDefault);
        }

    }


    private void updateText() {
        if (pageLoaded) {
            loadUrl("javascript:setText(\"" + getText() + "\"  )");
        }
    }

    private void updateTextSize() {
        if (pageLoaded) {
            loadUrl("javascript:setTextSize(" + getTextSize() + ")");
        }
    }

    private void updateTextColor() {
        if (pageLoaded) {
            loadUrl("javascript:setTextColor(\"" + getTextColor() + "\"  )");
        }
    }


    public String getText() {
        return text;
    }


    public String getTextColor() {
        return textColor;
    }


    public float getTextSize() {
        return textSize;
    }

    public Scale getPixelScaleType() {
        return pixelScaleType;
    }

    public void setPixelScaleType(Scale pixelScaleType) {
        this.pixelScaleType = pixelScaleType;
    }

    public interface OnLoadListener {
        void onLoad();
    }

    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }


    /**
     * Max allowed duration for a "click", in milliseconds.
     */
    private static final int MAX_CLICK_DURATION = 1000;

    /**
     * Max allowed distance to move during a "click", in DP.
     */
    private static final int MAX_CLICK_DISTANCE = 15;

    private long pressStartTime;
    private float pressedX;
    private float pressedY;
    private boolean stayedWithinClickDistance;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                pressStartTime = System.currentTimeMillis();
                pressedX = event.getX();
                pressedY = event.getY();
                stayedWithinClickDistance = true;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (stayedWithinClickDistance && distance(pressedX, pressedY, event.getX(), event.getY()) > MAX_CLICK_DISTANCE) {
                    stayedWithinClickDistance = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                long pressDuration = System.currentTimeMillis() - pressStartTime;
                if (pressDuration < MAX_CLICK_DURATION && stayedWithinClickDistance) {
                    performClick();
                }
            }
        }
        return true;
    }

    private float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float distanceInPx = (float) Math.sqrt(dx * dx + dy * dy);
        return Util.convertPixelsToDp(distanceInPx, getContext());
    }
}

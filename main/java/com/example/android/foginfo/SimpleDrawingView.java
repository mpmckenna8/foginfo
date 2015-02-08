package com.example.android.foginfo;

/**
 * Created by matthewmckenna on 2/1/15.
 */

import android.app.Fragment;
import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

import com.example.android.foginfo.data.WeatherContract;
import com.example.android.foginfo.data.WeatherDbHelper;

/**
 * Created by matthewmckenna on 1/30/15.
 */

public class SimpleDrawingView extends View {
    // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;

    public float ViDegrees = 40;

    Context mContext;


    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);

        int lenewea = WeatherDbHelper.DATABASE_NAME.length();

        mContext = context;

        if(mContext.getSystemService(Context.ACCESSIBILITY_SERVICE) != null )
        {
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
        }






        setupPaint();
    }

/**
    @Override
    public boolean DispatchPopulateAccessibilityEvent (AccessibilityEvent ev){
        ev.getText().add("North");
        return true;
    }

**/



    public void setDegrees(float degrees) {
        ViDegrees = degrees;
        invalidate();
    }


    // Setup paint with color and stroke styles
    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        Float degs = DetailFragment.windangle;

        setDegrees(degs);


        drawPaint.setColor(Color.GREEN);

        String blaper = mContext.getString(R.string.pref_location_default);

        Log.v("blah", blaper);



        //Rect blah = new Rect
        int widy = getWidth();
        int hei = getHeight();

        canvas.translate(widy/2,hei/2);

        // from my practice rectangle
        //canvas.drawRect(4, 15,190, 250, drawPaint);

        Path path = new Path();

        path.moveTo(-50,0);

        path.lineTo(50, 0);

        path.lineTo(0, -hei/2+50);


        path.lineTo(-50,0);

        drawPaint.setColor(Color.DKGRAY);

        drawPaint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path, drawPaint);

        Path wiPath = new Path();

        wiPath.moveTo(-50,0);

        wiPath.lineTo(50, 0);

        wiPath.lineTo(0, -hei/2+150);


        wiPath.lineTo(-50,0);

        drawPaint.setTextSize(50);
        drawPaint.setColor(Color.WHITE);
        drawPaint.setAntiAlias(true);

        canvas.drawText("N",-20,-145, drawPaint);



        canvas.drawCircle(0,0,50, drawPaint);





        canvas.rotate(ViDegrees);

        drawPaint.setColor(Color.RED);

        canvas.drawPath(wiPath, drawPaint);






        String blah = WeatherContract.WeatherEntry._ID;

        Log.v("what get from wc", blah);



    }
}
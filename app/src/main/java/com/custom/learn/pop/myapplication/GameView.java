package com.custom.learn.pop.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by pop on 12/16/2016.
 */

/***
 * <p>the container tells the view what to do , holder manages the view</p>
 * the surfaceView low level class it does not call the onDraw method
 * so we have to call  it explicitly .
 */

public class GameView extends SurfaceView {
    Bitmap mBitmap;

    SurfaceHolder mSurfaceHolder;
    GameLoopThread mGameLoopThread;
 //   private int pos_x=0;
 //   int xSpeed=1;
    Sprite badPeopleSprite;

    public GameView(Context context) {
        super(context);
        mSurfaceHolder = getHolder();
/**
 * implemented the game loop
 */
        mGameLoopThread = new GameLoopThread(this);
        /***
         * register as anonymous  class , which going to be a listener that callback of the holder.
         * so addCall back would be listener
         */
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                /***
                 * this method lock the canvas
                 * we have done this game loop method
                 */
                mGameLoopThread.setRunning(true);
                mGameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                boolean retry=true;
                /***
                 * stop the Game thread
                 */
                mGameLoopThread.setRunning(false);

                while (retry){

                    try {
                        /**
                         * The join method allows one thread to wait for the completion of another.
                         * If t is a Thread object whose thread is currently executing,
                         *                          t.join();
                         * causes the current thread to pause execution until t's thread terminates.
                         * Overloads of join allow the programmer to specify a waiting period.
                         * However, as with sleep, join is dependent on the OS for timing,
                         * so you should not assume that join will wait exactly as long as you specify.
                         */
                        mGameLoopThread.join();

                        retry=false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        /**
         * set image here
         */
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bad1);

        badPeopleSprite=new Sprite(this,mBitmap);
    }

    /***
     * invoke by view
     *
     * @param canvas chalk object
     */
    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * tell the canvas: object the bord would be black
         */
        canvas.drawColor(Color.BLACK);
        badPeopleSprite.onDraw(canvas);


    }
}

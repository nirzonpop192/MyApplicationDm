package com.custom.learn.pop.myapplication;

import android.graphics.Canvas;
import android.provider.Settings;

/**
 * Created by pop on 12/16/2016.
 */

public class GameLoopThread extends Thread {
    private GameView view;
    private boolean running = false;
    /***
     * fps=frame per second
     * limited the drawing to 10 FPS that is 100 ms (millisecond).
     */
    private static final long FPS = 50;


    public GameLoopThread(GameView view) {
        this.view = view;
    }

    public void setRunning(boolean run) {
        this.running = run;
    }

    @Override
    public void run() {

        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            /***
             * loop start time ;
             */
            startTime= System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.onDraw(c);
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }// end of if
            }// end of finally
            try{
                sleepTime=ticksPS-(System.currentTimeMillis()-startTime);
                if (sleepTime>0){
                    sleep(sleepTime);
                }else {
                    sleep(10);
                }
            }catch (Exception e){}
        }// end of while
    }
}

package com.custom.learn.pop.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by pop on
 * 12/30/2016.
 */

public class Sprite {
    private int pos_x = 0;
    private int xSpeed = 5;
    private GameView mGameView;
    private Bitmap mBitmap;


    public Sprite( GameView mGameView,Bitmap mBitmap) {
        this.mBitmap = mBitmap;
        this.mGameView = mGameView;
    }

    /**
     *   override the method
     * @param canvas of screen
     */
    public void onDraw(Canvas canvas){

        updatePosition();

        /**
         * tell the canvas : print the mBitmap on the bord
         */
        canvas.drawBitmap(mBitmap,pos_x,0,null);
    }

    public  void updatePosition(){

        /**
         * if object reached to boarder it reset to other direction Right To Left Direction
         */
        if (pos_x>mGameView.getWidth()-mBitmap.getWidth()-xSpeed){
            xSpeed=-5;
        }
        /**
         * if object reached to boarder it reset to other direction Left To  Right Direction
         */
        if (pos_x+xSpeed<0){
            xSpeed=5;
        }
        /**
         * upgrade position
         */
        pos_x=pos_x+xSpeed;
    }
}

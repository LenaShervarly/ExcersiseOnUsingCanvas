package com.miracle.boardgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tmpuser-10206 on 3/15/17.
 */

public class Board extends View {

    private final GestureDetector mDetector;
    private BoardHandler hanlder;
    private GameState gameState;

    public void setGame(GameState gameState) {
        this.gameState = gameState;
    }

    public Board(Context context) {
        super(context);
        mDetector = new GestureDetector(this.getContext(), new mListener());
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDetector = new GestureDetector(this.getContext(), new mListener());
    }

    private class mListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int widthCell = canvas.getWidth()/8;
        int hightCell = canvas.getHeight()/8;
        Paint black = new Paint();
        Paint white = new Paint();
        white.setARGB(255, 255, 255, 255);
        Resources res = getResources();
        Drawable logo = res.getDrawable(R.drawable.kth);

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Paint currentColor = ((i+j) % 2 == 0) ? black : white;
                canvas.drawRect(i * widthCell, j*hightCell, widthCell + i * widthCell, hightCell + j* hightCell, currentColor);

                if(gameState.obstacles[i][j]) {
                    logo.setBounds(i * widthCell, j * hightCell, widthCell + i * widthCell, hightCell + j * hightCell);
                    logo.draw(canvas);
                }
            }
        }
        Drawable player = res.getDrawable(R.drawable.player);
        player.setBounds(widthCell, hightCell, widthCell+widthCell, hightCell +hightCell);
        player.draw(canvas);


    }

    public interface BoardHandler {
        void onClick(int cellX, int cellY);

        void onRigh();
        void onLeft();
        void onUp();
        void onDown();
    }

    public void setHandler(BoardHandler hanlder){
        this.hanlder = hanlder;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean res =mDetector.onTouchEvent(event);
        if(!res) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                int width = getWidth();
                int height = getHeight();

                int cellX = (int)event.getX() / (width/8);
                int cellY = (int)event.getY() / (height/8);

                if(!hanlder.equals(null)) {
                    int dx = cellX - gameState.playerX;
                    int dy = cellY - gameState.targetY;
                    int angle = (int) Math.atan(dy/dx);

                    /*switch (angle) {
                        case 45 :
                    }*/
                    if(cellX > gameState.playerX && cellY == gameState.playerY)
                        hanlder.onRigh();
                    if(cellX < gameState.playerX && cellY == gameState.playerY)
                        hanlder.onLeft();
                    if(cellX == gameState.playerX && cellY > gameState.playerY)
                        hanlder.onUp();
                    if(cellX == gameState.playerX && cellY < gameState.playerY)
                        hanlder.onDown();
                }

                res = true;
            }
        }
        return res;
    }


}

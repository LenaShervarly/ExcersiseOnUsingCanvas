package com.miracle.boardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Board.BoardHandler {
    private GameState currentGame = createLevel1();
    private Board board;

    GameState createLevel1() {
        GameState gameState = new GameState();
        gameState.playerX = 0;
        gameState.playerY = 0;

        gameState.targetX =7;
        gameState.targetY =5;

        gameState.obstacles = new boolean[8][8];
        gameState.obstacles[1][3] = true;
        gameState.obstacles[1][4] = true;
        gameState.obstacles[1][5] = true;
        gameState.obstacles[2][3] = true;
        gameState.obstacles[3][3] = true;
        return gameState;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LinearLayout mLinearLayout = new LinearLayout(this);
        board = (Board) findViewById(R.id.board);
        board.setHandler(this);
        board.setGame(createLevel1());
        /*mLinearLayout.addView(board);
        setContentView(mLinearLayout); */



    }

    @Override
    public void onClick(int cellX, int cellY) {
        Toast.makeText(this, "Hello" + cellX + " " + cellY, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRigh() {
        if(currentGame.playerX >=7)
            return;
        if(currentGame.obstacles[currentGame.playerX+1][currentGame.playerY])
            return;
        currentGame.playerX +=1;
        findViewById(R.id.board).invalidate();
        //updateBoardState();
    }

    @Override
    public void onLeft() {
        if(currentGame.playerX <=0)
            return;
        if(currentGame.obstacles[currentGame.playerX-1][currentGame.playerY])
            return;
        currentGame.playerX -=1;
        findViewById(R.id.board).invalidate();
        //updateBoardState(currentGame.playerX, currentGame.playerY);
    }

    @Override
    public void onUp() {
        if(currentGame.playerY >=7)
            return;
        if(currentGame.obstacles[currentGame.playerX][currentGame.playerY+1])
            return;
        currentGame.playerX -=1;
        findViewById(R.id.board).invalidate();
        //updateBoardState();
    }

    @Override
    public void onDown() {
        if(currentGame.playerY <=0)
            return;
        if(currentGame.obstacles[currentGame.playerX][currentGame.playerY-1])
            return;
        currentGame.playerX -=1;
        findViewById(R.id.board).invalidate();
        //updateBoardState();
    }

    private void updateBoardState(int playerX, int playerY){
        board = (Board)findViewById(R.id.board);
        GameState updatedGameState = new GameState();
        updatedGameState.playerX = playerX;
        updatedGameState.playerY = playerY;
    }
}

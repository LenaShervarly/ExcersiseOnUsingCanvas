package com.miracle.boardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Board.BoardHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LinearLayout mLinearLayout = new LinearLayout(this);
        Board board = (Board) findViewById(R.id.board);
        board.setHandler(this);
        /*mLinearLayout.addView(board);
        setContentView(mLinearLayout); */
    }

    @Override
    public void onClick(int cellX, int cellY) {
        Toast.makeText(this, "Hello" + cellX + " " + cellY, Toast.LENGTH_SHORT).show();
    }
}

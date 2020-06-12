package com.example.minesweeperproject.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.example.minesweeperproject.R;
import com.example.minesweeperproject.GameEngine;

import androidx.core.content.ContextCompat;

public class Cell extends BaseCell implements View.OnClickListener , View.OnLongClickListener{


    public Cell(Context context, int x, int y){
        super(context);

        setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().click(getXPos(), getYPos());
    }

    @Override
    public boolean onLongClick(View v) {
        GameEngine.getInstance().flag(getXPos(), getYPos());

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("Minesweeper","Cell::onDraw");
        drawButton(canvas);

        if(isFlagged() ){
            drawFlag(canvas); //if flagged draw flag
        } else if (isRevealed() && isBomb() && !isClicked()){ //if pressed on bomb draw all bombs
            drawNormalBomb(canvas);
        } else {
            if( isClicked() ) {
                if(getValue() == -1) { //if click on bomb draw exploded bomb
                    drawBombExploded(canvas);
                } else { //draw number
                    drawNumber(canvas);
                }
            } else { //draw button
                drawButton(canvas);
            }
        }
    }



    private void drawFlag(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBombExploded(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas) {
        Drawable drawable = null;

        switch (getValue()) { //which number to print based on neighbors
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 80:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }


}

package com.example.minesweeperproject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.minesweeperproject.util.Generator;
import com.example.minesweeperproject.util.PrintGrid;
import com.example.minesweeperproject.views.grid.Cell;


public class GameEngine {
    private static GameEngine instance;

    public static final int numBombs = 10; //number of bombs to generate
    public static final int WIDTH = 10; //width of play board
    public static final int HEIGHT = 16; //height of play board

    private Context context;

    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

    public static GameEngine getInstance()  {
        if(instance == null ) {
            instance = new GameEngine();
        }
        return instance;
    }

    private GameEngine() {

    }

    public void createGrid(Context context){
        Log.e("GameEngine","createGrid is working");
        this.context = context;

        //Creating the grid(play board)
        int[][] GeneratedGrid = Generator.generate(numBombs,WIDTH,HEIGHT);
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        setGrid(context, GeneratedGrid);
    }

    private void setGrid(final Context context, final int[][] grid){
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < HEIGHT; y++){
                if(MinesweeperGrid[x][y] == null){
                    MinesweeperGrid[x][y] = new Cell(context, x, y);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position){
        int x = position % WIDTH;
        int y = position / WIDTH;

        return MinesweeperGrid[x][y];
    }

    public Cell getCellAt(int x, int y){
        return MinesweeperGrid[x][y];
    }

    public void click(int x, int y) {
        if( x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x,y).isClicked() ){
            getCellAt(x,y).setClicked();

            if(getCellAt(x,y).getValue() == 0) { //if the square is not a bomb and has no neighbors, loop to find to reveal squares until neighbors
                for(int xt = -1; xt <= 1; xt++){
                    for(int yt = -1; yt <= 1; yt++) {
                        if(xt != yt){
                            click(x + xt, y + yt);
                        }
                    }
                }
            }

            if(getCellAt(x,y).isBomb()){  //if square clicked is bomb, game over
                onGameLost();
            }
        }

        checkEnd();
    }

    private boolean checkEnd() {
        int bombNotFound = numBombs;
        int notRevealed = WIDTH * HEIGHT;

        for(int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) { //decrement the number of cells not revealed if the cell is revealed or flagged
                    notRevealed--;
                }
                if (getCellAt(x, y).isFlagged() && getCellAt(x, y).isBomb()) { //decrement number of remaining bombs if flagged cell is a bomb
                    bombNotFound--;
                }
            }
        }

        if (bombNotFound == 0 && notRevealed == 0) {
            Toast.makeText(context, "You Win!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //if its flagged, un-flag
    //if its un-flagged, flag
    public void flag(int x, int y){
        boolean isFlagged = getCellAt(x,y).isFlagged();
        getCellAt(x,y).setFlagged(!isFlagged);
        getCellAt(x,y).invalidate();
    }

    private void onGameLost() {
        //handle game over
        Toast.makeText(context,"You Lost", Toast.LENGTH_SHORT).show();

        //Reveal all bombs at game over
        for(int x = 0; x < WIDTH; x++){
            for(int y = 0; y < WIDTH; y++){
                getCellAt(x,y).setRevealed();
            }
        }
    }
}

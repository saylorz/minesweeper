package com.example.minesweeperproject;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.minesweeperproject.util.Generator;
import com.example.minesweeperproject.util.PrintGrid;
import com.example.minesweeperproject.views.grid.Cell;

public class GameEngine {
    private static GameEngine instance;

    public static final int numBombs = 10; //number of bombs to generate
    public static final int WIDTH = 10; //width of play board
    public static final int HEIGHT = 10; //height of play board

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
                    MinesweeperGrid[x][y] = new Cell(context, y * HEIGHT + x);
                }
                MinesweeperGrid[x][y].setValue(grid[x][y]);
                MinesweeperGrid[x][y].invalidate();
            }
        }
    }

    public View getCellAt(int position){
        int x = position % WIDTH;
        int y = position / HEIGHT;

        return MinesweeperGrid[x][y];
    }
}

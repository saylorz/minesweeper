package com.example.minesweeperproject;

import android.content.Context;
import android.util.Log;

import com.example.minesweeperproject.util.Generator;
import com.example.minesweeperproject.util.PrintGrid;

public class GameEngine {
    private static GameEngine instance;

    private static final int numBombs = 10; //number of bombs to generate
    private static final int WIDTH = 10; //width of play board
    private static final int HEIGHT = 10; //height of play board

    private Context context;

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
    }
}

package com.example.minesweeperproject.util;

import java.util.Random;

public class Generator {
    public static int[][] generate(int bombNum, final int width, final int height){
        //Random for generating numbers
        Random randNum = new Random();

        //Creating a 2D-array to store the grid
        int[][] grid = new int[width][height];
        for(int x = 0; x < width; x++){
            grid[x] = new int[height];
        }

        while(bombNum > 0){
            int x = randNum.nextInt(width);
            int y = randNum.nextInt(height);

            // -1 for bomb in square
            if(grid[x][y] != -1){
                grid[x][y] = -1;
                bombNum--;
            }
        }
        grid = numNeighbors(grid,width,height);

        return grid;
    }

    //Display number of bombs that are touching a square
    private static int[][] numNeighbors(int[][] grid, final int width, final int height) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                grid[x][y] = getNeighborNum(grid,x,y,width,height);
            }
        }

        return grid;
    }

    //Find number of bombs touching a square
    private static int getNeighborNum(final int grid[][], final int x, final int y, final int width, final int height){
        //bomb = -1
        if( grid[x][y] == -1 ){
            return -1;
        }

        int count = 0;

        if(isMineAt(grid,x - 1 ,y + 1,width,height)) count++; // top-left
        if(isMineAt(grid,x     ,y + 1,width,height)) count++; // top
        if(isMineAt(grid,x + 1 ,y + 1,width,height)) count++; // top-right
        if(isMineAt(grid,x - 1 ,y    ,width,height)) count++; // left
        if(isMineAt(grid,x + 1 ,y    ,width,height)) count++; // right
        if(isMineAt(grid,x - 1 ,y - 1,width,height)) count++; // bottom-left
        if(isMineAt(grid,x     ,y - 1,width,height)) count++; // bottom
        if(isMineAt(grid,x + 1 ,y - 1,width,height)) count++; // bottom-right

        return count;
    }

    private static boolean isMineAt(final int [][] grid, final int x, final int y, final int width, final int height) {
        if( x >= 0 && y >= 0 && x < width && y < height ){
            if(grid[x][y] == -1){
                return true;
            }
        }
        return false;
    }

}

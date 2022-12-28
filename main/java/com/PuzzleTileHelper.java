package com;

import java.util.Random;

public class PuzzleTileHelper {

    Random random = new Random();
    public int[][] getRandomPuzzleTile(){
        int[][] result = new int[3][3];


        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length;j++){
                int randomNumber = random.nextInt(9);
                result[i][j]= randomNumber;
            }
        }

        return result;
    };
}

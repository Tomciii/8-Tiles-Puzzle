package com;
import java.util.Random;


public class PuzzleTileHelper {

    final private Random random;

    public PuzzleTileHelper(){
        random = new Random();
    }

    private int[] getRandomNumbers(int lengthOfArray, int widthOfArray){

        int[] result = this.initializeRandomIntArray(lengthOfArray, widthOfArray);
        this.swapIntArray(lengthOfArray, widthOfArray, result);

        return result;
    }

    private void swapIntArray(int lengthOfArray, int widthOfArray, int[] result) {
        for (int i = 0; i < lengthOfArray * widthOfArray; i++){

            int index = random.nextInt(lengthOfArray * widthOfArray);
            int swapNumber = result[index];
            result[index] = result[i];
            result[i] = swapNumber;
        }
    }

    private int[] initializeRandomIntArray(int lengthOfArray, int widthOfArray) {
        int result[] = new int[lengthOfArray * widthOfArray];

        for (int i = 0; i < lengthOfArray * widthOfArray; i++){
            result[i] = i;
        }

        return result;
    }


    public int[][] getRandomPuzzleTile(){
        final int lengthOfArray = 3;
        final int widthOfArray = 3;
        final int[][] result = new int[lengthOfArray][widthOfArray];

        final int[] randomNumbers = this.getRandomNumbers(lengthOfArray,widthOfArray);
        int index = 0;

        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length;j++){
                     result[i][j]= randomNumbers[index];
                     index++;
            }
        }

        return result;
    };
}

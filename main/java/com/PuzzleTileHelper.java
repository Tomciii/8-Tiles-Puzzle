package com;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class PuzzleTileHelper {

    final private Random random;

    public PuzzleTileHelper(){
        random = new Random();
    }

    public boolean isSolvable(int[][] puzzleTile){

        boolean isPuzzleSidesEven = this.calculateIsPuzzleOdd(puzzleTile);
        boolean isPuzzleValudesEven = this.isPuzzleValuesEven(puzzleTile);

        return isPuzzleSidesEven ^ isPuzzleValudesEven;
    }

    private boolean isPuzzleValuesEven(int[][] puzzleTile) {
        int[] flatArray = Arrays.stream(puzzleTile)
                .flatMapToInt(Arrays::stream)
                .toArray();

        int result = 0;
        for (int i = 0; i < flatArray.length; i++){

            if (flatArray[i] == 0 || i == flatArray.length - 1){
                continue;
            }

            for (int j = i + 1; j < flatArray.length; j++){

                if (flatArray[j] == 0){
                    continue;
                }

                if (flatArray[i] > flatArray[j]){
                    result++;
                }
            }
        }

        return result % 2 == 0;
    }

    private boolean calculateIsPuzzleOdd(int[][] puzzleTile) {
        return puzzleTile.length * puzzleTile[0].length % 2 == 0;
    }

    public int calculateMisplacedTiles(int[][] puzzleTile) {
        int result = 0;

        int index = 0;
        for (int i = 0; i < puzzleTile.length; i++){
            for (int j = 0; j < puzzleTile[i].length;j++){
                if (puzzleTile[i][j] != index){
                    result++;
                }

                index++;
            }
        }

        return result;
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

    public void generateValidPuzzleTiles(List<PuzzleTile> validPuzzleTiles, PuzzleTile puzzleTile) {

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

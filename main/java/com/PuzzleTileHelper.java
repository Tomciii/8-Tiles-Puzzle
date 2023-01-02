package com;
import com.costCalculator.MisplacedTilesDistanceCostCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The PuzzleTileHelper Class holds all the business logic for intializing and generating new PuzzleTile instances.
 */
public class PuzzleTileHelper {

    final private Random random;

    public PuzzleTileHelper(){
        this.random = new Random();
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

    public void generateValidPuzzleTiles(List<PuzzleTile> validPuzzleTiles, List<PuzzleTile> invalidPuzzleTiles, PuzzleTile puzzleTile) {
        int[][] basePuzzleTile = puzzleTile.getPuzzleTile();
        int currentTurn = puzzleTile.getCurrentTurn();

     OUTER:   for (int i = 0; i < basePuzzleTile.length; i++){
            for (int j = 0; j < basePuzzleTile[i].length; j++){

                if (basePuzzleTile[i][j] == 0){

                    if (i < 2) {
                        this.generateValidPuzzleSwapToBottom(validPuzzleTiles, invalidPuzzleTiles, this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    if (i > 0) {
                        this.generateValidPuzzleSwapToTop(validPuzzleTiles, invalidPuzzleTiles,this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    if (j > 0){
                        this.generateValidPuzzleSwapToLeft(validPuzzleTiles, invalidPuzzleTiles,this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    if (j < 2){
                        this.generateValidPuzzleSwapToRight(validPuzzleTiles, invalidPuzzleTiles, this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    break OUTER;
                }
            }
        }
    }

    private void generateValidPuzzleSwapToRight(List<PuzzleTile> validPuzzleTiles, List<PuzzleTile> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i][j + 1];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i][j  + 1] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, MisplacedTilesDistanceCostCalculator.calculateMisplacedTiles.apply(basePuzzleTile)));
    }

    private void generateValidPuzzleSwapToLeft(List<PuzzleTile> validPuzzleTiles, List<PuzzleTile> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i][j - 1];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i][j - 1] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, MisplacedTilesDistanceCostCalculator.calculateMisplacedTiles.apply(basePuzzleTile)));
    }

    public boolean isContainedInList(List<PuzzleTile> list, int[][] basePuzzleTile){
            return list.stream().anyMatch(puzzle -> Arrays.deepEquals(puzzle.getPuzzleTile(),basePuzzleTile));
    }

    private void generateValidPuzzleSwapToTop(List<PuzzleTile> validPuzzleTiles, List<PuzzleTile> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i - 1][j];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i - 1][j] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, MisplacedTilesDistanceCostCalculator.calculateMisplacedTiles.apply(basePuzzleTile)));
    }

    private int[][] copyArray(int[][] basePuzzleTile){
        int[][]result = new int[basePuzzleTile.length][basePuzzleTile[0].length];

        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[0].length; j++){
                result[i][j] = basePuzzleTile[i][j];
            }
        }
        return result;
    }

    private void generateValidPuzzleSwapToBottom(List<PuzzleTile> validPuzzleTiles, List<PuzzleTile> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i + 1][j];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i + 1][j] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, MisplacedTilesDistanceCostCalculator.calculateMisplacedTiles.apply(basePuzzleTile)));
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

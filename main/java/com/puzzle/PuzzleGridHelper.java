package com.puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * The PuzzleTileHelper Class holds all the business logic for intializing and generating new PuzzleTile instances.
 */
public class PuzzleGridHelper {

    /**
     * Instance of the Random class.
     */
    final private Random random;

    /**
     * Instance of the CostCalculator, passed down from the entry point.
     */
    final private Function<int[][], Integer> costCalculator;

    public PuzzleGridHelper(Function<int[][], Integer> costCalculator){
        this.random = new Random();
        this.costCalculator = costCalculator;
    }

    public boolean isSolvable(int[][] puzzleTile){

        boolean isPuzzleSidesEven = this.calculateIsPuzzleEven(puzzleTile);
        boolean isPuzzleValuesEven = this.isPuzzleValuesEven(puzzleTile);

        return isPuzzleSidesEven ^ isPuzzleValuesEven;
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

    private boolean calculateIsPuzzleEven(int[][] puzzleTile) {
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

    /**
     * Creates all the possible PuzzleTiles it can create and checks if they dont already exist.
     * @param validPuzzleTiles
     * @param invalidPuzzleTiles
     * @param puzzleTile
     */
    public void generateValidPuzzleTiles(List<PuzzleGrid> validPuzzleTiles, List<PuzzleGrid> invalidPuzzleTiles, PuzzleGrid puzzleTile) {
        int[][] basePuzzleTile = puzzleTile.getPuzzleGrid();
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

    private void generateValidPuzzleSwapToRight(List<PuzzleGrid> validPuzzleTiles, List<PuzzleGrid> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i][j + 1];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i][j  + 1] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleGrid(basePuzzleTile, ++currentTurn, costCalculator.apply(basePuzzleTile)));
    }

    private void generateValidPuzzleSwapToLeft(List<PuzzleGrid> validPuzzleTiles, List<PuzzleGrid> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i][j - 1];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i][j - 1] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleGrid(basePuzzleTile, ++currentTurn, costCalculator.apply(basePuzzleTile)));
    }

    /**
     * Checks if a PuzzleTile is contained in a certain list, to help determine whether Tile was already created.
     * @param list
     * @param basePuzzleTile
     * @return
     */
    public boolean isContainedInList(List<PuzzleGrid> list, int[][] basePuzzleTile){
            return list.stream().anyMatch(puzzle -> Arrays.deepEquals(puzzle.getPuzzleGrid(),basePuzzleTile));
    }

    private void generateValidPuzzleSwapToTop(List<PuzzleGrid> validPuzzleTiles, List<PuzzleGrid> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i - 1][j];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i - 1][j] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleGrid(basePuzzleTile, ++currentTurn, costCalculator.apply(basePuzzleTile)));
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

    private void generateValidPuzzleSwapToBottom(List<PuzzleGrid> validPuzzleTiles, List<PuzzleGrid> invalidPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i + 1][j];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i + 1][j] = 0;

        if (isContainedInList(validPuzzleTiles, basePuzzleTile) || isContainedInList(invalidPuzzleTiles, basePuzzleTile)){
            return;
        }

        validPuzzleTiles.add(new PuzzleGrid(basePuzzleTile, ++currentTurn, costCalculator.apply(basePuzzleTile)));
    }

    public int[][] getRandomPuzzleTile(){
        final int lengthOfArray = 3;
        final int widthOfArray = 3;
        final int[][] result = new int[lengthOfArray][widthOfArray];

        final int[] randomNumbers = this.getRandomNumbers(lengthOfArray, widthOfArray);
        int index = 0;

        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length;j++){
                     result[i][j]= randomNumbers[index];
                     index++;
            }
        }

        return result;
    }
}

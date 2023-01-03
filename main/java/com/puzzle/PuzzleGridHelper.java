package com.puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * The PuzzleGridHelper Class holds all the business logic for intializing and generating new PuzzleGrid instances.
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

    public boolean isSolvable(int[][] puzzleGrid){

        boolean isPuzzleSidesEven = this.isPuzzleGridEven(puzzleGrid);
        boolean isPuzzleValuesEven = this.isPuzzleValuesEven(puzzleGrid);

        return isPuzzleSidesEven ^ isPuzzleValuesEven;
    }

    private boolean isPuzzleValuesEven(int[][] puzzleGrid) {
        int[] flatArray = Arrays.stream(puzzleGrid)
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

    private boolean isPuzzleGridEven(int[][] puzzleGrid) {
        return puzzleGrid.length * puzzleGrid[0].length % 2 == 0;
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
     * Creates all the possible PuzzleGrids it can create and checks if they dont already exist.
     * @param validPuzzleGrids
     * @param invalidPuzzleGrids
     * @param puzzleGrid
     */
    public void generateValidPuzzleGrids(List<PuzzleGrid> validPuzzleGrids, List<PuzzleGrid> invalidPuzzleGrids, PuzzleGrid puzzleGrid) {
        int[][] basePuzzleGrid = puzzleGrid.getPuzzleGrid();
        int currentTurn = puzzleGrid.getCurrentTurn();

     OUTER:   for (int i = 0; i < basePuzzleGrid.length; i++){
            for (int j = 0; j < basePuzzleGrid[i].length; j++){

                if (basePuzzleGrid[i][j] == 0){

                    if (i < 2) {
                        this.generateValidPuzzleSwapToBottom(validPuzzleGrids, invalidPuzzleGrids, this.copyArray(basePuzzleGrid), currentTurn, i, j);
                    }

                    if (i > 0) {
                        this.generateValidPuzzleSwapToTop(validPuzzleGrids, invalidPuzzleGrids,this.copyArray(basePuzzleGrid), currentTurn, i, j);
                    }

                    if (j > 0){
                        this.generateValidPuzzleSwapToLeft(validPuzzleGrids, invalidPuzzleGrids,this.copyArray(basePuzzleGrid), currentTurn, i, j);
                    }

                    if (j < 2){
                        this.generateValidPuzzleSwapToRight(validPuzzleGrids, invalidPuzzleGrids, this.copyArray(basePuzzleGrid), currentTurn, i, j);
                    }

                    break OUTER;
                }
            }
        }
    }

    private void generateValidPuzzleSwapToRight(List<PuzzleGrid> validPuzzleGrids, List<PuzzleGrid> invalidPuzzleGrids, int[][] basePuzzleGrid, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleGrid[i][j + 1];
        basePuzzleGrid[i][j] = swapNumber;
        basePuzzleGrid[i][j  + 1] = 0;

        if (isContainedInList(validPuzzleGrids, basePuzzleGrid) || isContainedInList(invalidPuzzleGrids, basePuzzleGrid)){
            return;
        }

        validPuzzleGrids.add(new PuzzleGrid(basePuzzleGrid, ++currentTurn, costCalculator.apply(basePuzzleGrid)));
    }

    private void generateValidPuzzleSwapToLeft(List<PuzzleGrid> validPuzzleGrids, List<PuzzleGrid> invalidPuzzleGrids, int[][] basePuzzleGrid, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleGrid[i][j - 1];
        basePuzzleGrid[i][j] = swapNumber;
        basePuzzleGrid[i][j - 1] = 0;

        if (isContainedInList(validPuzzleGrids, basePuzzleGrid) || isContainedInList(invalidPuzzleGrids, basePuzzleGrid)){
            return;
        }

        validPuzzleGrids.add(new PuzzleGrid(basePuzzleGrid, ++currentTurn, costCalculator.apply(basePuzzleGrid)));
    }

    /**
     * Checks if a PuzzleGrid is contained in a certain list, to help determine whether grid was already created.
     * @param list
     * @param basePuzzleGrid
     * @return
     */
    public boolean isContainedInList(List<PuzzleGrid> list, int[][] basePuzzleGrid){
            return list.stream().anyMatch(puzzle -> Arrays.deepEquals(puzzle.getPuzzleGrid(),basePuzzleGrid));
    }

    private void generateValidPuzzleSwapToTop(List<PuzzleGrid> validPuzzleGrids, List<PuzzleGrid> invalidPuzzleGrids, int[][] basePuzzleGrid, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleGrid[i - 1][j];
        basePuzzleGrid[i][j] = swapNumber;
        basePuzzleGrid[i - 1][j] = 0;

        if (isContainedInList(validPuzzleGrids, basePuzzleGrid) || isContainedInList(invalidPuzzleGrids, basePuzzleGrid)){
            return;
        }

        validPuzzleGrids.add(new PuzzleGrid(basePuzzleGrid, ++currentTurn, costCalculator.apply(basePuzzleGrid)));
    }

    private int[][] copyArray(int[][] basePuzzleGrid){
        int[][]result = new int[basePuzzleGrid.length][basePuzzleGrid[0].length];

        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[0].length; j++){
                result[i][j] = basePuzzleGrid[i][j];
            }
        }
        return result;
    }

    private void generateValidPuzzleSwapToBottom(List<PuzzleGrid> validPuzzleGrids, List<PuzzleGrid> invalidPuzzleGrids, int[][] basePuzzleGrid, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleGrid[i + 1][j];
        basePuzzleGrid[i][j] = swapNumber;
        basePuzzleGrid[i + 1][j] = 0;

        if (isContainedInList(validPuzzleGrids, basePuzzleGrid) || isContainedInList(invalidPuzzleGrids, basePuzzleGrid)){
            return;
        }

        validPuzzleGrids.add(new PuzzleGrid(basePuzzleGrid, ++currentTurn, costCalculator.apply(basePuzzleGrid)));
    }

    public int[][] getRandomPuzzleGrid(){
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

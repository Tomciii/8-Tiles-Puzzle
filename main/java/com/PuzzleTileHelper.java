package com;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The PuzzleTileHelper Class holds all the business logic for intializing and generating new PuzzleTile instances.
 */
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
        int[][] basePuzzleTile = puzzleTile.getPuzzleTile();
        int currentTurn = puzzleTile.getCurrentTurn();

     OUTER:   for (int i = 0; i < basePuzzleTile.length; i++){
            for (int j = 0; j < basePuzzleTile[i].length; j++){

                if (basePuzzleTile[i][j] == 0){

                    if (i < 2) {
                        this.generateValidPuzzleSwapToBottom(validPuzzleTiles, this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    if (i > 0) {
                        this.generateValidPuzzleSwapToTop(validPuzzleTiles, this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    if (j > 0){
                        this.generateValidPuzzleSwapToLeft(validPuzzleTiles, this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    if (j < 2){
                        this.generateValidPuzzleSwapToRight(validPuzzleTiles, this.copyArray(basePuzzleTile), currentTurn, i, j);
                    }

                    break OUTER;
                }
            }
        }
    }

    private void generateValidPuzzleSwapToRight(List<PuzzleTile> validPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i][j + 1];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i][j  + 1] = 0;

        if (validPuzzleTiles.stream().anyMatch(puzzle -> puzzle.getPuzzleTile().equals(basePuzzleTile))){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, this.calculateMisplacedTiles(basePuzzleTile)));
    }

    public int calculateTotalManhattenDistance(int[][] puzzleTile){
        int result = 0;

        for (int i = 0; i < puzzleTile.length; i++){
            for (int j = 0; j < puzzleTile[0].length; j++){
                int tileNumber = puzzleTile[i][j];

                if (tileNumber == 0){
                    result += i;
                    result += j;
                    continue;
                }

                if (tileNumber == 1 ||tileNumber == 2){
                    if (i == 1){
                        result += 1;
                    }

                    if (i == 2){
                        result += 2;
                    }
                }

                if (tileNumber == 3 ||tileNumber == 4 || tileNumber == 5){
                    if (i == 0){
                        result += 1;
                    }

                    if (i == 2){
                        result += 1;
                    }
                }

                if (tileNumber == 6 ||tileNumber == 7 || tileNumber == 8){
                    if (i == 0){
                        result += 2;
                    }

                    if (i == 1){
                        result += 1;
                    }
                }

                if (tileNumber == 3 || tileNumber == 6){
                    if (j == 1){
                        result++;
                    }

                    if (j == 2){
                        result += 2;
                    }
                }

                if (tileNumber == 1 || tileNumber == 4 || tileNumber == 7){
                    if (j == 0){
                        result++;
                    }

                    if (j == 2){
                        result += 1;
                    }
                }

                if (tileNumber == 2 || tileNumber == 5 || tileNumber == 8){
                    if (j == 0){
                        result += 2;
                    }

                    if (j == 1){
                        result += 1;
                    }
                }
            }
        }

        return result;
    }

    private void generateValidPuzzleSwapToLeft(List<PuzzleTile> validPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i][j - 1];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i][j - 1] = 0;

        if (validPuzzleTiles.stream().anyMatch(puzzle -> puzzle.getPuzzleTile().equals(basePuzzleTile))){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, this.calculateMisplacedTiles(basePuzzleTile)));
    }

    private void generateValidPuzzleSwapToTop(List<PuzzleTile> validPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i - 1][j];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i - 1][j] = 0;

        if (validPuzzleTiles.stream().anyMatch(puzzle -> puzzle.getPuzzleTile().equals(basePuzzleTile))){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, this.calculateMisplacedTiles(basePuzzleTile)));
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

    private void generateValidPuzzleSwapToBottom(List<PuzzleTile> validPuzzleTiles, int[][] basePuzzleTile, int currentTurn, int i, int j) {
        int swapNumber = basePuzzleTile[i + 1][j];
        basePuzzleTile[i][j] = swapNumber;
        basePuzzleTile[i + 1][j] = 0;

        if (validPuzzleTiles.stream().anyMatch(puzzle -> puzzle.getPuzzleTile().equals(basePuzzleTile))){
            return;
        }

        validPuzzleTiles.add(new PuzzleTile(basePuzzleTile, ++currentTurn, this.calculateMisplacedTiles(basePuzzleTile)));
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

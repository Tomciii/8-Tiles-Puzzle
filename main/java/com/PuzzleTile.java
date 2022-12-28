package com;

import java.util.Arrays;

public class PuzzleTile {

    final private int[][] puzzleTile;
    private int misplacedTiles;
    private int manhattenValue;
    private boolean isSolvable;
    private int fn;

    public int getCurrentTurn() {
        return currentTurn;
    }

    private int currentTurn;

    public boolean isSolvable() {
        return isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int currentTurn, int misplacedTiles, boolean isSolvable){
        this.puzzleTile = puzzleTile;
        this.currentTurn = currentTurn;
        this.misplacedTiles = misplacedTiles;
        this.fn = this.misplacedTiles + this.currentTurn;
        this.isSolvable = isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int currentTurn, int manhattenValue, boolean solveWithManhattenValue,boolean isSolvable){
        this.puzzleTile = puzzleTile;
        this.currentTurn = currentTurn;
        this.manhattenValue = manhattenValue;
        this.isSolvable = isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int earliestTurn, int misplacedTiles){
        this.puzzleTile = puzzleTile;
        this.currentTurn = earliestTurn;
        this.misplacedTiles = misplacedTiles;
        this.fn = this.misplacedTiles + this.currentTurn;
    }

    public int[][] getPuzzleTile() {
        return puzzleTile;
    }

    @Override
    public String toString() {
        return "PuzzleTile{" +
                "puzzleTile=" + Arrays.deepToString(puzzleTile) +
                ", earliestTurn=" + currentTurn +
                ", misplacedTiles=" + misplacedTiles +
                ", manhattenValue=" + manhattenValue +
                ", fn=" + fn +
                ", isSolvable=" + isSolvable +
                '}';
    }


}

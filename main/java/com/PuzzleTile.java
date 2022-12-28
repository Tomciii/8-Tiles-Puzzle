package com;

import java.util.Arrays;

/**
 * A PuzzleTile represents a gameBoard and holds information about whether it's solvable etc.
 */
public class PuzzleTile {

    final private int[][] puzzleTile;
    private int misplacedTiles;
    private int manhattenDistance;
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

    public PuzzleTile(int[][] puzzleTile, int currentTurn, int manhattenDistance, boolean solveWithManhattenValue,boolean isSolvable){
        this.puzzleTile = puzzleTile;
        this.currentTurn = currentTurn;
        this.manhattenDistance = manhattenDistance;
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
                ", manhattenValue=" + manhattenDistance +
                ", fn=" + fn +
                ", isSolvable=" + isSolvable +
                '}';
    }


}

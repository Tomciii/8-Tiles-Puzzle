package com;

import java.util.Arrays;

/**
 * A PuzzleTile represents a gameBoard and holds information about whether it's solvable etc.
 */
public class PuzzleTile {

    final private int[][] puzzleTile;

    public int getCost() {
        return cost;
    }

    /**
     * Cost is either determined through manhatten distance or misplaced tiles.
     */
    private int cost;
    private boolean isSolvable;

    public int getFn() {
        return fn;
    }

    private int fn;

    public int getCurrentTurn() {
        return currentTurn;
    }

    private int currentTurn;

    public boolean isSolvable() {
        return isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int currentTurn, int cost, boolean isSolvable){
        this.puzzleTile = puzzleTile;
        this.currentTurn = currentTurn;
        this.cost = cost;
        this.fn = this.cost + this.currentTurn;
        this.isSolvable = isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int currentTurn, int cost){
        this.puzzleTile = puzzleTile;
        this.currentTurn = currentTurn;
        this.cost = cost;
        this.fn = this.cost + this.currentTurn;
    }


    public int[][] getPuzzleTile() {
        return puzzleTile;
    }

    @Override
    public String toString() {
        return "PuzzleTile{" +
                "puzzleTile=" + Arrays.deepToString(puzzleTile) +
                ", earliestTurn=" + currentTurn +
                ", cost=" + cost +
                ", fn=" + fn +
                ", isSolvable=" + isSolvable +
                '}';
    }


}

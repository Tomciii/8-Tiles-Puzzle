package com;

import java.util.Arrays;

/**
 * A PuzzleTile represents a puzzleBoard and holds information about whether it's solvable or not.
 * Designed to be immutable.
 */
public class PuzzleTile {

    private final int[][] puzzleBoard;

    public int getCost() {
        return cost;
    }

    /**
     * Cost is either determined through manhatten distance or misplaced tiles.
     */
    private int cost;

    /**
     * Only used for the initializing PuzzleTile
     */
    private boolean isSolvable;

    /**
     * Fn = Current turn + cost.
     */
    private int fn;

    private int currentTurn;

    /**
     * For the initial PuzzleTile, this constructor is called.
     * @param puzzleTile
     * @param currentTurn
     * @param cost
     * @param isSolvable
     */
    public PuzzleTile(int[][] puzzleTile, int currentTurn, int cost, boolean isSolvable){
        this.puzzleBoard = puzzleTile;
        this.currentTurn = currentTurn;
        this.cost = cost;
        this.fn = this.cost + this.currentTurn;
        this.isSolvable = isSolvable;
    }

    /**
     * For non-initial PuzzleTiles, this constructor is called.
     * @param puzzleTile
     * @param currentTurn
     * @param cost
     */
    public PuzzleTile(int[][] puzzleTile, int currentTurn, int cost){
        this.puzzleBoard = puzzleTile;
        this.currentTurn = currentTurn;
        this.cost = cost;
        this.fn = this.cost + this.currentTurn;
    }


    public int[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    @Override
    public String toString() {
        return "PuzzleTile{" +
                "puzzleTile=" + Arrays.deepToString(puzzleBoard) +
                ", earliestTurn=" + currentTurn +
                ", cost=" + cost +
                ", fn=" + fn +
                ", isSolvable=" + isSolvable +
                '}';
    }

    public int getFn() {
        return fn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public boolean isSolvable() {
        return isSolvable;
    }


}

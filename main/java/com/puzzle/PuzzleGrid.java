package com.puzzle;

import java.util.Arrays;

/**
 * A PuzzleGrid represents a puzzleBoard and holds information about whether it's solvable or not.
 * Designed to be immutable.
 */
public class PuzzleGrid {

    private final int[][] puzzleGrid;

    public int getCost() {
        return cost;
    }

    /**
     * Cost is either determined through manhatten distance or misplaced grids.
     */
    private int cost;

    /**
     * Only used for the initializing PuzzleGrid
     */
    private boolean isSolvable;

    /**
     * Fn = Current turn + cost.
     */
    private int fn;

    private int currentTurn;

    /**
     * For the initial PuzzleGrid, this constructor is called.
     * @param puzzleGrid
     * @param currentTurn
     * @param cost
     * @param isSolvable
     */
    public PuzzleGrid(int[][] puzzleGrid, int currentTurn, int cost, boolean isSolvable){
        this.puzzleGrid = puzzleGrid;
        this.currentTurn = currentTurn;
        this.cost = cost;
        this.fn = this.cost + this.currentTurn;
        this.isSolvable = isSolvable;
    }

    /**
     * For non-initial PuzzleGrids, this constructor is called.
     * @param puzzleGrids
     * @param currentTurn
     * @param cost
     */
    public PuzzleGrid(int[][] puzzleGrids, int currentTurn, int cost){
        this.puzzleGrid = puzzleGrids;
        this.currentTurn = currentTurn;
        this.cost = cost;
        this.fn = this.cost + this.currentTurn;
    }


    public int[][] getPuzzleGrid() {
        return puzzleGrid;
    }

    @Override
    public String toString() {
        return "PuzzleGrid{" +
                "puzzleGrid=" + Arrays.deepToString(puzzleGrid) +
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

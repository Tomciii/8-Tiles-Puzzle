package com;

import java.util.Arrays;
import java.util.List;

public class PuzzleTile {

    final private int[][] puzzleTile;
    private int misplacedTiles;
    private int manhattenValue;
    private boolean isSolvable;
    private int fn;
    private int earliestTurn;

    public boolean isSolvable() {
        return isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int earliestTurn, int misplacedTiles, boolean isSolvable){
        this.puzzleTile = puzzleTile;
        this.earliestTurn = earliestTurn;
        this.misplacedTiles = misplacedTiles;
        this.fn = this.misplacedTiles + this.earliestTurn;
        this.isSolvable = isSolvable;
    }

    public PuzzleTile(int[][] puzzleTile, int earliestTurn, int misplacedTiles){
        this.puzzleTile = puzzleTile;
        this.earliestTurn = earliestTurn;
        this.misplacedTiles = misplacedTiles;
        this.fn = this.misplacedTiles + this.earliestTurn;
        this.isSolvable = isSolvable;
    }

    public int[][] getPuzzleTile() {
        return puzzleTile;
    }

    @Override
    public String toString() {
        return "PuzzleTile{" +
                "puzzleTile=" + Arrays.deepToString(puzzleTile) +
                ", earliestTurn=" + earliestTurn +
                ", misplacedTiles=" + misplacedTiles +
                ", manhattenValue=" + manhattenValue +
                ", fn=" + fn +
                ", isSolvable=" + isSolvable +
                '}';
    }


}

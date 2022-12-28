package com;

import java.util.Arrays;

public class PuzzleTile {

    private int[][] puzzleTile;
    private int misplacedTiles;
    private int manhattenValue;
    private int fn;

    public int getMisplacedTiles() {
        return misplacedTiles;
    }

    public PuzzleTile(int[][] puzzleTile){
        this.puzzleTile = puzzleTile;
    }

    public boolean isSolvable(){
        return false;
    }

    public void setMisplacedTiles(int misplacedTiles) {
        this.misplacedTiles = misplacedTiles;
    }

    public int[][] getPuzzleTile() {
        return puzzleTile;
    }

    private void setPuzzleTile(int[][] puzzleTile) {
        this.puzzleTile = puzzleTile;
    }

    @Override
    public String toString() {
        return "PuzzleTile{" +
                "puzzleTile=" + Arrays.deepToString(puzzleTile) +
                ", misplacedTiles=" + misplacedTiles +
                ", manhattenValue=" + manhattenValue +
                ", fn=" + fn +
                '}';
    }
}

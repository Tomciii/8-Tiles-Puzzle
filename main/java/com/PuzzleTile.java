package com;

public class PuzzleTile {

    private int[][] puzzleTile;
    private int misplacedTiles;
    private int manhattenValue;
    private int fn;

    public int getMisplacedTiles() {
        return misplacedTiles;
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

    public void setPuzzleTile(int[][] puzzleTile) {
        this.puzzleTile = puzzleTile;
    }
}

package com;

import java.util.Arrays;

public class PuzzleTile {

    final private int[][] puzzleTile;
    private int misplacedTiles;
    private int manhattenValue;
    private int fn;
    private int earliestTurn;

    public int getMisplacedTiles() {
        return misplacedTiles;
    }

    public PuzzleTile(int[][] puzzleTile, int earliestTurn){
        this.puzzleTile = puzzleTile;
        this.earliestTurn = earliestTurn;
        this.misplacedTiles = this.calculateMisplacedTiles();
    }

    private int calculateMisplacedTiles() {
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

    public boolean isSolvable(){
        return false;
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
                ", isSolvable=" + isSolvable() +
                '}';
    }
}

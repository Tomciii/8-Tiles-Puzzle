package com;

import java.util.HashSet;
import java.util.Set;

public class Puzzle {

    private PuzzleTileHelper puzzleTileHelper;

    public int getTurn() {
        return turn;
    }

    private void initiatePuzzleTile(){
        int[][] puzzleTile = this.puzzleTileHelper.getRandomPuzzleTile();
        PuzzleTile puzzleTile1 = new PuzzleTile(puzzleTile);

        System.out.println(puzzleTile1);
        this.validPuzzleTiles.add(puzzleTile1);
    }

    public void play(){
        this.initiatePuzzleTile();
    };

    public Set<PuzzleTile> getValidPuzzleTiles() {
        return validPuzzleTiles;
    }

    public void setValidPuzzleTiles(Set<PuzzleTile> validPuzzleTiles) {
        this.validPuzzleTiles = validPuzzleTiles;
    }

    public Set<PuzzleTile> getInvalidPuzzleTiles() {
        return invalidPuzzleTiles;
    }

    public void setInvalidPuzzleTiles(Set<PuzzleTile> invalidPuzzleTiles) {
        this.invalidPuzzleTiles = invalidPuzzleTiles;
    }

    private Set<PuzzleTile> validPuzzleTiles;
    private Set<PuzzleTile> invalidPuzzleTiles;

    public void setTurn(int turn) {
        this.turn = turn;
    }

    private int turn;

    public Puzzle(){
        this.turn = 0;
        this.puzzleTileHelper = new PuzzleTileHelper();
        this.validPuzzleTiles = new HashSet<>();
        this.invalidPuzzleTiles = new HashSet<>();
    }
}

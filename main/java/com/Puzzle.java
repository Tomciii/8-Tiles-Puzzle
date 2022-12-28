package com;

import java.util.*;

public class Puzzle {

    private PuzzleTileHelper puzzleTileHelper;
    private int currentTurn;

    public int getCurrentTurn() {
        return currentTurn;
    }

    private void initiatePuzzleTile(){
        int[][] puzzleTile = this.puzzleTileHelper.getRandomPuzzleTile();
        PuzzleTile puzzleTile1 = new PuzzleTile(puzzleTile, this.currentTurn);

        this.validPuzzleTiles.add(puzzleTile1);
        System.out.println(validPuzzleTiles.get(0));
    }

    public void play(){
        this.initiatePuzzleTile();
    };

    public List<PuzzleTile> getValidPuzzleTiles() {
        return validPuzzleTiles;
    }

    public void setValidPuzzleTiles(List<PuzzleTile> validPuzzleTiles) {
        this.validPuzzleTiles = validPuzzleTiles;
    }

    public List<PuzzleTile> getInvalidPuzzleTiles() {
        return invalidPuzzleTiles;
    }

    public void setInvalidPuzzleTiles(List<PuzzleTile> invalidPuzzleTiles) {
        this.invalidPuzzleTiles = invalidPuzzleTiles;
    }

    private List<PuzzleTile> validPuzzleTiles;
    private List<PuzzleTile> invalidPuzzleTiles;

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Puzzle(){
        this.currentTurn = 0;
        this.puzzleTileHelper = new PuzzleTileHelper();
        this.validPuzzleTiles = new ArrayList<>();
        this.invalidPuzzleTiles = new ArrayList<>();
    }
}

package com;

import java.util.Set;

public class Puzzle {

    public int getTurn() {
        return turn;
    }

    public void play(){

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
        turn = 0;
    }
}

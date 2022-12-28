package com;

import java.util.*;

/**
 * The Puzzle initializes with a solvable PuzzleTile and then executes the algorithm logic.
 */
public class Puzzle {

    private PuzzleTileHelper puzzleTileHelper;
    private int currentTurn;
    private List<PuzzleTile> validPuzzleTiles;
    private List<PuzzleTile> invalidPuzzleTiles;

    public Puzzle(){
        this.currentTurn = 0;
        this.puzzleTileHelper = new PuzzleTileHelper();
        this.validPuzzleTiles = new ArrayList<>();
        this.invalidPuzzleTiles = new ArrayList<>();
    }

    private void initiatePuzzleTile(){
        PuzzleTile startPosition;

        do {
            int[][] puzzleTile = this.puzzleTileHelper.getRandomPuzzleTile();
            startPosition = new PuzzleTile(puzzleTile,
                    this.currentTurn,
                    this.puzzleTileHelper.calculateTotalManhattenDistance(puzzleTile),
                    true,
                    this.puzzleTileHelper.isSolvable(puzzleTile)
                );

            if (startPosition.isSolvable()){
                this.validPuzzleTiles.add(startPosition);
                System.out.println(validPuzzleTiles.get(0));
            }
        } while (!startPosition.isSolvable());

        this.currentTurn++;
    }

    public void play(){
        this.initiatePuzzleTile();
        this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.validPuzzleTiles.get(0));
    }
}
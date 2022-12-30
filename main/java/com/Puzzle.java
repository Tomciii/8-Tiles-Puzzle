package com;

import java.util.*;

/**
 * The Puzzle initializes with a solvable PuzzleTile and then executes the algorithm logic.
 */
public class Puzzle {

    final private PuzzleTileHelper puzzleTileHelper;
    private int currentTurn;

    /**
     * The validPuzzleTiles list contains all PuzzleTiles that have not been traversed yet.
     */
    private List<PuzzleTile> validPuzzleTiles;

    /**
     * The validPuzzleTiles list contains all PuzzleTiles that have been traversed.
     */
    private List<PuzzleTile> invalidPuzzleTiles;

    public Puzzle(){
        this.currentTurn = 0;
        this.puzzleTileHelper = new PuzzleTileHelper();
        this.validPuzzleTiles = new ArrayList<>();
        this.invalidPuzzleTiles = new ArrayList<>();
    }

    /**
     * Generates the starting PuzzleTile, which has to be a solvable PuzzleTile.
     */
    private void generateStartingPuzzleTile(){
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
        this.generateStartingPuzzleTile();
        this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.validPuzzleTiles.get(0));
    }
}
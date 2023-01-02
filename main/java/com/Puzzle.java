package com;

import java.util.*;
import java.util.function.Function;


/**
 * The Puzzle initializes with a solvable PuzzleTile and then executes the algorithm logic.
 */
public class Puzzle {

    final private PuzzleTileHelper puzzleTileHelper;
    private int currentTurn;
    Function<int[][], Integer> costCalculator;

    /**
     * The validPuzzleTiles list contains all PuzzleTiles that have not been traversed yet.
     */
    private List<PuzzleTile> validPuzzleTiles;

    /**
     * The validPuzzleTiles list contains all PuzzleTiles that have been traversed.
     */
    private List<PuzzleTile> invalidPuzzleTiles;

    public Puzzle(Function<int[][], Integer> costCalculator){
        this.costCalculator = costCalculator;
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
                    this.costCalculator.apply(puzzleTile),
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
        this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.invalidPuzzleTiles, this.validPuzzleTiles.get(0));

        while(!this.isPuzzleSolved(this.pickLowestCostPuzzleTile())){
            PuzzleTile puzzleTile = this.pickLowestCostPuzzleTile();
            System.out.println("Generating New PuzzleTiles");
            this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.invalidPuzzleTiles, puzzleTile);
            this.movePuzzleTileToInvalidList();
        }

        System.out.println("Puzzle Solved!");
    }

    private boolean isPuzzleSolved(PuzzleTile puzzleTile){
        return puzzleTile.getCost() == this.currentTurn;
    }

    private void movePuzzleTileToInvalidList(){
        this.invalidPuzzleTiles.add(this.validPuzzleTiles.stream().min(Comparator.comparing(puzzleTile -> puzzleTile.getFn())).get());
        this.validPuzzleTiles.remove(this.validPuzzleTiles.stream().min(Comparator.comparing(puzzleTile -> puzzleTile.getFn())).get());
    }
    private PuzzleTile pickLowestCostPuzzleTile(){
        return this.validPuzzleTiles.stream().min(Comparator.comparing(puzzleTile -> puzzleTile.getFn())).get();
    }
}
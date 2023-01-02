package com;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Puzzle initializes with a solvable PuzzleTile and then executes the algorithm logic.
 */
public class Puzzle {

    /**
     * Used to determine the duration of the program.
     */
    private final double startTime = System.nanoTime();

    /**
     * An instance of the PuzzleTileHelper class.
     */
    final private PuzzleTileHelper puzzleTileHelper;
    private int currentTurn;

    /**
     * Instance of the CostCalculator, passed down from the entry point.
     */
    final private Function<int[][], Integer> costCalculator;

    /**
     * The end position to solve the puzzle.
     */
    final private int[][] endPosition = {{0,1,2},{3,4,5},{6,7,8}};

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
        this.puzzleTileHelper = new PuzzleTileHelper(costCalculator);
        this.validPuzzleTiles = new ArrayList<>();
        this.invalidPuzzleTiles = new ArrayList<>();
    }

    /**
     * Generates the starting PuzzleTile, which has to be a solvable PuzzleTile.
     */
    private void generateStartingPuzzleTile(){
        PuzzleTile startPosition;
        System.out.println("Generating Initial PuzzleTile..");

        do {
            int[][] puzzleTile = this.puzzleTileHelper.getRandomPuzzleTile();
            startPosition = new PuzzleTile(puzzleTile,
                    this.currentTurn,
                    this.costCalculator.apply(puzzleTile),
                    this.puzzleTileHelper.isSolvable(puzzleTile)
                );
        } while (startPosition.isSolvable() == false);

        this.validPuzzleTiles.add(startPosition);
        System.out.println(validPuzzleTiles.get(0));
        this.currentTurn++;
    }

    /**
     * Generates a starting position, then goes into a loop to open all the nodes and tries to solve the puzzle.
     */
    public void solve(){
        this.generateStartingPuzzleTile();
        this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.invalidPuzzleTiles, this.validPuzzleTiles.get(0));

        this.solvePuzzle();

        final double endTime = System.nanoTime();
        final double duration = (endTime - this.startTime) / 1_000_000;

        System.out.format("Duration taken in milli seconds: " + duration);
    }

    /**
     * Loops through the lowest cost Puzzle Tiles, checks
     */
    private void solvePuzzle() {
        System.out.println("Solving Puzzle..");

        while(!this.isPuzzleSolved(this.getLowestCostPuzzleTile())){
            int index = this.getLowestCostPuzzleTileIndex();
            PuzzleTile currentPuzzleTile = this.validPuzzleTiles.get(index);
            this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.invalidPuzzleTiles, currentPuzzleTile);
            this.movePuzzleTileToInvalidList(index);
        }

        System.out.println("Puzzle Solved!");
        System.out.println(this.getLowestCostPuzzleTile());
    }

    /**
     * Compares the currentPuzzleTile with the endposition.
     * @param currentPuzzleTile
     * @return
     */
    private boolean isPuzzleSolved(PuzzleTile currentPuzzleTile){
        return Arrays.deepEquals(currentPuzzleTile.getPuzzleTile(),this.endPosition);
    }

    private void movePuzzleTileToInvalidList(int index){
        this.invalidPuzzleTiles.add(this.validPuzzleTiles.get(index));
        this.validPuzzleTiles.remove(this.validPuzzleTiles.get(index));
    }

    private PuzzleTile getLowestCostPuzzleTile(){
        return this.validPuzzleTiles.stream().min(Comparator.comparing(PuzzleTile::getFn).thenComparing(PuzzleTile::getCost)).get();
    }

    private int getLowestCostPuzzleTileIndex(){
        PuzzleTile puzzleTile = this.getLowestCostPuzzleTile();
        return this.validPuzzleTiles.indexOf(puzzleTile);
    }
}
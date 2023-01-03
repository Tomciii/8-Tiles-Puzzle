package com.puzzle;

import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * The Puzzle initializes with a solvable PuzzleTile and then executes the algorithm logic.
 */
public class Puzzle {

    private static final Logger logger = Logger.getLogger(Puzzle.class.getName());

    /**
     * Used to determine the duration of the program.
     */
    private final double startTime = System.nanoTime();

    /**
     * An instance of the PuzzleTileHelper class.
     */
    private final PuzzleGridHelper puzzleTileHelper;
    private int currentTurn;

    /**
     * Instance of the CostCalculator, passed down from the entry point.
     */
    private final Function<int[][], Integer> costCalculator;

    /**
     * The end position to solve the puzzle.
     */
    private final int[][] endPosition = {{0,1,2},{3,4,5},{6,7,8}};

    /**
     * The validPuzzleTiles list contains all PuzzleTiles that have not been traversed yet.
     */
    private List<PuzzleGrid> validPuzzleGrids;

    /**
     * The validPuzzleTiles list contains all PuzzleTiles that have been traversed.
     */
    private List<PuzzleGrid> invalidPuzzleGrids;

    public Puzzle(Function<int[][], Integer> costCalculator){
        this.costCalculator = costCalculator;
        this.currentTurn = 0;
        this.puzzleTileHelper = new PuzzleGridHelper(costCalculator);
        this.validPuzzleGrids = new ArrayList<>();
        this.invalidPuzzleGrids = new ArrayList<>();
    }

    /**
     * Generates the starting PuzzleGrid, which has to be a solvable PuzzleTile.
     */
    private void generateStartingPuzzleTile(){
        PuzzleGrid startPosition;

        logger.info("Generating initial PuzzleGrid...");

        do {
            int[][] puzzleTile = this.puzzleTileHelper.getRandomPuzzleTile();
            startPosition = new PuzzleGrid(puzzleTile,
                    this.currentTurn,
                    this.costCalculator.apply(puzzleTile),
                    this.puzzleTileHelper.isSolvable(puzzleTile)
                );
        } while (!startPosition.isSolvable());

        this.validPuzzleGrids.add(startPosition);
        logger.info(this.validPuzzleGrids.get(0).toString());
        this.currentTurn++;
    }

    /**
     * Generates a starting position, then goes into a loop to open all the nodes and tries to solve the puzzle.
     */
    public void solve(){
        this.generateStartingPuzzleTile();
        this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleGrids, this.invalidPuzzleGrids, this.validPuzzleGrids.get(0));

        this.solvePuzzle();

        final double endTime = System.nanoTime();
        final double duration = (endTime - this.startTime) / 1_000_000;

        logger.info("Duration taken in milli seconds: " + duration);
    }

    /**
     * Loops through the lowest cost Puzzle Tiles, checks
     */
    private void solvePuzzle() {

        logger.info("Solving Puzzle..");

        while(!this.isPuzzleSolved(this.getLowestCostPuzzleTile())){
            int index = this.getLowestCostPuzzleTileIndex();
            PuzzleGrid currentPuzzleTile = this.validPuzzleGrids.get(index);
            this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleGrids, this.invalidPuzzleGrids, currentPuzzleTile);
            this.movePuzzleTileToInvalidList(index);
        }

        logger.info("Puzzle Solved!");
        logger.info(this.getLowestCostPuzzleTile().toString());
    }

    /**
     * Compares the currentPuzzleTile with the endposition.
     * @param currentPuzzleTile
     * @return
     */
    private boolean isPuzzleSolved(PuzzleGrid currentPuzzleTile){
        return Arrays.deepEquals(currentPuzzleTile.getPuzzleGrid(),this.endPosition);
    }

    private void movePuzzleTileToInvalidList(int index){
        this.invalidPuzzleGrids.add(this.validPuzzleGrids.get(index));
        this.validPuzzleGrids.remove(this.validPuzzleGrids.get(index));
    }

    private PuzzleGrid getLowestCostPuzzleTile(){
        return this.validPuzzleGrids.stream().min(Comparator.comparing(PuzzleGrid::getFn).thenComparing(PuzzleGrid::getCost)).get();
    }

    private int getLowestCostPuzzleTileIndex(){
        PuzzleGrid puzzleTile = this.getLowestCostPuzzleTile();
        return this.validPuzzleGrids.indexOf(puzzleTile);
    }
}
package com.puzzle;

import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * The Puzzle initializes with a solvable PuzzleGrid and then executes the algorithm logic.
 */
public class Puzzle {

    private static final Logger logger = Logger.getLogger(Puzzle.class.getName());

    /**
     * Used to determine the duration of the program.
     */
    private final double startTime = System.nanoTime();

    /**
     * An instance of the PuzzleGridHelper class.
     */
    private final PuzzleGridHelper puzzleGridHelper;
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
     * The validPuzzleGrids list contains all PuzzleGrids that have not been traversed yet.
     */
    private List<PuzzleGrid> validPuzzleGrids;

    /**
     * The invalidPuzzleGrids list contains all PuzzleGrids that have been traversed.
     */
    private List<PuzzleGrid> invalidPuzzleGrids;

    public Puzzle(Function<int[][], Integer> costCalculator){
        this.costCalculator = costCalculator;
        this.currentTurn = 0;
        this.puzzleGridHelper = new PuzzleGridHelper(costCalculator);
        this.validPuzzleGrids = new ArrayList<>();
        this.invalidPuzzleGrids = new ArrayList<>();
    }

    /**
     * Generates the starting PuzzleGrid, which has to be a solvable PuzzleGrid.
     */
    private void generateStartingPuzzleGrid(){
        PuzzleGrid startPosition;

        logger.info("Generating initial PuzzleGrid...");

        do {
            int[][] puzzleGrid = this.puzzleGridHelper.getRandomPuzzleGrid();
            startPosition = new PuzzleGrid(puzzleGrid,
                    this.currentTurn,
                    this.costCalculator.apply(puzzleGrid),
                    this.puzzleGridHelper.isSolvable(puzzleGrid)
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
        this.generateStartingPuzzleGrid();
        this.puzzleGridHelper.generateValidPuzzleGrids(this.validPuzzleGrids, this.invalidPuzzleGrids, this.validPuzzleGrids.get(0));

        this.solvePuzzle();

        final double endTime = System.nanoTime();
        final double duration = (endTime - this.startTime) / 1_000_000;

        logger.info("Duration taken in milli seconds: " + duration);
    }

    /**
     * Loops through the lowest cost Puzzle Grids, checks
     */
    private void solvePuzzle() {

        logger.info("Solving Puzzle..");

        while(!this.isPuzzleSolved(this.getLowestCostPuzzleGrid())){
            int index = this.getLowestCostPuzzleGridIndex();
            PuzzleGrid currentPuzzleGrid = this.validPuzzleGrids.get(index);
            this.puzzleGridHelper.generateValidPuzzleGrids(this.validPuzzleGrids, this.invalidPuzzleGrids, currentPuzzleGrid);
            this.movePuzzleGridToInvalidList(index);
        }

        logger.info("Puzzle Solved!");
        logger.info(this.getLowestCostPuzzleGrid().toString());
    }

    /**
     * Compares the currentPuzzleGrid with the endposition.
     * @param currentPuzzleGrid
     * @return
     */
    private boolean isPuzzleSolved(PuzzleGrid currentPuzzleGrid){
        return Arrays.deepEquals(currentPuzzleGrid.getPuzzleGrid(),this.endPosition);
    }

    private void movePuzzleGridToInvalidList(int index){
        this.invalidPuzzleGrids.add(this.validPuzzleGrids.get(index));
        this.validPuzzleGrids.remove(this.validPuzzleGrids.get(index));
    }

    private PuzzleGrid getLowestCostPuzzleGrid(){
        return this.validPuzzleGrids.stream().min(Comparator.comparing(PuzzleGrid::getFn).thenComparing(PuzzleGrid::getCost)).get();
    }

    private int getLowestCostPuzzleGridIndex(){
        PuzzleGrid puzzleGrid = this.getLowestCostPuzzleGrid();
        return this.validPuzzleGrids.indexOf(puzzleGrid);
    }
}
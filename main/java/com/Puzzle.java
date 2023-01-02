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
    private final long startTime = System.nanoTime();

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


    public void solve(){
        System.out.println("Generating Initial PuzzleTile..");
        this.generateStartingPuzzleTile();
        this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.invalidPuzzleTiles, this.validPuzzleTiles.get(0));

        System.out.println("Solving Puzzle..");
        solvePuzzle();

        final long endTime = System.nanoTime();
        final long duration = (endTime - this.startTime) / 1_000_000_000;

        System.out.println("Puzzle Solved!");
        System.out.println(duration);
    }

    /**
     * Loops through the lowest cost Puzzle Tiles, checks
     */
    private void solvePuzzle() {

        while(!this.isPuzzleSolved(this.getLowestCostPuzzleTile())){
            PuzzleTile currentPuzzleTile = this.getLowestCostPuzzleTile();
            this.puzzleTileHelper.generateValidPuzzleTiles(this.validPuzzleTiles, this.invalidPuzzleTiles, currentPuzzleTile);
            this.movePuzzleTileToInvalidList();
            if (this.validPuzzleTiles.size() == 0){
                System.out.println();
            }
            System.out.println(currentPuzzleTile.getCurrentTurn() + " " + currentPuzzleTile.getCost());
        }
    }

    /**
     * Compares the currentPuzzleTile with the endposition.
     * @param currentPuzzleTile
     * @return
     */
    private boolean isPuzzleSolved(PuzzleTile currentPuzzleTile){
        return Arrays.deepEquals(currentPuzzleTile.getPuzzleTile(),this.endPosition);
    }

    private void movePuzzleTileToInvalidList(){
        this.invalidPuzzleTiles.add(this.validPuzzleTiles.stream().min(Comparator.comparing(puzzleTile -> puzzleTile.getFn())).get());
        this.validPuzzleTiles.remove(this.validPuzzleTiles.stream().min(Comparator.comparing(puzzleTile -> puzzleTile.getFn())).get());
    }

    private PuzzleTile getLowestCostPuzzleTile(){

            List<PuzzleTile> list = this.validPuzzleTiles.stream().filter(a -> a.getFn() == this.validPuzzleTiles.stream().min(Comparator.comparing(PuzzleTile::getFn)).get().getFn()).collect(Collectors.toList());
            if (list.size() > 2){
               return list.stream().min(Comparator.comparing(PuzzleTile::getCost)).get();
            }

                Optional<PuzzleTile> result = this.validPuzzleTiles
                        .stream()
                        .min(Comparator.comparing(puzzleTile -> puzzleTile.getFn()));

            return result.get();
    }
}
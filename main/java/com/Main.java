package com;

import com.costCalculator.MisplacedTilesDistanceCostCalculator;

/**
 * Entrypoint of the program.
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(MisplacedTilesDistanceCostCalculator.calculateMisplacedTiles);
        puzzle.solve();
    }
}

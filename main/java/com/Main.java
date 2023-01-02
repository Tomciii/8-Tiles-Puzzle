package com;

import com.costCalculator.ManhattenDistanceCostCalculator;
import com.costCalculator.MisplacedTilesDistanceCostCalculator;

/**
 * Entrypoint of the program.
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(MisplacedTilesDistanceCostCalculator.calculateCost);
        puzzle.solve();
    }
}

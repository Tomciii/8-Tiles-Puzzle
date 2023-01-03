package com;

import com.costCalculator.ManhattenDistanceCostCalculator;
import com.costCalculator.MisplacedTilesCostCalculator;
import com.puzzle.Puzzle;

/**
 * Entrypoint of the program.
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(MisplacedTilesCostCalculator.calculateCost);
        puzzle.solve();
    }
}

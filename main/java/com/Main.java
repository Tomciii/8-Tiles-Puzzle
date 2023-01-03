package com;

import com.costCalculator.ManhattenDistanceCostCalculator;
import com.puzzle.Puzzle;

/**
 * Entrypoint of the program.
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(ManhattenDistanceCostCalculator.calculateCost);
        puzzle.solve();
    }
}

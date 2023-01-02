package com;

import com.costCalculator.ManhattenDistanceCostCalculator;

/**
 * Entrypoint of the program.
 */
public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(ManhattenDistanceCostCalculator.calculateCost);
        puzzle.solve();
    }
}

package com.costCalculator;

import java.util.function.Function;

/**
 * Calculates the cost with the misplaced tiles method.
 */
public class MisplacedTilesCostCalculator {

    final static private int[][] endPosition = {{0,1,2},{3,4,5},{6,7,8}};

    public static Function<int[][],Integer> calculateCost = (puzzleGrid) -> {
        int result = 0;

        for (int i = 0; i < puzzleGrid.length; i++){
            for (int j = 0; j < puzzleGrid[i].length;j++){
                if (puzzleGrid[i][j] != endPosition[i][j]){
                    result++;
                }
            }
        }

        return result;
    };

}

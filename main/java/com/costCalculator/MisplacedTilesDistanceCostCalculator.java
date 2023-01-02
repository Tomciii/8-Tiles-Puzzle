package com.costCalculator;

import java.util.function.Function;

/**
 * Calculates the cost with the misplaced tiles method.
 */
public class MisplacedTilesDistanceCostCalculator {

    final static private int[][] endPosition = {{0,1,2},{3,4,5},{6,7,8}};

    public static Function<int[][],Integer> calculateCost = (puzzleTile) -> {
        int result = 0;

        for (int i = 0; i < puzzleTile.length; i++){
            for (int j = 0; j < puzzleTile[i].length;j++){
                if (puzzleTile[i][j] != endPosition[i][j]){
                    result++;
                }
            }
        }

        return result;
    };

}

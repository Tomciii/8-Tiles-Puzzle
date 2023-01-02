package com.costCalculator;

import java.util.function.Function;

/**
 * Calculates the cost with the misplaced tiles method.
 */
public class MisplacedTilesDistanceCostCalculator {
    public static Function<int[][],Integer> calculateCost = (puzzleTile) -> {
        int result = 0;

        int index = 0;
        for (int i = 0; i < puzzleTile.length; i++){
            for (int j = 0; j < puzzleTile[i].length;j++){
                if (puzzleTile[i][j] != index){
                    result++;
                }

                index++;
            }
        }

        return result;
    };

}

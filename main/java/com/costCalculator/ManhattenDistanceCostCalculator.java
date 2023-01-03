package com.costCalculator;

import java.util.function.Function;

/**
 * Calculates the cost with the manhatten distance method.
 */
public class ManhattenDistanceCostCalculator {

    public static Function<int[][],Integer> calculateCost = (puzzleGrid) -> {
        int result = 0;

        for (int i = 0; i < puzzleGrid.length; i++){
            for (int j = 0; j < puzzleGrid[0].length; j++){
                int tileNumber = puzzleGrid[i][j];

                if (tileNumber == 0){
                    result += i;
                    result += j;
                    continue;
                }

                if (tileNumber == 1 ||tileNumber == 2){
                    if (i == 1){
                        result += 1;
                    }

                    if (i == 2){
                        result += 2;
                    }
                }

                if (tileNumber == 3 ||tileNumber == 4 || tileNumber == 5){
                    if (i == 0){
                        result += 1;
                    }

                    if (i == 2){
                        result += 1;
                    }
                }

                if (tileNumber == 6 ||tileNumber == 7 || tileNumber == 8){
                    if (i == 0){
                        result += 2;
                    }

                    if (i == 1){
                        result += 1;
                    }
                }

                if (tileNumber == 3 || tileNumber == 6){
                    if (j == 1){
                        result++;
                    }

                    if (j == 2){
                        result += 2;
                    }
                }

                if (tileNumber == 1 || tileNumber == 4 || tileNumber == 7){
                    if (j == 0){
                        result++;
                    }

                    if (j == 2){
                        result += 1;
                    }
                }

                if (tileNumber == 2 || tileNumber == 5 || tileNumber == 8){
                    if (j == 0){
                        result += 2;
                    }

                    if (j == 1){
                        result += 1;
                    }
                }
            }
        }

        return result;
    };
}

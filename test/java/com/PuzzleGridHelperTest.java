package com;

import com.costCalculator.ManhattenDistanceCostCalculator;
import com.puzzle.PuzzleGrid;
import com.puzzle.PuzzleGridHelper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class PuzzleGridHelperTest {

    private PuzzleGridHelper puzzleGridHelper = new PuzzleGridHelper(ManhattenDistanceCostCalculator.calculateCost);

    @Test
    public void testIsContainedInList(){

        List<PuzzleGrid> puzzleGridList = this.getListWithPuzzleGrid();
        PuzzleGrid puzzleGrid = this.getPuzzleGrid();

        Assertions.assertTrue(this.puzzleGridHelper.isContainedInList(puzzleGridList,puzzleGrid.getPuzzleGrid()));
    }

    private List<PuzzleGrid> getListWithPuzzleGrid() {
        List<PuzzleGrid> list = new ArrayList<>();
        int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}};
        PuzzleGrid grid = new PuzzleGrid(puzzle,0,0);
        list.add(grid);
        return list;
    }

    private PuzzleGrid getPuzzleGrid(){
        int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}};
       return new PuzzleGrid(puzzle,0,0);
    }

}
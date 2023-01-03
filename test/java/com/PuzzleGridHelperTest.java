package com;

import com.costCalculator.ManhattenDistanceCostCalculator;
import com.puzzle.PuzzleGrid;
import com.puzzle.PuzzleGridHelper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class PuzzleGridHelperTest {

    private PuzzleGridHelper puzzleTileHelper = new PuzzleGridHelper(ManhattenDistanceCostCalculator.calculateCost);

    @Test
    public void testIsContainedInList(){

        List<PuzzleGrid> puzzleTileList = this.getListWithPuzzleTile();
        PuzzleGrid puzzleTile = this.getPuzzleTile();

        Assertions.assertTrue(this.puzzleTileHelper.isContainedInList(puzzleTileList,puzzleTile.getPuzzleGrid()));
    }

    private List<PuzzleGrid> getListWithPuzzleTile() {
        List<PuzzleGrid> list = new ArrayList<>();
        int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}};
        PuzzleGrid tile = new PuzzleGrid(puzzle,0,0);
        list.add(tile);
        return list;
    }

    private PuzzleGrid getPuzzleTile(){
        int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}};
       return new PuzzleGrid(puzzle,0,0);
    }

}
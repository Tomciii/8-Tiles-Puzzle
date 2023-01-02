package com;

import com.costCalculator.ManhattenDistanceCostCalculator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class PuzzleTileHelperTest {

    private PuzzleTileHelper puzzleTileHelper = new PuzzleTileHelper(ManhattenDistanceCostCalculator.calculateCost);

    @Test
    public void testIsContainedInList(){

        List<PuzzleTile> puzzleTileList = this.getListWithPuzzleTile();
        PuzzleTile puzzleTile = this.getPuzzleTile();

        Assertions.assertTrue(this.puzzleTileHelper.isContainedInList(puzzleTileList,puzzleTile.getPuzzle()));
    }

    private List<PuzzleTile> getListWithPuzzleTile() {
        List<PuzzleTile> list = new ArrayList<>();
        int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}};
        PuzzleTile tile = new PuzzleTile(puzzle,0,0);
        list.add(tile);
        return list;
    }

    private PuzzleTile getPuzzleTile(){
        int[][] puzzle = {{0,1,2},{3,4,5},{6,7,8}};
       return new PuzzleTile(puzzle,0,0);
    }

}
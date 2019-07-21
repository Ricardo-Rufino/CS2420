package Puzzle;

import java.util.ArrayList;
import java.util.List;

public final class Board {

    private final int[][] blocks;
    private final int[][] goal;
    private final int length;

    public Board(int[][] blocks){
        if(blocks.length != blocks[0].length)
            throw new java.lang.IllegalArgumentException("Must be an N by N matrix.");

        this.blocks = blocks;
        this.length = blocks.length;
        this.goal   = goalBoard();
    }

    public int size(){
        return blocks.length;
    }

    public int hamming(){

        int counter  = 0;

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){

                if(blocks[i][j] != goal[i][j] && blocks[i][j] != 0)
                    counter++;

            }
        }

        return counter;
    }

    public int manhattan(){

        int counter = 0;

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){

                if(blocks[i][j] != goal[i][j] && blocks[i][j] != 0){

                    counter += distance(i, j, blocks[i][j]);

                }

            }
        }

        return counter;
    }

    public boolean isGoal(){
        return goalComparison();
    }

    public boolean isSolvable(){

        int inversion = 0;                                              // Variable used to count # of invariants.
        int blankPos  = 0;                                              // Row position of blank element.
        int array[]   = new int[length*length];                         // One dimensional array.

        // Converts the 2d arryay into a 1d array;
        int index = 0;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){

                if(blocks[i][j] == 0)
                    blankPos = i;

                array[index] = blocks[i][j];
                index++;

            }
        }

        // Calculates the total amount of inversions.
        for(int i = 0; i < array.length - 1; i++){
            for(int j = i+1; j < array.length; j++){

                if(array[i] > array[j] && array[i] != 0 && array[j] !=0)
                    inversion++;

            }
        }

        // If the length is odd...
        if(length%2 != 0){
            if(inversion%2 != 0)
                return false;
            else
                return true;
        }
        // If the length is even...
        else{
            if( (inversion + blankPos)%2 == 0 )
                return false;
            else
                return true;
        }
    }

    // Overridden methods ----------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object x){

        if(x == this)
            return true;
        if(x == null)
            return false;
        if(x.getClass() != this.getClass())
            return false;

        Board other = (Board) x;
        int differences     = 0;
        for(int i = 0; i < length; i++){
            for (int j = 0; j < length; j++) {

                if (this.blocks[i][j] != other.blocks[i][j])
                    differences++;
            }
        }

        return (this.length == other.length && differences == 0);
    }

    public Iterable<Board> neighbors(){

        int row = 0;
        int col = 0;

        List<Board> neighborBoards = new ArrayList<>();

        // This nested for-loop searches for the blank element.
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){

                if(blocks[i][j] == 0){
                    row = i;
                    col = j;
                    break;
                }

            }
        }

        // Creates possible boards based upon the current one.
        if(row < (length-1))
            neighborBoards.add((new Board((exchange(blocks, row, col, row+1, col)))));
        if(col < (length-1))
            neighborBoards.add((new Board((exchange(blocks, row, col, row, col+1)))));
        if(row > 0)
            neighborBoards.add((new Board((exchange(blocks, row, col, row-1, col)))));
        if(col > 0)
            neighborBoards.add((new Board((exchange(blocks, row, col, row, col-1)))));

        return neighborBoards;
    }

    @Override
    public String toString(){

        StringBuilder text = new StringBuilder();

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){

                if(j != length - 1)
                    text.append(blocks[i][j] + " ");
                else
                    text.append(blocks[i][j] + "\n");

            }
        }

        return text.toString();
    }

    // This method calculates the Manhattan distance of an element -----------------------------------------------------
    private int distance(int iActual, int jActual, int actualValue){

        int horDiff, verDiff;

        int iIdeal = (actualValue - 1)/length;
        int jIdeal = actualValue - iIdeal*length - 1;

        horDiff = Math.abs(iActual - iIdeal);
        verDiff = Math.abs(jActual - jIdeal);

        return horDiff + verDiff;
    }

    // Creates the goal board of size N x N ----------------------------------------------------------------------------
    private int[][] goalBoard(){

        int goal[][] = new int[length][length];

        for(int i = 0; i < length; i++){                                // This nested for-loop creates the goal board.
            for(int j = 0; j < length; j++){

                goal[i][j] = i*length + j + 1;

            }
        }
        goal[length-1][length-1] = 0;                                   // Sets bottom right block to 0.

        return goal;

    }

    // Compares if board is equal to goal board ------------------------------------------------------------------------
    private boolean goalComparison(){

        int differences     = 0;

        for(int i = 0; i < length; i++){
            for (int j = 0; j < length; j++) {

                if(differences > 0)
                    return false;

                if (goal[i][j] != blocks[i][j])
                    differences++;
            }
        }

        return differences == 0;

    }

    // Exchanges blank element location --------------------------------------------------------------------------------
    private int[][] exchange(int[][] oldBoard, int iOld, int jOld, int iNew, int jNew){

        int duplicate[][] = new int[length][length];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){

                duplicate[i][j] = oldBoard[i][j];
            }
        }

        duplicate[iOld][jOld] = duplicate[iNew][jNew];
        duplicate[iNew][jNew] = 0;

        return duplicate;

    }

    // Unit Testing ----------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        int testBoard[][] = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};          // Solvable matrix
//        int testBoard[][] = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};          // Unsolvable matrix
//        int testBoard[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};          // Random matrix

        Board board  = new Board(testBoard);
        Board board2 = new Board(testBoard);

        System.out.println("Size: " + board.length);
        System.out.println("Hamming: " + board.hamming());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is Goal?: " + board.isGoal());
        System.out.println("Is Solvable?: " + board.isSolvable());
        System.out.println();
        System.out.print(board);
        System.out.println();

        for(Board i : board.neighbors()){
            System.out.println(i);
        }

        System.out.print(board);
        System.out.println(board.equals(board2));

    }
}

package Puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public final class Solver {

    private final Board initial;
    private final Node solution;

    public Solver(Board initial) throws InterruptedException {
        if(!initial.isSolvable())
            throw new java.lang.IllegalArgumentException("The board is not solvable!");
        if(initial == null)
            throw new java.lang.NullPointerException("The board can not be null!");

        // Variables used to solve the board
        Node currentNode;
        MinPQ<Node> pq  = new MinPQ<Node>();

        // Insertion of the iniital board
        this.initial = initial;
        currentNode  = new Node(initial, null);

        if(initial.isGoal()){
            this.solution = currentNode;
            return;
        }
        pq.insert(currentNode);

        while(true){

            for(Board i : currentNode.current.neighbors()){

                if(i.isGoal()){
                    this.solution = new Node(i, currentNode);
                    return;
                }

                if(currentNode.previous == null || !i.equals(currentNode.previous.current))
                    pq.insert(new Node(i, currentNode));

            }

            currentNode = pq.delMin();
        }
    }

    public int moves(){
        return solution.moves;
    }

    public Iterable<Board> solution(){

        Node temp         = solution;
        List<Board> steps = new ArrayList<>();

        while(temp.previous != null){
            steps.add(temp.current);
            temp = temp.previous;
        }
        Collections.reverse(steps);

        return steps;
    }

    // Node class-------------------------------------------------------------------------------------------------------
    public class Node implements Comparable<Node>{

        private final Board current;
        private final Node previous;
        private final int moves;

        public Node(Board current, Node previous){

            if(previous == null)
                this.moves = 0;
            else
                this.moves = previous.moves + 1;

            this.current  = current;
            this.previous = previous;
        }

        @Override
        public int compareTo(Node other){
//            int first  = this.current.hamming()  + this.moves;
//            int second = other.current.hamming() + other.moves;

            // For Manhattan criteria use below code, and comment out the two lines above.
            int first  = this.current.manhattan()  + this.moves;
            int second = other.current.manhattan() + other.moves;

            return first - second;
        }
    }

    // Client test -----------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws InterruptedException {

////        int testBoard[][] = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};          // Solvable matrix
////        int testBoard[][] = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};          // Unsolvable matrix
////        int testBoard[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};          // Random matrix
////        int testBoard[][] = {{1, 0}, {3, 2}};
//        int testBoard[][] = {{7, 8, 5}, {4, 0, 2}, {3, 6, 1}};          // Solvable matrix
//
//
//        Board board    = new Board(testBoard);
//
//        long startTime = System.nanoTime();
//        Solver solver  = new Solver(board);
//        long endTime   = System.nanoTime() - startTime;
//
//        System.out.println("\n" + solver.moves());
//        for(Board i : solver.solution()){
//            System.out.println(i);
//        }
//        System.out.println("Execution time: " + endTime / 1000000 + " ms");

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {

            long startTime = System.nanoTime();
            Solver solver  = new Solver(initial);
            long endTime   = System.nanoTime() - startTime;

            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);

            System.out.println("Execution time: " + endTime / 1000000 / 1000+ " s");
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }

    }

}

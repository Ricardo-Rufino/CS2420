package KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;
import java.util.List;

public class PointST<Value> {
    private RedBlackBST<Point2D, Value> rbst;

    // Construct an empty symbol table of points
    public PointST(){
        rbst = new RedBlackBST<>();
    }

    // Is the symbol table empty?
    public boolean isEmpty(){
        return rbst.isEmpty();
    }

    // Number of points
    public int size(){
        return rbst.size();
    }

    // Associate point p with Value val
    public void put(Point2D p, Value val){
        if(p == null || val == null)
            throw new java.lang.NullPointerException("Input cannot be null.");

        rbst.put(p, val);
    }

    // Value associated with point p
    public Value get(Point2D p){
        if(p == null)
            throw new java.lang.NullPointerException("Input cannot be null.");
        return rbst.get(p);
    }

    // Does the symbol table contain point p?
    public boolean contains(Point2D p){
        if(p == null)
            throw new java.lang.NullPointerException("Input cannot be null.");

        return rbst.contains(p);
    }

    // All points in the symbol table
    public Iterable<Point2D> points(){
        return rbst.keys();
    }

    // All points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if(rect == null)
            throw new java.lang.NullPointerException("Input cannot be null.");

        List<Point2D> list = new ArrayList<>();

        for(Point2D p : points()){
            if(rect.contains(p))
                list.add(p);
        }

        return list;
    }

    // A nearest neighbor to point p; null if the symbol table is empty
    public Point2D nearest(Point2D p){
        if(p == null)
            throw new java.lang.NullPointerException("Input cannot be null.");

        if(isEmpty())
            return null;

        Point2D closestPoint = null;                        // This point will become the smallest.
        double distance      = 999;                         // Arbritary large number.

        for(Point2D i : rbst.keys()){
            double newDistance = i.distanceSquaredTo(p);

            if(newDistance <= distance){
                distance     = newDistance;
                closestPoint = i;
            }
        }

        return closestPoint;
    }

    // Unit testing of the methods (not graded)
    public static void main(String[] args) {
        PointST<Integer> pst = new PointST<>();

        double x = 1;
        double y = 1;
        Point2D point;

        for(int i = 0; i < 10; i++){
            x *= 2;
            y *= 4;

            point = new Point2D(x,y);
            pst.put(point, i);
        }

        System.out.println("\nAll the points in the Red Black BST: ");
        for(Point2D p : pst.points()){
            System.out.println(p);
        }
        System.out.println();

        System.out.println("Is it empty?                      " + pst.isEmpty());
        System.out.println("Size of PointST:                  " + pst.size());
        System.out.println("What is the value of point (2,4): " + pst.get(new Point2D(2, 4)));
        System.out.println("Does it contain point (2,4):      " + pst.contains(new Point2D(2, 4)));
        System.out.println("Closest point to (0,0):           " + pst.nearest(new Point2D(0, 0)));

        System.out.println("\nPoints inside rectangle [0,0],[4,17]: ");
        for(Point2D i : pst.range(new RectHV(0, 0, 4, 17))){
            System.out.println(i);
        }
        System.out.println();
    }
}

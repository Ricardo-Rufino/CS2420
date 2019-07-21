package KdTrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class KdTreeST<Value> {
    private Node root;                  // Root node of kdTree.
    private int counter;                // Variable that keeps count of number of nodes.

    // Initializes an empty node and sets counter to zero.
    public KdTreeST(){

    }

    public boolean isEmpty(){
        if(counter == 0)   return true;
        else               return false;
    }

    public int size(){
        return counter;
    }

    // Associate the value val with point p. ---------------------------------------------------------------------------
    public void put(Point2D point, Value val){
        if(point == null | val == null)
            throw new java.lang.NullPointerException("Inputs cannot be null!");

        root = put(null, root, point, val, true);
    }

    private Node put(Node parent, Node node, Point2D point, Value val, boolean vertical){
        if(node == null){
            counter++;
            return new Node(point, val, rectangle(parent, point, !vertical));
        }

        // Since node is not null, it's now the parent node.
        double cmp = pointComp(node, point, vertical);
        if(point.equals(node.p)){
            node.val = val;
            return node;
        }
        else if(cmp < 0){
            node.left = put(node, node.left, point, val, !vertical);
            return node;
        }
        else{
            node.right = put(node, node.right, point, val, !vertical);
            return node;
        }
    }

    // Function that computes rectangle for a given node.
    private RectHV rectangle(Node parent, Point2D point, boolean vertical){
        if(parent == null)
            return new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
                              Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        double comp = pointComp(parent, point, vertical);
        if(vertical){                   // If the level is odd...
            if(comp < 0)
                return new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
            else
                return new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
        }
        else{                           // If the level is even...
            if(comp < 0)
                return new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
            else
                return new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
        }
    }

    // Function that compares points depending on the level of the node.
    private double pointComp(Node parent, Point2D point, boolean vertical){
        if(vertical)    return (point.x() - parent.p.x());
        else            return (point.y() - parent.p.y());
    }

    // Value associated with point p. ----------------------------------------------------------------------------------
    public Value get(Point2D point){
        if(point == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        return get(point, root, true);
    }

    // Return value associated with the given key, or null if no such key exists.
    private Value get(Point2D point, Node node, boolean vertical){
        if(node == null)                return null;

        double cmp = pointComp(node, point, vertical);
        if     (point.equals(node.p))   return node.val;
        else if(cmp < 0)                return get(point, node.left,  !vertical);
        else                            return get(point, node.right, !vertical);
    }
    //------------------------------------------------------------------------------------------------------------------

    // Does the tree contain contain point p?
    public boolean contains(Point2D p){
        return get(p) != null;
    }

    // Returns all the points in the tree.
    public Iterable<Point2D> points(){
        List<Point2D> list = new ArrayList<>();

        preOrder(root, list);

        return list;
    }

    // Function that returns the points in a pre-order manner.
    private void preOrder(Node node, List<Point2D> list){
        if(node == null)
            return;

        list.add(node.p);

        preOrder(node.left,  list);
        preOrder(node.right, list);
    }

    // Returns all points that are inside the rectangle. ---------------------------------------------------------------
    public Iterable<Point2D> range(RectHV rect){
        List<Point2D> pointsInside = new ArrayList<>();

        range(root, rect, pointsInside);

        return pointsInside;
    }

    // All the points that are inside the rectangle.
    private void range(Node node, RectHV rect, List<Point2D> pointsInside){
        if(node == null)
            return;

        if(!node.rect.intersects(rect))
            return;
        if(rect.contains(node.p))
            pointsInside.add(node.p);

        range(node.left,  rect, pointsInside);
        range(node.right, rect, pointsInside);
    }

    // A nearest neighbor to point p; null if the tree is empty. -------------------------------------------------------
    public Point2D nearest(Point2D queryPoint){
        if(root == null)
            return null;

        return nearest(root, queryPoint, root.p);

    }

    private Point2D nearest(Node node, Point2D queryPoint, Point2D nearestPoint){
        if(node == null)
            return nearestPoint;

        // Distance from query point to current closest point.
        double closestDistance = nearestPoint.distanceSquaredTo(queryPoint);

        // If the distance from node's rectangle to queryPoint is larger than nearestPoint; do not pursuit.
        if(node.rect.distanceSquaredTo(queryPoint) > closestDistance)
            return nearestPoint;

        // If the node's point is closer to the queryPoint; this point becomes the nearestPoint.
        if(node.p.distanceSquaredTo(queryPoint) < closestDistance)
            nearestPoint = node.p;

        if(node.left != null && node.left.rect.contains(queryPoint)) {
            nearestPoint = nearest(node.left,  queryPoint, nearestPoint);
            nearestPoint = nearest(node.right, queryPoint, nearestPoint);
        }
        else{
            nearestPoint = nearest(node.right, queryPoint, nearestPoint);
            nearestPoint = nearest(node.left,  queryPoint, nearestPoint);
        }

        return nearestPoint;
    }

    // Data structure with node implementation--------------------------------------------------------------------------
    public class Node{
        private Point2D p;              // Point of node.
        private Value   val;            // Value used in symbol table.
        private RectHV  rect;           // Rectangle of node.
        private Node    left, right;    // Left and right node of node.

        public Node(Point2D p, Value val, RectHV rect){
            this.p        = p;
            this.val      = val;
            this.rect     = rect;
        }

        @Override
        public String toString() {
            return String.format("Point: (%.2f, %.2f); Rectangle: {[%.2f, %.2f], [%.2f, %.2f]}",
                                 this.p.x(), this.p.y(),
                                 this.rect.xmin(), this.rect.ymin(), this.rect.xmax(), this.rect.ymax());
        }
    }

    // Unit testing-----------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
//        KdTreeST<Integer> kdt = new KdTreeST<>();
//
//        kdt.put(new Point2D(2, 3), 1);
//        kdt.put(new Point2D(1, 5), 2);
//        kdt.put(new Point2D(4, 2), 3);
//        kdt.put(new Point2D(4, 5), 4);
//        kdt.put(new Point2D(4, 4), 5);
//        kdt.put(new Point2D(3, 3), 6);
//
//        System.out.println(kdt.root);
//        System.out.println(kdt.root.left);
//        System.out.println(kdt.root.right);
//        System.out.println(kdt.root.right.right);
//        System.out.println(kdt.root.right.right.right);
//        System.out.println(kdt.root.right.right.left);
//        System.out.println("Size of tree: " + kdt.counter);
//
//        System.out.println("\nSymbol Table values of the following points");
//        System.out.println("(2, 3): " + kdt.get(new Point2D(2,3)));
//        System.out.println("(1, 5): " + kdt.get(new Point2D(1,5)));
//        System.out.println("(4, 2): " + kdt.get(new Point2D(4,2)));
//        System.out.println("(4, 5): " + kdt.get(new Point2D(4,5)));
//        System.out.println("(4, 4): " + kdt.get(new Point2D(4,4)));
//        System.out.println("(3, 3): " + kdt.get(new Point2D(3,3)));
//        System.out.println("(7, 7): " + kdt.get(new Point2D(7,7)));
//
//        System.out.println("\nDoes the tree contain point (7, 7)? " + kdt.contains(new Point2D(7, 7)));
//        System.out.println("Does the tree contain point (3, 3)? " + kdt.contains(new Point2D(3, 3)));
//
//        System.out.println("\nList of all points in the tree: ");
//        for(Point2D p : kdt.points()){
//            System.out.println(p);
//        }
//
//        System.out.println("\nThese points are inside rectangle [1.5, 2.5], [3.5, 3.5]: ");
//        for(Point2D p : kdt.range(new RectHV(1.5, 2.5, 3.5, 3.5))){
//            System.out.println(p);
//        }
//
//        System.out.println("\nNearest point to (4.5, 1.5): ");
//        System.out.println(kdt.nearest(new Point2D(4.5, 1.5)));

        KdTreeST<Integer> kdt = new KdTreeST<>();
        PointST<Integer>  pts = new PointST<>();

        String file1 = "src/KdTrees/input100K.txt";
        String file2 = "src/KdTrees/input1M.txt";

        // Reading in the file
        In in = new In(file1);
        for (int j = 0; !in.isEmpty(); j++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdt.put(p, j);
            pts.put(p, j);
        }

        // Performance Comparison
        long   startTime;
        long   endTime;
        double i;
        double n1 = 1000;               // Number of samples for 100k node test.
        double n2 = 1000;               // Number of samples for 1M node test.

        // Performance comparison with 100,000 nodes. ------------------------------------------------------------------
        System.out.println("Performance test with 100,000 nodes.");
        // Performance of Brute-Force
        startTime = System.nanoTime();
        for(i = 0; i < n1; i++){
            double x      = StdRandom.uniform();
            double y      = StdRandom.uniform();
            Point2D point = new Point2D(x, y);

            pts.nearest(point);
        }
        endTime = System.nanoTime() - startTime;
        endTime = endTime/1000000;
        System.out.println("\tBrute-Force: " + i/endTime);

        // Performance of KdTree
        startTime = System.nanoTime();
        for(i = 0; i < n1; i++){
            double x      = StdRandom.uniform();
            double y      = StdRandom.uniform();
            Point2D point = new Point2D(x, y);

            kdt.nearest(point);
        }
        endTime = System.nanoTime() - startTime;
        endTime = endTime/1000000;
        System.out.println("\tKdTree: " + i/endTime);
        System.out.println();

        // Comparison with 1M nodes. -----------------------------------------------------------------------------------
        kdt = new KdTreeST<>();
        pts = new PointST<>();

        // Reading in the file
        in = new In(file2);
        for (int j = 0; !in.isEmpty(); j++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdt.put(p, j);
            pts.put(p, j);
        }

        // Performance Comparison
        System.out.println("Performance test with 1,000,000 nodes.");

        // Performance of Brute-Force
        startTime = System.nanoTime();
        for(i = 0; i < n2; i++){
            double x      = StdRandom.uniform();
            double y      = StdRandom.uniform();
            Point2D point = new Point2D(x, y);

            pts.nearest(point);
        }
        endTime = System.nanoTime() - startTime;
        endTime = endTime/1000000;
        System.out.println("\tBrute-Force: " + i/endTime);

        // Performance of KdTree
        startTime = System.nanoTime();
        for(i = 0; i < n2; i++){
            double x      = StdRandom.uniform();
            double y      = StdRandom.uniform();
            Point2D point = new Point2D(x, y);

            kdt.nearest(point);
        }
        endTime = System.nanoTime() - startTime;
        endTime = endTime/1000000;
        System.out.println("\tKdTree: " + i/endTime);
    }
}

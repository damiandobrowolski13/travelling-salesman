/**
 * Lab 6: Travelling Salesperson Linked List Implementation
 * @author DAMIAN DOBROWOLSKI
 * @date 11.02.2023
 * */

import edu.princeton.cs.algs4.*;

    /*
      Tour()                      // create an empty tour
      void show()                 // print the tour to standard output
      void draw()                 // draw the tour to standard draw
      int size()                  // number of points on tour
      double distance()           // return the total distance of the tour
      void insertNearest(Point p) // insert p using nearest neighbor heuristic
      void insertSmallest(Point p)// insert p using smallest increase heuristic

      Compile: javac -cp .:algs4.jar NearestInsertion.java 
      Run:     java -cp .:algs4.jar NearestInsertion < tsp1000.txt 

    */


public class Tour {
    // inner class for one node

  private class Node {

    private Point p;
    private Node next;

    private Node() {
      this(null,null);
    }

    private Node(Point thepoint, Node thenext) {
      p = thepoint;
      next = thenext;
    }
  }

  private Node path; // head of path
  private int len;

  public Tour() {
    path = new Node();
    len = 0;
  }

/**
 * @return boolean checking if at end of linked list
 * */
  public boolean isEmpty(){
    return len == 0;
  }

/**
 * @return number of elements in linked list
 * */
  public int size() {
    return len;
  }

/**
 * @return total distance of tour
 * loop through tour. use distanceTo method of Point class to find distance between each point and summate
 * */
  public double distance() {
    Node current = path;
    double dist = 0.0;
    while (current.next != null){
      dist += current.p.distanceTo(current.next.p);
      current = current.next;
    }
    return dist;
  }

/**
 * prints tour points to StdOut
 * */
  public void show() {
    Node current = path;
    while (current != null){
      System.out.println(current.p);
      current = current.next;
    }
  }

/**
 * draws tour points with draw() and connects them with drawTo() methods of Point class to StdDraw
 * */
  public void draw() {
    Node current = path;
    while (current != null){
      current.p.draw();
      if (current.next != null){
        current.p.drawTo(current.next.p);
      }
      current = current.next;
    }
  }

/**
 * @param Point to be inserted into tour
 * prepends point to linked list
 * */
  public void insert(Point p){
    if (isEmpty()){
      path = new Node(p,null);
    } else{
      Node oldPath = path;
      path = new Node(p,oldPath);
    } len++;
  }

/**
 * @param Point to be inserted using nearest neighbor heuristic
 * loops through list, checking distance between current point and each other point to find closest one, then adds it to tour
 * */
  public void insertNearest(Point p) {
    if (isEmpty()){
      path = new Node(p,null);
    } else{
      Node current = path;
      double leastDist = 1000000;
      Node nearest = new Node();
      while (current != null){
        if (current.p.distanceTo(p) < leastDist){
          leastDist = current.p.distanceTo(p);
          nearest = current;
        }
        current = current.next;
      }
      Node addedNode = new Node (p,nearest.next);
      nearest.next = addedNode;
    } len++;
  }
  
/**
 * @param Point to be inserted using smallest increase heuristic
 * loops through list, checking potential displacement of distance if added to find point adding smallest increase to distance, then adds it to tour
 * */
  public void insertSmallest(Point p) {
    if (isEmpty()){
      path = new Node(p,null);
    } else{
      Node current = path;
      double leastIncr = 1000000;
      Node smallest = new Node();
      while (current.next != null){
        if (current.p.distanceTo(p) + p.distanceTo(current.next.p) - current.p.distanceTo(current.next.p) < leastIncr){
          leastIncr = current.p.distanceTo(p) + p.distanceTo(current.next.p) - current.p.distanceTo(current.next.p);
          smallest = current;
        }
        current = current.next;
      }
       if (len == 1){
        insert(p);
      }
      Node addedNode = new Node (p,smallest.next);
      smallest.next = addedNode;
    } len++;
  }

} // end Tour

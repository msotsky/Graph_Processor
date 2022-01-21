// Class with code to read in a directed graph given as an edge list
// and then build an adjacency matrix and/or adjacency list.
//
// Only minor changes required if graph is undirected.
//
// Format of input file:
// - first line contains n (# nodes) and m (# edges)
// - next m lines each contain two integers x y, indicating
//   that there is a directed edge from x to y
//
// Nodes are indexed 1..n for input files under consideration
// (could just as easily be 0..(n-1) (most natural)), so when
// reading in the edge list in main(), subtract 1 from each
// node label to convert to 0-based indexing.

import java.io.*;
import java.util.*;

public class GraphProcessor {
    //---------------------------------------------------------------
    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("in5e.txt");   // hardcoded input file
        Scanner sc = new Scanner(inFile);

        // Read in first line of file
        int numNodes = sc.nextInt();
        int numEdges = sc.nextInt();

        // Read in and store the edge list
        int[][] edgeList = new int[numEdges][2];
        for (int j = 0; j < numEdges; j++) {
            // edge from x to y (x --> y)
            int x = sc.nextInt() - 1;   // convert to 0-based indexing
            int y = sc.nextInt() - 1;   // convert to 0-based indexing
            edgeList[j][0] = x;
            edgeList[j][1] = y;
        }
        sc.close();

        /*
        int[][] adjMatrix = buildAdjMatrix(numNodes, numEdges, edgeList);

        // TESTING -- print adjacency matrix
        System.out.println();
        for (int x = 0; x < numNodes; x++) {
            System.out.println(Arrays.toString(adjMatrix[x]));
        }
        System.out.println();
        */

        int[][] adjList = buildAdjList(numNodes, numEdges, edgeList);

        // TESTING -- print adjacency list structure
        System.out.println();
        for (int x = 0; x < numNodes; x++) {
            System.out.println(Arrays.toString(adjList[x]));
        }
        System.out.println();
    } // main(String[])
    //---------------------------------------------------------------
    public static boolean[][] buildAdjMatrix(int numNodes, int numEdges, int[][] edgeList) {
        boolean[][] adjMatrix = new boolean[numNodes][numNodes];
        for (int j = 0; j < numEdges; j++) {
            int x = edgeList[j][0];
            int y = edgeList[j][1];
            adjMatrix[x][y] = true;
        }

        return adjMatrix;
    } // buildAdjMatrix(int,int,int[][])
    //---------------------------------------------------------------
    public static int[][] buildAdjList(int numNodes, int numEdges, int[][] edgeList) {
        int[] outDegree = new int[numNodes];
        for (int j = 0; j < edgeList.length; j++) {
            int x = edgeList[j][0];
            int y = edgeList[j][1];
            outDegree[x]++;
        }

        int[][] adjList = new int[numNodes][];
        for (int x = 0; x < numNodes; x++) {
            adjList[x] = new int[outDegree[x]];
        }

        // Need to scan through edgeList a second time to get data
        // that we will put into adjList
        // - specifically, for any node x, a list of all of its out-neighbours
        int[] index = new int[numNodes];
        for (int j = 0; j < edgeList.length; j++) {
            int x = edgeList[j][0];
            int y = edgeList[j][1];
            adjList[x][index[x]] = y;
            index[x]++;
        }

        return adjList;
    } // buildAdjList(int,int,int[][])
    //---------------------------------------------------------------
} // class GraphProcessor

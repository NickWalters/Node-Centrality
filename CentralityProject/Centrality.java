import java.util.*;

/**
 * This is the big boy class. all the centrality work is calculated here
 * But! dont make it too big! you can use helper classes too if nessessary!
 *
 * @author Nicholas Walters
 * @version 9 May 2018 (last change by Nick)
 */
public class Centrality
{
    // instance variables - replace the example below with your own
    ArrayList<Integer> array;
    
    public Centrality(FileData info)
    {
        //array = info.readFile(1);
    }
    
    
    /**
     * returns the degree centrality of a given graph
     * @param matrixOfGraph, the matrix representation of graph/text file
     * @param allNodesUnique, a list containing info of all distinct nodes
     */
    public void getDegreeCentrality(ArrayList<String> nodes){
        HashMap<Integer, Integer> nodesIndex = new HashMap<>();
        HashMap<Integer, Integer> indexNodes = new HashMap<>();
        for(String line: nodes){
            // get two nodes per line, put in array
            String[] bothNodes = line.split(" ");
            // if hashmap contains these nodes already, then increment its edgeValue
            // if not in hashmap, then add Node and its edge count is 1
            if(!nodesIndex.containsKey(bothNodes[0])){
                nodesIndex.put(Integer.parseInt(bothNodes[0]), 1);
            }
            else{
                int numEdges = nodesIndex.get(bothNodes[0]);
                nodesIndex.put(Integer.parseInt(bothNodes[0]), numEdges++);
            }
            
            if(!nodesIndex.containsKey(bothNodes[1])){
                nodesIndex.put(Integer.parseInt(bothNodes[1]), 1);
            }
            else{
                int numEdges = nodesIndex.get(bothNodes[1]);
                nodesIndex.put(Integer.parseInt(bothNodes[1]), numEdges++);
            }
        }
        //print all the nodes, with their associated values
        System.out.println(nodesIndex.entrySet());
    }

    
    //adj is edgeMatrix
    public int[] getClosenessCentrality(int[][] adj)
    {
        int size = adj.length * adj.length;
        int[] closeness = new int[size];
        for (int vertex = 0; vertex < adj.length; vertex++) {
            boolean[] visited = new boolean[size];
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(size);
            
            pq.add(vertex);
            while (!pq.isEmpty()) {
                int u = pq.remove();
                if(!visited[u]) {
                    visited[u] = true;
                    for (int i = 0; i < size; i++) {
                        //Priority Queue speeds up extract-min
                        if (!visited[i]) {
                            if (adj[u][i] > 0) {
                                pq.add(adj[u][i]+1);
                            } 
    			}
    		    }
    		    closeness[vertex] += 1;
    		}
    	    }
        }	
        return closeness;
    }
    
    /**
     * Brandes algorithm is the most efficient algorithm for betweeness Centrality
     * this runs in O(nm) time, compared to all other algorithms which require O(n^3) time for unweighted graphs
     */
    public float[] getBetweenessCentrality(int[][] edgeMatrix){
       int numNodes = edgeMatrix.length;
       float[] betweenessArray = new float[numNodes];
        // Brandes algorithm O(EV^2) for UNWEIGHTED graphs: 
        // Brandes algorithm : https://people.csail.mit.edu/jshun/6886-s18/papers/BrandesBC.pdf
        
        // assign the shortest paths list to use later on. Corresponds to P on paper
        ArrayList paths[] = new ArrayList[numNodes];
        // create a sigma list according to paper (σ)
        float sigma[] = new float[numNodes];
        // create a delta list according to paper (δ)
        float[] delta = new float[numNodes];
        // holds the distance for each iteration of the paths
        int distances[];
        // assign an empty queue
        Queue<Integer> queue;
        
        // for each s in V
        // beginning with the starting node, for all Vertex V which is an element of the graph G do:
        for(int startingNode = 0; startingNode < numNodes; startingNode++)
        {
            Stack<Integer> stack = new Stack<Integer>();
            distances = new int[numNodes];
            for(int i = 0; i<numNodes; i++)
            {
                paths[i] = new ArrayList(); // create an array inside of an array, to store the different sequences shortest paths
                sigma[i] = 0;
                distances[i] = -1;
            }
            sigma[startingNode] = 1;
            distances[startingNode] = 0;
            
            queue = new ArrayDeque<Integer>();
            queue.add(startingNode);
            int v = 0; // current Node/Vertex
            // while Q not empty do:
            while(!queue.isEmpty())
            {
                // dequeue v from Q and push to S
                v = queue.remove();
                stack.push(v);
                // for each neighbour w of v/currentVertex do:
                for(int currentNeighbor = 0; currentNeighbor< numNodes; currentNeighbor++)
                {
                    if(edgeMatrix[v][currentNeighbor] == 1)
                    {
                        if(distances[v]<0)
                        {
                            queue.add(currentNeighbor);
                            distances[currentNeighbor] = distances[v]+1;
                        }
                        if(distances[currentNeighbor] == distances[v]+1)
                        {
                            sigma[currentNeighbor] += sigma[v];
                            paths[currentNeighbor].add(v);
                        }
                    }
                }
            }
            
            for(int i = 0; i< numNodes; i++)
            {
                //δ[v] = 0, for all vertex thats an element of Graph
                delta[i] = 0;
            }
            
            //While Stack is not empty do:
            while(!stack.isEmpty())
            {
                // pop one by one
                v = stack.pop();
                // for each vertex in P/Paths, delta[w] = delta[w] + (sigma[w] / sigma[v]) * (1+ delta[v])
                java.util.Iterator<Integer> pathIterator = paths[v].iterator();
                int w; // w is the neighbour
                while(pathIterator.hasNext())
                {
                    w = pathIterator.next();
                    delta[w] = delta[w] + ((sigma[w])/ (sigma[v]))*(1+ delta[v]);
                }
                if(v != startingNode){
                    betweenessArray[v] += delta[v];
                }
            }
        }
        return betweenessArray;
    }
    
    
    public Node[] getKatzCentrality(){
        return null;
    }
}

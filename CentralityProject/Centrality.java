import java.awt.geom.GeneralPath;
import java.util.*;
import java.util.Map.Entry;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TopLevelAttribute;

import sun.java2d.pipe.AlphaColorPipe;
import sun.security.krb5.internal.crypto.dk.AesDkCrypto;
import sun.security.provider.certpath.AdjacencyList;

/**
 * This is the big boy class. all the centrality work is calculated here
 * But! dont make it too big! you can use helper classes too if nessessary!
 *
 * @author Nicholas Walters and James Caldon 22226341
 * @version 9 May 2018 (last change by Nick)
 */
public class Centrality
{
    // instance variables - replace the example below with your own
    ArrayList<Integer> array;
    private int numVertices;
    private int[][] edgeMatrix;

    private HashMap<Integer, Integer> degreeCentralities;
    private float[] katzCentralities;

    
    

    
    public Centrality(Graph data)
    {
        //array = info.readFile(1);
        
        //ArrayList<int[]> n = data.getEdges();
        //degreeCentralities = getDegreeCentrality(n);
       // numVertices = getNumVertices();
        //betweenessCentralities = new float[numVertices];
        //weightsOfShortestPaths = new int[numVertices][numVertices];
        //katzCentralities = new float[numVertices];
    }
    
    // EDIT, changed to use an arraylist filled with arrays of ints (no parsing needed)- James
    /**
     * @author Nick
     * returns the degree centrality of a given graph
     * @param matrixOfGraph, the matrix representation of graph/text file
     * @param allNodesUnique, a list containing info of all distinct nodes
     */
    public ArrayList<ArrayList<Integer>> getDegreeCentrality(Graph g){
    	/*
        HashMap<Integer, Integer> nodesIndex = new HashMap<>();
        //indexNodes not used
        HashMap<Integer, Integer> indexNodes = new HashMap<>();
        for(int[] bothNodes: nodes){
            // if hashmap contains these nodes already, then increment its edgeValue
            // if not in hashmap, then add Node and its edge count is 1
            if(!nodesIndex.containsKey(bothNodes[0])){
                nodesIndex.put(bothNodes[0], 1);
            }
            else{
                int numEdges = nodesIndex.get(bothNodes[0]);
                nodesIndex.put(bothNodes[0], numEdges++);
            }
            
            if(!nodesIndex.containsKey(bothNodes[1])){
                nodesIndex.put(bothNodes[1], 1);
            }
            else{
                int numEdges = nodesIndex.get(bothNodes[1]);
                nodesIndex.put(bothNodes[1], numEdges++);
            }
        }
        //print all the nodes, with their associated values
        numVertices = nodesIndex.size();
        return nodesIndex;
        */
    	
    	ArrayList<ArrayList<Integer>> ordered = new ArrayList<ArrayList<Integer>>();
    	
    	for (int cN = 0; cN < g.getNumberComponents(); cN++) {
    		ordered.add(new ArrayList<>());
	    	ArrayList<HashSet<Integer>> adjList = g.getAdjList(cN);
	    	PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
	    	for (int i = 0; i < adjList.size(); i++) {
				pq.add(new Node(i, (float) adjList.get(i).size()));
			}
	    	int max = 5;
	    	if (pq.size() < 5) {
	    		max = pq.size();
	    	}
	    	for (int i = 0; i < max; i++) {
				ordered.get(cN).add(g.getVertex(pq.poll().v, cN)); 
			}
    	}
    	return ordered;
    }
    
	/**
	 * @author James
	 * holds reference to the vertex and its weight
	 */
    
	public class Node {
		public final Integer v;
		public final Float w;
		Node (Integer vertex, Float weight) {
			v = vertex;
			w = weight;
		}
	}
	
	/**
	 * @author James
	 * Comparator for two nodes, used by the priority queue
	 * the highest priority is the lower value
	 */
	public class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node o1, Node o2) {
			if (o1.w < o2.w) return 1;
			else if (o1.w > o2.w) return -1;
			return 0;
		}	
		
	}
	/*
    adj is edgeMatrix rip effieciency
    public int[] getClosenessCentrality(Graph g) {
    	int[][] adj = g.getAdjMatrix();
    	int size = g.getNumberOfVertices();
    	System.out.println(g);
    	int[] closeness = new int[size];
    	//for (int vertex = 0; vertex < adj.length; vertex++) {
    	int vertex = 0;
    		boolean[] visited = new boolean[size];
    		PriorityQueue<Node> pq = new PriorityQueue<Node>(size, new NodeComparator());
    		
    		
    		pq.add(new Node(vertex, 0));
    		
    		
    		while (!pq.isEmpty()) {
    			Node u = pq.remove();
    			if(!visited[u.v]) {
    				visited[u.v] = true;
    				for (int i = 0; i < size; i++) {
    					//Priority Queue speeds up extract-min
    					if (!visited[i]) {
    						if (adj[u.v][i] > 0) {
    							pq.add(new Node(i, adj[u.v][i]+u.w));
    						} 

    					}
    				}
    				closeness[vertex] = u.w;
    			}
    			
    		}
		//}
		for (int i = 0; i < closeness.length; i++) {
			System.out.print(closeness[i] + ", ");
			
		}
        return closeness;
    }
    */
    
	/**Uses adjacency list instead for that extra effieciency. Complexity O((|E| + |V|) * |V|).
	 * 
	 * @param g
	 * @return
	 */
    public ArrayList<ArrayList<Integer>> getClosenessCentrality(Graph g) {
    	ArrayList<ArrayList<Integer>> ordered = new ArrayList<ArrayList<Integer>>();
    	
    	for (int cN = 0; cN < g.getNumberComponents(); cN++) {
    		ordered.add(new ArrayList<>());
	    	ArrayList<HashSet<Integer>> adj = g.getAdjList(cN);
	    	int size = adj.size();
	    	float[] closeness = new float[size];
	    	PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
	    	for (int vertex = 0; vertex < size; vertex++) {
	    			
		    	int[] distance = new int[size];
		    	for (int i = 0; i < size; i++) {
					distance[i] = -1;
				}
		    	distance[vertex] = 0;
		    	Queue<Integer> q = new LinkedList<Integer>();
		
		    	q.add(vertex);
		    	
		    	while (!q.isEmpty()) {
		    		
		    		int v = q.poll();
		    		HashSet<Integer> adjacent = adj.get(v);
		    		for (Integer i : adjacent) {
		    			if(distance[i] == -1) {
							distance[i] = distance[v] + 1;
							q.add(i);
		    			}
					}
		    		closeness[vertex] += distance[v];
		    	
		    	}
		    	
		    	pq.add(new Node(vertex, 1/closeness[vertex]));
		    	closeness[vertex] = 1/closeness[vertex]; 
	    	}
	    	int max = 5;
	    	if (pq.size() < 5) {
	    		max = pq.size();
	    	}
	    	for (int i = 0; i < max; i++) {
				ordered.get(cN).add(g.getVertex(pq.poll().v, cN)); 
			}
	    	
    	}
        return ordered;
    }
    
    /**
     * @author Nick
     * Brandes algorithm is the most efficient algorithm for betweeness Centrality
     * this runs in O(nm) time, compared to all other algorithms which require O(n^3) time for unweighted graphs
     */
    public ArrayList<ArrayList<Integer>> getBetweenessCentrality(Graph g){
    	ArrayList<ArrayList<Integer>> ordered = new ArrayList<ArrayList<Integer>>();
		for (int cN = 0; cN < g.getNumberComponents(); cN++) {
		    float[] betweenessCentralities = new float[g.getNumberOfVertices(cN)];
		    int[][] weightsOfShortestPaths = new int[g.getNumberOfVertices(cN)][];
    		ordered.add(new ArrayList<>());
    		PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
	    	int[][] edgeMatrix = g.getAdjMatrix(cN);
	    	ArrayList<HashSet<Integer>> adjList = g.getAdjList(cN);
		    int numNodes = edgeMatrix.length;

	        // Brandes algorithm O(EV^2) for UNWEIGHTED graphs: 
	        // Brandes algorithm : https://people.csail.mit.edu/jshun/6886-s18/papers/BrandesBC.pdf
	        
	        java.util.Stack<Integer> stack;
	        // assign the shortest paths list to use later on. Corresponds to P on paper
	        //ArrayList paths[] = new ArrayList[numNodes];

	        // create a sigma list according to paper (Ã�Æ’)
	        float sigma[] = new float[numNodes];
	        // create a delta list according to paper (ÃŽÂ´)
	        float[] delta = new float[numNodes];
	        // holds the distance for each iteration of the paths
	        int distances[];
	        // assign an empty queue
	        Queue<Integer> queue;
	        
	        // for each s in V
	        // beginning with the starting node, for all Vertex V which is an element of the graph G do:
	        for(int startingNode = 0; startingNode < numNodes; startingNode++)
	        {
		        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>(numNodes);
	            stack = new java.util.Stack<Integer>();
	            distances = new int[numNodes];
	            for(int i = 0; i<numNodes; i++)
	            {
	            	paths.add(new ArrayList<Integer>()); // create an array inside of an array, to store the different sequences shortest paths
	                sigma[i] = 0;
	                distances[i] = -1;
	            }
	            sigma[startingNode] = 1;
	            distances[startingNode] = 0;
	            
	            queue = new ArrayDeque<Integer>();
	            queue.add(startingNode);
	            int v; // current Node/Vertex
	            // while Q not empty do:
	            while(!queue.isEmpty())
	            {
	                // dequeue v from Q and push to S
	                v = queue.remove();
	                stack.push(v);
	                HashSet<Integer> adjacent = adjList.get(v);
	                // for each neighbour w of v/currentVertex do:
	                for (Integer currentNeighbor : adjacent) {
	                    if(distances[currentNeighbor] < 0)
	                    {
	                        queue.add(currentNeighbor);
	                        distances[currentNeighbor] = distances[v]+1;
	                    }
	                    if(distances[currentNeighbor] == distances[v]+1)
	                    {
	                        sigma[currentNeighbor] += sigma[v];
	                        paths.get(currentNeighbor).add(v);
	                    }
	                    weightsOfShortestPaths[v] = distances;
	                }
	            }

	            for(int i = 0; i< numNodes; i++)
	            {
	                //ÃŽÂ´[v] = 0, for all vertex thats an element of Graph
	                delta[i] = 0;
	            }
	            
	            //While Stack is not empty do:
	            while(!stack.isEmpty())
	            {
	                // pop one by one
	                v = stack.pop();
	                // for each vertex in P/Paths, delta[w] = delta[w] + (sigma[w] / sigma[v]) * (1+ delta[v])
	                java.util.Iterator<Integer> pathIterator = paths.get(v).iterator();
	                int w; // w is the neighbour
	                while(pathIterator.hasNext())
	                {
	                    w = pathIterator.next();
	                    delta[w] += (float)((float)(sigma[w])/ (float)(sigma[v]))*(float)(1+ delta[v]);
	                }
	                if(v != startingNode){
	                    betweenessCentralities[v] += (float)delta[v];

	                }
	                
	            }


	            
	        }
            for (int i = 0; i < betweenessCentralities.length; i++) {
                pq.add(new Node(i, betweenessCentralities[i]));
			}
	        int max = 5;
	    	if (pq.size() < 5) {
	    		max = pq.size();
	    	}
	    	for (int i = 0; i < max; i++) {
				ordered.get(cN).add(g.getVertex(pq.poll().v, cN)); 
			}
		}
        return ordered;
    }
    
    
    
	public ArrayList<ArrayList<Integer>> getKatzCentrality(Graph g){
		ArrayList<ArrayList<Integer>> ordered = new ArrayList<ArrayList<Integer>>();
		for (int cN = 0; cN < g.getNumberComponents(); cN++) {
		    float[] betweenessCentralities = new float[g.getNumberOfVertices(cN)];
		    int[][] weightsOfShortestPaths = new int[g.getNumberOfVertices(cN)][];
		    ArrayList<HashSet<Integer>> adjList = g.getAdjList(cN);
    		ordered.add(new ArrayList<>());
    		PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
	    	int[][] edgeMatrix = g.getAdjMatrix(cN);
		    int numNodes = edgeMatrix.length;
		    float[] catz = new float[g.getNumberOfVertices(cN)];
	        // Brandes algorithm O(EV^2) for UNWEIGHTED graphs: 
	        // Brandes algorithm : https://people.csail.mit.edu/jshun/6886-s18/papers/BrandesBC.pdf
	        
	        java.util.Stack<Integer> stack;
	        // assign the shortest paths list to use later on. Corresponds to P on paper
	        ArrayList paths[] = new ArrayList[numNodes];
	        // create a sigma list according to paper (Ã�Æ’)
	        float sigma[] = new float[numNodes];
	        // create a delta list according to paper (ÃŽÂ´)
	        float[] delta = new float[numNodes];
	        // holds the distance for each iteration of the paths
	        int distances[];
	        // assign an empty queue
	        Queue<Integer> queue;
	        
	        // for each s in V
	        // beginning with the starting node, for all Vertex V which is an element of the graph G do:
	        for(int startingNode = 0; startingNode < numNodes; startingNode++)
	        {
	            stack = new java.util.Stack<Integer>();
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
	            int v; // current Node/Vertex
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
	                        if(distances[currentNeighbor]<0)
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
	                    weightsOfShortestPaths[v] = distances;
	                }
	            }

	            for(int i = 0; i< numNodes; i++)
	            {
	                //ÃŽÂ´[v] = 0, for all vertex thats an element of Graph
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
	                    delta[w] += (float)((float)(sigma[w])/ (float)(sigma[v]))*(float)(1+ delta[v]);
	                }
	                if(v != startingNode){
	                    betweenessCentralities[v] += (float)delta[v];

	                }
	                
	            }


	            
	        }
	        double alpha = 0.5;		
	        for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					catz[i] = catz[i] + (float) (adjList.get(j).size()*Math.pow(alpha,weightsOfShortestPaths[i][j]));
				}
				pq.add(new Node(i, catz[i]));
			}

	    	int max = 5;
	    	if (pq.size() < 5) {
	    		max = pq.size();
	    	}
	    	for (int i = 0; i < max; i++) {
				ordered.get(cN).add(g.getVertex(pq.poll().v, cN)); 
			}
		}
        return ordered;
    	
    }
    
    
    public int getNumVertices(){
        return numVertices;
    }
    

    public float[] returnKatzCentrality(){
        return katzCentralities;
    }
}


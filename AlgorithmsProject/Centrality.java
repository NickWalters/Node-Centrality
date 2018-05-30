import java.util.*;
/**
 * This is the big boy class. all the centrality work is calculated here
 * But! dont make it too big! you can use helper classes too if nessessary!
 *
 * @author Nicholas Walters and James Caldon 22226341
 * @version 9 May 2018 (last change by Nick)
 */
public class Centrality
{

    private ArrayList<ArrayList<Integer>> degreeCentralities = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> katzCentralities = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> closenessCentralities = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> betweennessCentralities = new ArrayList<>();
    

    
    public Centrality(Graph g)
    {
    	calculateCentralities(g);
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
    
    
    /**
     * @author Nick and James
     * @param g
     */
    private void calculateCentralities(Graph g) {
	    int numNodes = g.getNumberOfVertices();
        float catz[] = new float[numNodes];
        float alpha = 0.5f;	
	    float[] betweenessCentralities = new float[numNodes];
	    int[][] weightsOfShortestPaths = new int[numNodes][];
		ArrayList<PriorityQueue<Node>> pqList = new ArrayList<PriorityQueue<Node>>();
		ArrayList<HashSet<Integer>> adjList = g.getAdjList();

        // Brandes algorithm O(EV^2) for UNWEIGHTED graphs: 
        // Brandes algorithm : https://people.csail.mit.edu/jshun/6886-s18/papers/BrandesBC.pdf
        
        Stack<Integer> stack;

        // create a sigma list according to paper (Ã�Æ’)
        float sigma[] = new float[numNodes];
        // create a delta list according to paper (ÃŽÂ´)
        float delta[] = new float[numNodes];
        // holds the distance for each iteration of the paths
        int distances[];
        // assign an empty queue
        Queue<Integer> queue;
        boolean visited[] = new boolean[numNodes];
        int component[] = new int[numNodes];
        // for each s in V
        // beginning with the starting node, for all Vertex V which is an element of the graph G do:
        visited[0] = true; 
	    float[] closeness = new float[g.getNumberOfVertices()];
	    pqList.add(new PriorityQueue<>(new NodeComparator()));
	    
	    int cN = 1;
        for(int startingNode = 0; startingNode < numNodes; startingNode++)
        {

            // assign the shortest paths list to use later on. Corresponds to P on paper
            ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
            stack = new Stack<Integer>();
            distances = new int[numNodes];
            if (!visited[startingNode]) {
            	for(int i = 0; i < numNodes; i++) {
            		if (!visited[i]) {
            			component[i] = cN;
            		}
            	}
            	pqList.add(new PriorityQueue<>(new NodeComparator()));
            	cN++;
            }
            for(int i = 0; i<numNodes; i++)
            {
                paths.add(new ArrayList<Integer>()); // create an array inside of an array, to store the different sequences shortest paths
                sigma[i] = 0;
                distances[i] = -1;
                //ÃŽÂ´[v] = 0, for all vertex thats an element of Graph
                delta[i] = 0f;
            }
            sigma[startingNode] = 1;
            distances[startingNode] = 0;
            
            queue = new ArrayDeque<Integer>();
            queue.add(startingNode);
            visited[startingNode] = true;
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
                        visited[currentNeighbor] = true;
                        distances[currentNeighbor] = distances[v]+1;
                    }
                    if(distances[currentNeighbor] == distances[v]+1)
                    {
                        sigma[currentNeighbor] += sigma[v];
                        paths.get(currentNeighbor).add(v);
                    }
                    weightsOfShortestPaths[v] = distances; 
                    
                }
                closeness[startingNode] += distances[v];
            }

            
            for(int i = 0; i< numNodes; i++)
            {


                if (visited[i]) {
                	catz[startingNode] = catz[startingNode] + (float) (adjList.get(i).size()*Math.pow(alpha,weightsOfShortestPaths[startingNode][i]));
                }

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
                    delta[w] += ((sigma[w])/(sigma[v]))*(1f + delta[v]);
                }
                if(v != startingNode){
                    betweenessCentralities[v] += delta[v];

                }
                
            }

            closeness[startingNode] = 1/closeness[startingNode];
      
        }
        
    	for (int i = 0; i < adjList.size(); i++) {
			pqList.get(component[i]).add(new Node(i, (float) adjList.get(i).size()));
		}
    	
    	findTopFive(pqList, degreeCentralities, g);
        
        for (int k = 0; k < numNodes; k++) {
            pqList.get(component[k]).add(new Node(k, closeness[k]));
		}

        findTopFive(pqList, closenessCentralities, g);
        
        for (int k = 0; k < numNodes; k++) {
            pqList.get(component[k]).add(new Node(k, betweenessCentralities[k]));

		}
        
        findTopFive(pqList, betweennessCentralities, g);


        
	
        for (int i = 0; i < numNodes; i++) {
			pqList.get(component[i]).add(new Node(i, catz[i]));
		}
        
        findTopFive(pqList, katzCentralities, g);
    }
    
    private void findTopFive(ArrayList<PriorityQueue<Node>> pqList, ArrayList<ArrayList<Integer>> aL, Graph g) {
        for (int i = 0; i < pqList.size(); i++) {
        	int count = 0;
        	aL.add(new ArrayList<>());
			while (!pqList.get(i).isEmpty() && count < 5) {
				aL.get(i).add(g.getVertex(pqList.get(i).poll().v));
				count++;
			}
            pqList.get(i).clear();
		}
    }
    
    public ArrayList<ArrayList<Integer>> getKatzCentrality(){
        return katzCentralities;
    }
    public ArrayList<ArrayList<Integer>> getClosenessCentrality(){
        return closenessCentralities;
    }
    public ArrayList<ArrayList<Integer>> getBetweenessCentrality(){
        return betweennessCentralities;
    }
    public ArrayList<ArrayList<Integer>> getDegreeCentrality(){
        return degreeCentralities;
    }
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.StyledEditorKit.ForegroundAction;


/**
 * 
 */

/**
 * @author James
 *
 */
public class Graph {

	private int[][] adjMatrix;
	private ArrayList<int[][]> adjMatrixList = new ArrayList<int[][]>();
	private ArrayList<ArrayList<HashSet<Integer>>> adjListList = new ArrayList<ArrayList<HashSet<Integer>>>();
	private ArrayList<int[]> edges = new ArrayList<>();
	private String filename;
	private ArrayList<HashSet<Integer>> adjList = new ArrayList<>();
	private Hashtable<Integer,Integer> vertices = new Hashtable<Integer, Integer>();
	private Hashtable<Integer,Integer> verticesIndex = new Hashtable<Integer, Integer>();
	private ArrayList<Hashtable<Integer, Integer>> verticesIndexList = new ArrayList<Hashtable<Integer, Integer>>();
	private ArrayList<HashSet<Integer>> verticesList = new ArrayList<>();
	private int numberComponents = 1;
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	public Graph(String filename) throws FileNotFoundException {
		this.filename = filename;
		readEdgeList(filename);
		
		generateAdjMatrix();
		findComponents();
		generateAdjMatrixForComponents();
	}
	
	private void readEdgeList(String filename) throws FileNotFoundException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = reader.readLine()) != null) {
            	String[] l = line.split(" ");
            	int[] array = new int[2];
            	array[0] = Integer.parseInt(l[0]);
            	array[1] = Integer.parseInt(l[1]);
                edges.add(array);
            }
            reader.close();
        }
        catch(Exception e){ 
            System.out.println("--Error, Cant read file-- " + e.getMessage());
            throw new FileNotFoundException("Not found");
        }
	}
	private void findComponents() {

		int vertex = 0;
		int size = vertices.size();
		boolean foundAllComponents;
		numberComponents = 0;
		int[] visited = new int[size];
		do {
			verticesList.add(new HashSet<Integer>());
			verticesList.get(numberComponents).add(vertex);
			foundAllComponents = true;
	    	visited[vertex] = 1;
	    	Queue<Integer> q = new LinkedList<Integer>();
	
	    	q.add(vertex);
	    	
	    	while (!q.isEmpty()) {
	    		int v = q.poll();
	    		HashSet<Integer> adjacent = adjList.get(v);
	    		for (Integer i : adjacent) {
	    			if(visited[i] == 0) {
						visited[i] = 1;
						verticesList.get(numberComponents).add(i);
						q.add(i);
	    			}
				}
	    	}
		    for (int i = 0; i < visited.length; i++) {
				if (visited[i] == 0) {
					foundAllComponents = false;
					vertex = i;
					break; 
				}
			}
			numberComponents++;
		} while (!foundAllComponents);
	}
	public int getNumberOfVertices() {
		return adjMatrix.length;
	}
	
	public int getNumberOfVertices(int componentNumber) {
		return adjListList.get(componentNumber).size();
	}
	
	private void generateAdjMatrix() {
		// Logic replicated from https://www.reddit.com/r/learnprogramming/comments/4ktlwx/making_an_adjacency_matrix_from_an_edge_list_text/
    	int count = 0;
    	for (int[] line: edges) {
    		int[] vertex = line;
    		if (!verticesIndex.containsKey(vertex[0])) {
    			verticesIndex.put(vertex[0], count);
    			this.vertices.put(count, vertex[0]);
    			adjList.add(count, new HashSet<>());
    			count++;
    		}
    		if (!verticesIndex.containsKey(vertex[1])) {
    			verticesIndex.put(vertex[1], count);
    			this.vertices.put(count, vertex[1]);
    			adjList.add(count, new HashSet<>());
    			count++;
    		}
		}
    	adjMatrix = new int[verticesIndex.size()][verticesIndex.size()];
    	for (int i = 0; i < edges.size(); i++) {
    		
    		int node1 = verticesIndex.get(edges.get(i)[0]);
    		int node2 = verticesIndex.get(edges.get(i)[1]);
			adjMatrix[node1][node2] = 1;
			adjMatrix[node2][node1] = 1;
			HashSet<Integer> connect1 = adjList.get(node1);
			HashSet<Integer> connect2 = adjList.get(node2);
			if (!connect1.contains(node2)) {
    			adjList.get(node1).add(node2);
			}
			if (!connect2.contains(node1)) {
    			adjList.get(node2).add(node1);
			}
		}

    }
	
	private void generateAdjMatrixForComponents() {
		ArrayList<Hashtable<Integer,Integer>> vertices = new ArrayList<Hashtable<Integer,Integer>>();
		for (int i = 0; i < verticesList.size(); i++) {
			vertices.add(new Hashtable<Integer, Integer>());
			adjListList.add(new ArrayList<>());
			verticesIndexList.add(new Hashtable<Integer, Integer>());
		}
    	int counts[] = new int[verticesList.size()];
    	for (int[] line: edges) {
    		int[] vertex = line;
    		
    		for (int i = 0; i < verticesList.size(); i++) {
	    		if (!vertices.get(i).containsKey(vertex[0]) && verticesList.get(i).contains(verticesIndex.get(vertex[0]))) {
	    			
	    			vertices.get(i).put(vertex[0], counts[i]);
	    			adjListList.get(i).add(counts[i], new HashSet<>());
	    			verticesIndexList.get(i).put(counts[i], vertex[0]);
	    			counts[i]++;

	    		}
	    		if (!vertices.get(i).containsKey(vertex[1]) && verticesList.get(i).contains(verticesIndex.get(vertex[1]))) {
	    			
	    			vertices.get(i).put(vertex[1], counts[i]);
	    			adjListList.get(i).add(counts[i], new HashSet<>());
	    			verticesIndexList.get(i).put(counts[i], vertex[1]);
	    			counts[i]++;
	    			
	    		}
    		}
		}
    	for (int cN = 0; cN < verticesList.size(); cN++) {
	    	adjMatrixList.add(new int[verticesList.get(cN).size()][verticesList.get(cN).size()]);
	    	for (int i = 0; i < edges.size(); i++) {
	    		if (vertices.get(cN).containsKey(edges.get(i)[0])) {
		    		int node1 = vertices.get(cN).get(edges.get(i)[0]);
		    		int node2 = vertices.get(cN).get(edges.get(i)[1]);
					adjMatrixList.get(cN)[node1][node2] = 1;
					adjMatrixList.get(cN)[node2][node1] = 1;
					HashSet<Integer> connect1 = adjListList.get(cN).get(node1);
					HashSet<Integer> connect2 = adjListList.get(cN).get(node2);
					if (!connect1.contains(node2)) {
		    			adjListList.get(cN).get(node1).add(node2);
					}
					if (!connect2.contains(node1)) {
		    			adjListList.get(cN).get(node2).add(node1);
					}
	    		}
			}
    	}
    }
	
	@Override
	public String toString() {
		String str = "";
		for (int[] is : adjMatrix) {
			for (int i : is) {
				str += i + "    ";
			}
			str += "\n\n";
		}
		return str;
	}

	
    public int[][] getAdjMatrix(int componentNumber){
    	if (numberComponents == 1) {
	    	int[][] adjMatrixClone = new int[adjMatrix.length][];
	    	for (int i = 0; i < adjMatrix.length; i++) {
				adjMatrixClone[i] = adjMatrix[i].clone(); 
			}
	        return adjMatrixClone;
    	} else {
    		int[][] adjMatrixClone = new int[adjMatrixList.get(componentNumber).length][];
	    	for (int i = 0; i < adjMatrixList.get(componentNumber).length; i++) {
				adjMatrixClone[i] = adjMatrixList.get(componentNumber)[i].clone(); 
			}
	        return adjMatrixClone;
    	}

    }
    
    
    public int getVertex(int index, int componentNumber) {
        return verticesIndexList.get(componentNumber).get(index);
    }
    
    public ArrayList<HashSet<Integer>> getAdjList(int componentNumber){
    	if (numberComponents == 1) {
	    	ArrayList<HashSet<Integer>> adjListClone = new ArrayList<>();
	    	for (int i = 0; i < adjList.size(); i++) {
				adjListClone.add(new HashSet<Integer>(adjList.get(i)));
			}
	        return adjListClone;
    	} else {
	    	ArrayList<HashSet<Integer>> adjListClone = new ArrayList<>();
	    	for (int i = 0; i < adjListList.get(componentNumber).size(); i++) {
				adjListClone.add(new HashSet<Integer>(adjListList.get(componentNumber).get(i)));
			}
	        return adjListClone;
    	}
    }
    //For Testing
    public ArrayList<int[]> getEdges() {

    	return edges;
    }
    
    public int getNumberComponents() {
        return numberComponents;
    }
    
    public String getFilname(){
        return filename;
    }
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;



/**
 * @author James
 *
 */
public class Graph {

	private ArrayList<int[]> edges = new ArrayList<>();
	private ArrayList<HashSet<Integer>> adjList = new ArrayList<>();
	private Hashtable<Integer,Integer> vertices = new Hashtable<>();
	private Hashtable<Integer,Integer> verticesIndex = new Hashtable<>();
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	public Graph(String filename) throws FileNotFoundException {
		readEdgeList(filename);
		
		generateAdjList();
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

	public int getNumberOfVertices() {
		return vertices.size();
	}

	
	private void generateAdjList() {
		
    	int count = 0;
    	for (int[] line: edges) {
    		int[] vertex = line;
    		if (!verticesIndex.containsKey(vertex[0])) {
    			verticesIndex.put(vertex[0], count);
    			vertices.put(count, vertex[0]);
    			adjList.add(count, new HashSet<>());
    			count++;
    		}
    		if (!verticesIndex.containsKey(vertex[1])) {
    			verticesIndex.put(vertex[1], count);
    			vertices.put(count, vertex[1]);
    			adjList.add(count, new HashSet<>());
    			count++;
    		}
		}
    	for (int i = 0; i < edges.size(); i++) {
    		
    		int node1 = verticesIndex.get(edges.get(i)[0]);
    		int node2 = verticesIndex.get(edges.get(i)[1]);
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
	


	

    
    
    public int getVertex(int index) {
        return vertices.get(index);
    }
    
    public ArrayList<HashSet<Integer>> getAdjList(){
    	ArrayList<HashSet<Integer>> adjListClone = new ArrayList<>();
    	for (int i = 0; i < adjList.size(); i++) {
			adjListClone.add(new HashSet<Integer>(adjList.get(i)));
		}
        return adjListClone;
    }
}

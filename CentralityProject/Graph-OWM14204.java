import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * 
 */

/**
 * @author James
 *
 */
public class Graph {

	private int[][] adjMatrix;
	private ArrayList<int[]> edges;
	/**
	 * 
	 */
	public Graph() {
		
		adjMatrix = generateAdjMatrix();
	}
	private readEdgeList() {
		
		
		
	}
	private int[][] generateAdjMatrix() {
    	int[][] adj;
    	Map<Integer,Integer> vertices = new Hashtable<Integer, Integer>();
    	Map<Integer,Integer> vertices2 = new Hashtable<Integer, Integer>();
    	int count = 0;
    	for (int[] line: nodes) {
    		int[] vertex = line;
    		if (!vertices.containsKey(vertex[0])) {
    			vertices.put(vertex[0], count);
    			vertices2.put(count, vertex[0]);
    			count++;
    		}
    		if (!vertices.containsKey(vertex[1])) {
    			vertices.put(vertex[1], count);
    			vertices2.put(count, vertex[1]);
    			count++;
    		}
		}
    	System.out.println(vertices.toString());
    	adj = new int[vertices.size()][vertices.size()];
    	for (int i = 0; i < nodes.size(); i++) {
    		int node1 = vertices.get(nodes.get(i)[0]);
    		int node2 = vertices.get(nodes.get(i)[1]);
				adj[node1][node2] = 1;
				adj[node2][node1] = 1;
    		
		}
    	// TEMPORARRY PRINT METHOD FROM CITS2200 GRAPH Class
        StringBuffer var1 = new StringBuffer(adj.length + "\n");

        for(int var2 = 0; var2 < adj.length; ++var2) {
            for(int var3 = 0; var3 < adj.length; ++var3) {
                var1.append(adj[var2][var3]);
                var1.append("\t");
            }

            var1.append("\n");
        }
        System.out.println(var1.toString());

		return adj;
    	
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

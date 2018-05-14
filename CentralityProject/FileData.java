
import java.util.*;
import java.util.Map.Entry;

import sun.security.provider.certpath.Vertex;

import java.io.*;
/**
 * This class reads the text files containing the nodes, and prints output
 *
 * @author (Nicholas Walters and James Caldon 22226341)
 * @version 9 May 2018
 */
public class FileData
{    
    private String filename;
    private ArrayList<int[]> nodes;
    private int[][] ajMatrix;
    /**
     * initialise data to work with
     * use this data for algorithms
     */
    public FileData(int whichFile) throws Exception
    {
    	
        nodes = readFile(whichFile);
        ajMatrix = generateAdjMatrix();
    }
    /**
     * input 1, to read 42833.edges.txt
     * input 2, to read 78813.edges.txt
     * Doing this allows distinction between which file you are using
     */
    private ArrayList<int[]> readFile(int whichFile) throws Exception{
        try{
            if(whichFile == 1){
                System.out.println(System.getProperty("user.dir"));
                filename = "428333.edges.txt";
            }  else
            if(whichFile == 2){
                filename = "78813.edges.txt";
            } else
            if(whichFile == 3) {
            	filename = "test.txt";
            } else {
            	filename = "428333.edges.txt";
            }
            
            return readFileHelper(filename);
        }
        catch(Exception e){
            System.out.println("--ERROR--, please select from the two files 1 or 2");
            throw new Exception("cannot find" + e.getMessage());
        }
    }
    
    
    private ArrayList<int[]> readFileHelper(String filename) throws Exception{

        ArrayList<int[]> nodes = new ArrayList<int[]>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = reader.readLine()) != null) {
            	String[] l = line.split(" ");
            	int[] array = new int[2];
            	array[0] = Integer.parseInt(l[0]);
            	array[1] = Integer.parseInt(l[1]);
                nodes.add(array);
            }
            reader.close();
        }
        catch(Exception e){
            System.out.println("--Error, Cant read file-- " + e.getMessage());
            throw new FileNotFoundException("Not found");
        }

        return nodes;
    }
    
    /**
	 * @author James
	 * holds reference to the vertex and its weight
	 */
	public class Node {
		public final Integer v;
		public final Integer w;
		Node (Integer vertex, Integer weight) {
			v = vertex;
			w = weight;
		}
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
    
    public String getFileName() {
        return filename;
    }
   
    public int[][] getAdjMatrix(){
        return ajMatrix;
    }
    
    /*
    public ArrayList<Integer> getUniqueNodes(){
        return nodeNumbers;
    }
    */
    public ArrayList<int[]> getNodes() {
    	System.out.print("[");
    	for (int[] list: nodes) {
    		System.out.print("[" + list[0] + "," + list[1] + "],");
    	}
    	System.out.println("]");
    	return nodes;
    }
}
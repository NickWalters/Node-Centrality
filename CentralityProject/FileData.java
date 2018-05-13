
import java.util.*;


import java.io.*;
/**
 * This class reads the text files containing the nodes, and prints output
 *
 * @author (Nicholas Walters)
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
                return readFileHelper(filename);
            }   
            if(whichFile == 2){
                filename = "78813.edges.txt";
                return readFileHelper(filename);
            }
        }
        catch(Exception e){
            System.out.println("--ERROR--, please select from the two files 1 or 2");
            throw new Exception("cannot find" + e.getMessage());
        }
        return null;
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
    
    // not finished
    private int[][] generateAjMatrix(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner fileReader = new Scanner(file);
        fileReader.useDelimiter(" |\\n");
        int numVertex = 0;
        int[][] edgeMatrix = new int[numVertex][numVertex];
        while(fileReader.hasNext()){
            int x = Integer.parseInt(fileReader.next());
            int y = Integer.parseInt(fileReader.next());
        }
        
        return null;
    }
    
    
    private int[][] generateAdjMatrix() {
    	int[][] adj;
    	Set<Integer> vertices = new HashSet<Integer>();
    	for (int[] line: nodes) {
    		int[] vertex = line;
    		if (!vertices.contains(vertex[0])) {
    			vertices.add(vertex[0]);
    		}
    		if (!vertices.contains(vertex[1])) {
    			vertices.add(vertex[1]);
    		}

		}
    	adj = new int[vertices.size()][vertices.size()];
		return adj;
    	
    }
    public String getFileName() {
        return filename;
    }
   
    public int[][] getAjMatrix(){

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

import java.util.*;

import org.omg.PortableInterceptor.USER_EXCEPTION;

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
    private ArrayList<String> nodes;
    private int[][] ajMatrix;
    /**
     * initialise data to work with
     * use this data for algorithms
     */
    public FileData(int whichFile) throws Exception
    {
    	
        ArrayList<String> nodes = readFile(whichFile);
        //ajMatrix = generateAjMatrix(filename);
    }
    /**
     * input 1, to read 42833.edges.txt
     * input 2, to read 78813.edges.txt
     * Doing this allows distinction between which file you are using
     */
    private ArrayList<String> readFile(int whichFile) throws Exception{
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
    
    
    private ArrayList<String> readFileHelper(String filename) throws Exception{

        ArrayList<String> nodes = new ArrayList<String>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = reader.readLine()) != null) {
                nodes.add(line);
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
    public ArrayList<String> getNodes() {
    	return nodes;
    }
}
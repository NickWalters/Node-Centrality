import java.util.*;
import CITS2200.*;
import java.io.*;
import java.nio.*;
/**
 * This class reads the text files containing the nodes, and prints output
 *
 * @author (Nicholas Walters)
 * @version 9 May 2018
 */
public class FileData
{
    // instance variables - replace the example below with your own
    private String filename;
    private int numNodes;
    
    private HashMap<Integer, Integer> nodeNumHash;
    
    /**
     * initialise data to work with
     * use this data for algorithms
     */
    public FileData()
    {
          nodeNumbers = new ArrayList<Integer>();
          nodeNumHash = new HashMap<Integer, Integer>();
          readFile(1);
          numNodes = nodeNumbers.size();
    }
    
    /**
     * input 1, to read 42833.edges.txt
     * input 2, to read 78813.edges.txt
     * Doing this allows distinction between which file you are using
     */
    public ArrayList<String> readFile(int whichFile){
        if(whichFile == 1){
            String filename = "428333.edges.txt";
            readFileHelper(filename);
        }
        else if(whichFile == 2){
            String filename = "78813.edges.txt";
            readFileHelper(filename);
        }
        else{
            System.out.println("--ERROR--, please select from the two files");   
        }
    }
    
    private ArrayList<String> readFileHelper(String filename){
        ArrayList<String> nodeNumbers = new Arra
        int i = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String lines;
            int firstVertex;
            int secondVertex;
            String[] twoNodes;
            
            while((lines = reader.readLine()) != null){
                twoNodes = lines.split(" ");
                //convert string representation to Integer
                firstVertex = Integer.parseInt(twoNodes[0]);
                secondVertex = Integer.parseInt(twoNodes[0]);
                if(!nodeNumbers.contains(firstVertex)){
                    nodeNumbers.add(firstVertex);
                    nodeNumHash.put(firstVertex, i);
                    i++;
                }
                if(!nodeNumbers.contains(secondVertex)){
                    nodeNumbers.add(secondVertex);
                    nodeNumHash.put(secondVertex, i);
                    i++;
                }
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("--Error, Cant read file-- " + e.getMessage());
        }
          
    }
    
    /**
     * must call method readFile first!
     * this method prints out all the UNIQUE/DISTINCT nodes provided from the txt file
     */
    public void printNodes(){
        String str = " | ";
        for(int node: nodeNumbers){
            str += node + " | ";
        }
        System.out.println(str);
    }
    
    public int[] getAjMatrix(){
        return null;
    }
    
    public String getFileName(){
        return filename;
    }
    
    public ArrayList<Integer> getUniqueNodes(){
        return nodeNumbers;
    }
}


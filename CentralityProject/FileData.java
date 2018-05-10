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
<<<<<<< HEAD
    private int numNodes;
    
    private HashMap<Integer, Integer> nodeNumHash;
    
=======
>>>>>>> f9c7fce5bf9a2131cb505fd08c8af422b5ba98ac
    /**
     * initialise data to work with
     * use this data for algorithms
     */
    public FileData() 
    {

    }
    /**
     * input 1, to read 42833.edges.txt
     * input 2, to read 78813.edges.txt
     * Doing this allows distinction between which file you are using
     */
    public ArrayList<String> readFile(int whichFile){
        if(whichFile == 1){
            filename = "428333.edges.txt";
            return readFileHelper(filename);
        }
        else if(whichFile == 2){
            filename = "78813.edges.txt";
            return readFileHelper(filename);
        }
        else{
<<<<<<< HEAD
            System.out.println("--ERROR--, please select from the two files");   
=======
            System.out.println("--ERROR--, please select from the two files");
            return null;
>>>>>>> f9c7fce5bf9a2131cb505fd08c8af422b5ba98ac
        }
    }
    
    private ArrayList<String> readFileHelper(String filename){
<<<<<<< HEAD
        ArrayList<String> nodeNumbers = new Arra
        int i = 0;
=======

        ArrayList<String> nodes = new ArrayList<String>();
>>>>>>> f9c7fce5bf9a2131cb505fd08c8af422b5ba98ac
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while((line = reader.readLine()) != null) {
                nodes.add(line);
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("--Error, Cant read file-- " + e.getMessage());
        }
        return nodes;
    }
    
    public String getFileName() {
        return filename;
    }
    /*
    public int[][] getAjMatrix(){

        return null;
    }
    */
    /*
    public ArrayList<Integer> getUniqueNodes(){
        return nodeNumbers;
    }
    */
}


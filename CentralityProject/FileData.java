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
            System.out.println("--ERROR--, please select from the two files");
            return null;
        }
    }
    
    private ArrayList<String> readFileHelper(String filename){

        ArrayList<String> nodes = new ArrayList<String>();
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


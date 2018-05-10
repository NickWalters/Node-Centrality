import java.util.*;
/**
 * This is the big boy class. all the centrality work is calculated here
 * But! dont make it too big! you can use helper classes too if nessessary!
 *
 * @author Nicholas Walters
 * @version 9 May 2018 (last change by Nick)
 */
public class Centrality
{
    // instance variables - replace the example below with your own
    private int x;
    Node node;
    
    public Centrality(FileData graphInfo)
    {
        
    }
    
    
    /**
     * returns the degree centrality of a given graph
     * @param matrixOfGraph, the matrix representation of graph/text file
     * @param allNodesUnique, a list containing info of all distinct nodes
     */
    public Node[] getDegreeCentrality(int[] matrixOfGraph, ArrayList<Integer> allNodesUnique){
        return null;
    }
    
    
    public Node[] getClosenessCentrality(){
        return null;
    }
    
    
    public Node[] getBetweenessCentrality(){
        return null;
    }
    
    
    public Node[] getKatzCentrality(){
        return null;
    }
}

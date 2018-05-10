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
    ArrayList<Integer> array;
    
    public Centrality(FileData info)
    {
        //array = info.readFile(1);
    }
    
    
    /**
     * returns the degree centrality of a given graph
     * @param matrixOfGraph, the matrix representation of graph/text file
     * @param allNodesUnique, a list containing info of all distinct nodes
     */
    public void getDegreeCentrality(ArrayList<String> nodes){
        HashMap<Integer, Integer> nodesMap = new HashMap<>();
        
        for(String line: nodes){
            // get two nodes per line, put in array
            String[] bothNodes = line.split(" ");
            // if hashmap contains these nodes already, then increment its edgeValue
            // if not in hashmap, then add Node and its edge count is 1
            if(!nodesMap.containsKey(bothNodes[0])){
                nodesMap.put(Integer.parseInt(bothNodes[0]), 1);
            }
            else{
                int numEdges = nodesMap.get(bothNodes[0]);
                nodesMap.put(Integer.parseInt(bothNodes[0]), numEdges++);
            }
            
            if(!nodesMap.containsKey(bothNodes[1])){
                nodesMap.put(Integer.parseInt(bothNodes[1]), 1);
            }
            else{
                int numEdges = nodesMap.get(bothNodes[1]);
                nodesMap.put(Integer.parseInt(bothNodes[1]), numEdges++);
            }
        }
        //print all the nodes, with their associated values
        System.out.println(nodesMap.entrySet());
    }
    
    
    public Node[] getClosenessCentrality(){
        return null;
    }
    
    
    public Node[] getBetweenessCentrality(int[][] ajMatrix, int numNodes){
        //analyse the adjacency matrix of edges
        //each row of the ajacency matrix gives enough information for that nodes specific BC
        // do this for every row, and sum up all the BC's
        for(int i=0; i<numNodes; i++){
            for(int j=0; i<numNodes; i++){
                
            }
        }
        return null;
    }
    
    
    public Node[] getKatzCentrality(){
        return null;
    }
}

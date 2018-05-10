 import java.util.*;
/**
 * The main method which prints final outputs and ties together all classes
 *
 * @author Nicholas Walters
 * @version 9 May 2018
 */
public class main
{
    public main()
    {
        // read the file data for 428333.edges.txt
        FileData data428333 = new FileData();
        data428333.readFile(1);
        int[] matrix = data428333.getAjMatrix();
        ArrayList<Integer> nodesUnique = data428333.getUniqueNodes();
        // run the 4 centrality measures, also time performance
        long time = System.currentTimeMillis(); //Start timer
        Centrality graph438333 = new Centrality(data428333);
        Node[] degree42833 = graph438333.getDegreeCentrality(matrix,nodesUnique);
        System.out.println("Degree centrality for: " + data428333.getFileName());
        
        for(int i=0; i<degree42833.length; i++){
            System.out.println();
        }
        long endTime = System.currentTimeMillis();
        long totalTimeTaken = endTime - time;
        System.out.println("Run time: totalTimeTaken
        
        
        // read the file data for 78813.edges.txt
        FileData data78813 = new FileData();
        data78813.readFile(2);
        data78813.getAjMatrix();
    }
}

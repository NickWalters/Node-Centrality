 


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
        data428333.getAjMatrix();
        // run the 4 centrality measures, also time performance
        long time = System.currentTimeMillis(); //Start timer
        Node[] topFiveDegree428333;
        
        
        
        // read the file data for 78813.edges.txt
        FileData data78813 = new FileData();
        data78813.readFile(2);
        data78813.getAjMatrix();
    }
}

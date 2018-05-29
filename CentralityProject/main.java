 import java.util.*;

import com.sun.javafx.collections.FloatArraySyncer;
/**
 * The main method which prints final outputs and ties together all classes
 *
 * @author Nicholas Walters and James Caldon 2226341
 * @version 9 May 2018
 */
public class main
{
    public static void main(String[] args)
    {
    	try {
            Graph2 testGraph = new Graph2("78813.edges.txt");

            CentralityEffieciencyImp c4 = new CentralityEffieciencyImp(testGraph);

            Graph testGraph1 = new Graph("78813.edges.txt");
            Centrality c3 = new Centrality(testGraph1);
            
            
            
            
            System.out.println("Degree");
            System.out.println(c3.getDegreeCentrality(testGraph1));
            System.out.println(c4.returnDegreeCentrality());
            System.out.println("Closeness");
            System.out.println(c3.getClosenessCentrality(testGraph1));
            System.out.println(c4.returnClosenessCentrality());
            System.out.println("Betweeness");
            System.out.println(c3.getBetweenessCentrality(testGraph1));
            System.out.println(c4.returnBetweenessCentrality());
            System.out.println("Katz Which one is right?");
            System.out.println(c3.getKatzCentrality(testGraph1));
            System.out.println(c4.returnKatzCentrality());
		} catch (Exception e) {
			System.out.println(e);
		}

        
    }
    

}

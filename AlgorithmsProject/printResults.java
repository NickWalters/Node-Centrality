import java.util.*;
import java.text.*;
/**
 * The main method which prints final outputs and ties together all classes
 *
 * @author Nicholas Walters 22243339 and James Caldon 2226341
 * @version 9 May 2018
 */
public class printResults
{
    public static void main(String[] args)
    {
        Runtime runtime = Runtime.getRuntime();
        long startTime = System.currentTimeMillis();
        try {
            
            
            
            System.out.println("----CITS2200 Centrality Analyser, by Nick Walters (22243339) and James Caldon (22226341)----");
            Graph testGraph = new Graph("78813.edges.txt");
            System.out.println();
            Graph testGraphTwo = new Graph("428333.edges.txt");
            
            long startTime78813 = System.currentTimeMillis();
            Centrality c4 = new Centrality(testGraph);
            long endTime78813 = System.currentTimeMillis();
            long totalTime78813 = endTime78813 - startTime78813;
            
            long startTime42833 = System.currentTimeMillis();
            Centrality c5 = new Centrality(testGraphTwo);
            long endTime42833 = System.currentTimeMillis();
            long totalTime42833 = endTime42833 - startTime42833;
            
            
            // This commented out is the inefficient way
            //Graph2 testGraph1 = new Graph2("78813.edges.txt");
            //CentralityEffieciencyImp c3 = new CentralityEffieciencyImp(testGraph1);
            
            System.out.println("428333.edges");
            System.out.println("(" + totalTime42833 + "ms)");
            System.out.println("---------------------");
            
            
            
            System.out.println("Degree");
            System.out.println(c5.getDegreeCentrality());
            
            
            System.out.println("Closeness");
            System.out.println(c5.getClosenessCentrality());
            
            
            
            System.out.println("Betweeness");
            System.out.println(c5.getBetweenessCentrality());
            
            
            System.out.println("Katz");
            System.out.println(c5.getKatzCentrality());
            
            
            System.out.println();
            System.out.println();
            System.out.println();
            
            System.out.println("78813.edges");
            System.out.println("(" + totalTime78813 + "ms)");
            System.out.println("---------------------");
            
            
            System.out.println("Degree");
            System.out.println(c4.getDegreeCentrality());
            
            
            System.out.println("Closeness");
            System.out.println(c4.getClosenessCentrality());
            
            
            System.out.println("Betweeness");
            System.out.println(c4.getBetweenessCentrality());
            
            
            System.out.println("Katz");
            System.out.println(c4.getKatzCentrality());
            
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }

        runtime.gc();
            long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
            long stopTime = System.currentTimeMillis();
            long totalTime = stopTime - startTime;
            NumberFormat formatter = new DecimalFormat("#0.00000");
            System.out.println();
            System.out.println();
            System.out.print("Execution time is " + formatter.format((totalTime) / 1000d) + " seconds");
            System.out.println();
            long kb = memoryUsed / 1024;
            long mb = kb / 1024;
            System.out.println("Memory Used: " + memoryUsed + " bytes " + "(~" + mb + "mb" + ")");
            
    }
    

}

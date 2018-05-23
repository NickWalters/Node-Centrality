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
    public static void main(String[] args) throws Exception
    {
        // read the file data for 428333.edges.txt
        //FileData data428333 = new FileData(1);
        //Centrality c4 = new Centrality(data428333);
        //c4.getDegreeCentrality(data428333.getNodes());
        //c4.getClosenessCentrality(data428333.getAdjMatrix());
    	
    	/*
        Graph testGraph = new Graph("test.txt");
        FileData data2 = new FileData(3);
        Centrality c4 = new Centrality(data2);
        Integer closeness = c4.getClosenessCentrality(testGraph);
        System.out.println("---Closeness Centralities----");
        System.out.println(closeness);
        System.out.println("------------------------------");
        
        Graph testGraph = new Graph("78813.edges.txt");
        Centrality c4 = new Centrality();
        int[] closeness = c4.getKatzCentrality(testGraph);
        for (int i : closeness) {
            System.out.println(i);
		}
		*/

        Graph2 testGraph = new Graph2("78813.edges.txt");
        //Graph testGraph = new Graph("428333.edges.txt");
        //Graph testGraph = new Graph("test.txt");
        CentralityEffieciencyImp c4 = new CentralityEffieciencyImp(testGraph);
        /**
        ArrayList<ArrayList<Integer>> degree = c4.getDegreeCentrality(testGraph);
        for (ArrayList<Integer> i : degree) {
        	System.out.println(i);
		}
        
        ArrayList<ArrayList<Integer>> closeness = c4.getClosenessCentrality(testGraph);
        for (ArrayList<Integer> i : closeness) {
        	System.out.println(i);
		}
		**/
        ArrayList<ArrayList<Integer>> betweeness = c4.getBetweenessCentrality(testGraph);
        for (ArrayList<Integer> i : betweeness) {
        	System.out.println(i);
		}
        /*
        ArrayList<ArrayList<Integer>> catz = c4.getKatzCentrality(testGraph);
        for (ArrayList<Integer> i : catz) {
        	System.out.println(i);
		}
		*/
        
        Graph testGraph1 = new Graph("78813.edges.txt");
        Centrality c3 = new Centrality(testGraph1);
        ArrayList<ArrayList<Integer>> betweeness2 = c3.getBetweenessCentrality(testGraph1);
        for (ArrayList<Integer> i : betweeness2) {
        	System.out.println(i);
		}
    	//for (int i = 0; i < closeness.length; i++) {
			//System.out.println(closeness[i]);
		//}
    	//int closenesss = c4.getKatzCentrality(testGraph);
    	//System.out.println(closenesss);
        //Graph testGraph = new Graph("test.txt");
        //FileData data = new FileData(3);
        //Centrality c4 = new Centrality(data);
        //Integer closeness = c4.getClosenessCentrality(testGraph);
        //System.out.println(closeness);
        
        /*
        Graph tg = new Graph("428333.edges.txt");
        FileData data = new FileData(1);
        Centrality c = new Centrality(data);
        float[] betweeness = c.getBetweenessCentrality(tg);
        System.out.println("---Betweeness Centralities----");
        for(float item: betweeness){
            System.out.println(item);
        }
        System.out.println("------------------------------");
        */
        
        
        
    	//for (int i = 0; i < closeness.length; i++) {
			//System.out.println(closeness[i]);
		//}
    	//double[] closenesss = c4.getKatzCentrality(testGraph);
        /**
        float[] floatArray = c4.getBetweenessCentrality(testGraph);
        System.out.println(testGraph);
        for (float f : floatArray) {
			System.out.println(f);
		}
		**/
        //int[] matrix = data428333.getAjMatrix();
        //ArrayList<Integer> nodesUnique = data428333.getUniqueNodes();
        // run the 4 centrality measures, also time performance
        long time = System.currentTimeMillis(); //Start timer
        /*
        Centrality graph438333 = new Centrality(data428333);
        Node[] degree42833 = graph438333.getDegreeCentrality(matrix, nodesUnique);
        System.out.println("Degree centrality for: " + data428333.getFileName());
        
        for(int i=0; i<degree42833.length; i++){
            System.out.println();
        }
        long endTime = System.currentTimeMillis();
        long totalTimeTaken = endTime - time;
        System.out.println("Run time:" + totalTimeTaken + "ms");
        System.out.println();
        */
        
        
        // read the file data for 78813.edges.txt
        /*
        try {
            FileData data78813 = new FileData(2);
            data78813.getNodes();
        }
        catch (Exception e) {


        }
        */
        //data78813.getAjMatrix();
    }
    

}

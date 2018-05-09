
/**
 * Stores all the information for each Node
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Node
{
    // instance variables - replace the example below with your own
    private int edgeCount;
    private int nodeID;
    private int currentDistance;
    private int previousNodeID;

    public Node(String type, int a, int b)
    {
        if (type.equals("Degree"))
	{
		edgeCount =  a;
		nodeID = b;
	}
	if (type.equals("Betweenness"))
	{
		currentDistance= Integer.MAX_VALUE;
		previousNodeID = a;
		nodeID = b;
	}
    }
    
    public int getEdgeCount() {
	return edgeCount;
    }
	
    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }
    
    public int getNodeID() {
        return nodeID;
    }
    
    public void setNodeID(int id){
        nodeID = id;
    }
    
    public int getCurrentDistance() {
	return currentDistance;
    }

    public void setCurrentDistance(int currentDistance) {
	this.currentDistance = currentDistance;
    }
}

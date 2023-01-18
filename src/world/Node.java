package world;

import java.util.ArrayList;

import core.Color;
import core.Main;
import core.Settings;
import ui.Text;


public class Node 
{
	private PathingGrid grid;
	private Cell cell;
	
	private int x;
	private int y;
	private int weight;
	private boolean needToUpdateNeighbors;
	
	public Node(PathingGrid grid, Cell cell)
	{		
		this.grid = grid;
		this.cell = cell;
		weight = -1;
		needToUpdateNeighbors = false;
	}
	
	
	public int getX()					{	return cell.getX();										}
	public int getY()					{	return cell.getY();										}
	public int getXPixel()				{	return cell.getXPixel();								}
	public int getYPixel()				{	return cell.getYPixel();								}
	public int getCost()				{	return cell.getMoveCost();								}

	public int getWeight()				{	return weight;											}

	public boolean isBlocked()			{	return cell.hasObstacle() || !cell.getTerrain().canEnter() || cell.hasAdversary();												}
	
	public boolean hasWestNode()		{	return Map.inBounds(x-1, y);								}
	public boolean hasEastNode()		{	return Map.inBounds(x+1, y);								}
	public boolean hasSouthNode()		{	return Map.inBounds(x, y+1);								}
	public boolean hasNorthNode()		{	return Map.inBounds(x, y-1);								}
	public Node getWestNode()			{	return hasWestNode() ? grid.getNode(x-1, y) : null;			}
	public Node getEastNode()			{	return hasEastNode() ? grid.getNode(x+1, y) : null;			}
	public Node getSouthNode()			{	return hasSouthNode() ? grid.getNode(x, y+1) : null;		}
	public Node getNorthNode()			{	return hasNorthNode() ? grid.getNode(x, y-1) : null;		}
	
	
	public boolean isUnreachable()		{	return weight >= 998;		}
	

	public boolean needToUpdateNeighbors()		{		return needToUpdateNeighbors;	}
	
	public void render()	
	{	
		if(Settings.debugMode)
		{
			Text.setColor(new Color(255, 255, 255, 255));
			Text.alignCenter();
			Text.alignMiddle();
			Text.draw(""+getWeight(), getXPixel()+Main.getCellSize()/2, getYPixel()+Main.getCellSize()/2);
		}
	}
	
	public void setOrigin()
	{
		weight = 0;
		updateNeighbors();
	}
	
	private void updateNeighbors()
	{
		needToUpdateNeighbors = false;			// This needs to happen before calls to neighbors
				
		if(hasWestNode())		{	getWestNode().setWeight(this);		}
		if(hasEastNode())		{	getEastNode().setWeight(this);		}
		if(hasNorthNode())		{	getNorthNode().setWeight(this);		}
		if(hasSouthNode())		{	getSouthNode().setWeight(this);		}
	
	}
	
	
	private void setWeight(Node parent)
	{			
	//	System.out.println("Cost: " + getCost());
		
		if(isBlocked() || parent.isBlocked())

		//		if(isBlocked() || parent.getWeight() == 999 || getWeight() == 999)
		{
			System.out.println("Blocked: " + getWeight() + " " + parent.getWeight());
			
			weight = 999;
		}
		
		else if(getWeight() == -1 || getWeight() > parent.getWeight() + getCost())
		{
			
			
			{
				//System.out.println("Old Weight: " + getWeight() + " " + parent.getWeight());
				
				weight = parent.getWeight() + getCost();
				
			//	System.out.println("New Weight: " + getWeight() + " " + parent.getWeight());

				needToUpdateNeighbors = true;
			}		
		}

		if(needToUpdateNeighbors())
		{
			updateNeighbors();
		}
	}
	
	void resetWeight()
	{		
		weight = -1;
	}
	
	void lockUnreachable()
	{
		if(weight == -1)
		{
			weight = 999;
		}
	}
	
	private Node getLowestWeightNeighbor()
	{
		int lowVal = 999;
		Node lowNode = null;
		
		for(Node n : getNeighbors())
		{
			if(n != null && !n.isBlocked() && n.getWeight() < lowVal)
			{				
				lowVal = n.getWeight();
				lowNode = n;
			}
		}
		
		//System.out.println(lowCell);
		if(lowNode == null || lowNode.getWeight() >= getWeight())
		{
			return null;
		}
		else
		{	
			return lowNode;
		}
		
		
	}
	
	private ArrayList<Node> getNeighbors()
	{
		ArrayList<Node> neighbors = new ArrayList<Node>();
		if(hasWestNode()) neighbors.add(getWestNode());
		if(hasEastNode()) neighbors.add(getEastNode());
		if(hasNorthNode()) neighbors.add(getNorthNode());
		if(hasSouthNode()) neighbors.add(getSouthNode());
		return neighbors;
	}
}

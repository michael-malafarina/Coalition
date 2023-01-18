package world;

import java.util.ArrayList;

public class PathingGrid 
{
	private ArrayList<Node> nodes;
	Node origin;
	
	PathingGrid(ArrayList<Cell> cells, Cell origin)
	{
		nodes = new ArrayList<Node>();
		
		for(int i = 0; i < cells.size(); i++)
		{
			Node n = new Node(this, cells.get(i));
			if(cells.get(i) == origin)
			{
				this.origin = n;
			}
			nodes.add(n);
		}
		
		
//		System.out.println(nodes.get(72).getX() + " "+ nodes.get(72).getY());
	}
	
	
	public Node getNode(Cell c)			{	return getNode(c.getX(), c.getY());				}
	public Node getNode(int x, int y)	{	return nodes.get(y * Map.getCols() + x);	}

	public boolean canReachCell(Cell c, int maxDistance)
	{
		Node n = getNode(c);
		
		if(n.isBlocked() || n.isUnreachable())
		{
			return false;
		}
		else if(n.getCost() > 1)
		{
			return n.getWeight() - maxDistance == 1;
		}
		else
		{
			//System.out.println(n.getWeight() + " " + maxDistance);
			return n.getWeight() <= maxDistance;
		}
	}
		
	public void calculateDistances()
	{	
		if(origin == null)
		{
			return;
		}
		
		for(Node n : nodes)
		{
			n.resetWeight();
		}

		origin.setOrigin();
		//System.out.println(origin.getWeight());

		
		System.out.println("");

		
//		for(int i = 0; i < nodes.size(); i++)
//		{
//			Node n = nodes.get(i);
//			if(i % Map.getCols() == 0)
//			{
//				System.out.println();
//			}
//			
//			System.out.print(n.getWeight());
//		}
		
//			
		for(Node n : nodes)
		{
		//	n.lockUnreachable();

		}
	}
	
	public void render()
	{

		for(Node n : nodes)
		{
			n.render();
		}
	}

}

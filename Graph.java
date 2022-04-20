import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.PriorityQueue;

/**
 * Graph's purpose is to create a graph of Town and Road, getting their connectiong and trying to find the shortest paths between vertices.
 * @author Michael DeReggi
 * @param <E>
 * @param <V>
 */
public class Graph<E,V> implements GraphInterface<Town,Road>
{
	
	Town sDest;
	private ArrayList<Town> townList;
	private Road[][] roads;
	private Set<Town> vertices=new HashSet<>();
	private Set<Road> edges=new HashSet<>();
	private ArrayList<String>sPath = new ArrayList<String>();
	private Map<String, Town> previousVertex=new HashMap<>();
	public Graph()
	{
		roads=new Road[13][13];
		townList=new ArrayList<Town>();
	}
	/**
	 *@param v vertex to be added to this graph.
     * @return true if this graph did not already contain the specified
     * vertex.
     * @throws NullPointerException if the specified vertex is null.
	 */
	@Override
	public boolean addVertex(Town v)
	{
		if(!containsVertex(v))
		{
			townList.add(v);
			vertices.add(v);
			return true;
		}
		return false;
	}
	/**
	 *@return a set view of the vertices contained in this graph.
	 */
	@Override
	public Set<Town> vertexSet()
	{
		return vertices;
	}
	/**
	 *@return a set of the edges contained in this graph.
	 */
	@Override
	public Set<Road> edgeSet()
	{
		return edges;
	}
	/**
	 *@param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
	 */
	@Override
	public boolean containsVertex(Town v)
	{
		for(int i=0; i<townList.size();i++)
		{
			if(townList.get(i).equals(v))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 *@param Town1 town's index trying to get
	 */
	public int townIndex(Town Town1)
	{
		for(int i=0;i<townList.size();i++)
		{
			if(Town1==townList.get(i))
			{
				return i;
			}
		}
		return -1;
	}
	/**
	 *@param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return true if this graph contains the specified edge.
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex)
	{
		if(townIndex(sourceVertex)!=-1 && townIndex(destinationVertex)!=-1)
		{
			if(roads[townIndex(sourceVertex)][townIndex(destinationVertex)]!=null)
			{
				return true;
			}
		}
		return false;
	}
	/**
	 *@param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return an edge connecting source vertex to target vertex.
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex)
	{
		if(containsEdge(sourceVertex, destinationVertex))
		{
			return roads[townIndex(sourceVertex)][townIndex(destinationVertex)];
		}
		return null;
	}
	/**
	 *@param vertex the vertex for which a set of touching edges is to be
     * returned.
     * @return a set of all edges touching the specified vertex.
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
	 */
	@Override
	public Set<Road> edgesOf(Town vertex)
	{
		Set<Road> edgesOf=new HashSet<>();
		Iterator<Road> check=edges.iterator();
		Road include;
		while(check.hasNext())
		{
			include=check.next();
			if(include.contains(vertex))
			{
				edgesOf.add(include);
			}
		}
		return edgesOf;
	}
	/**
	 *@param v vertex to be removed from this graph, if present.
     * @return true if the graph contained the specified vertex;
     * false otherwise.
	 */
	@Override
	public boolean removeVertex(Town v)
	{
		if(v==null)
		{
			return false;
		}
		Set<Road> vEdges=edgesOf(v);
		Iterator<Road> check=vEdges.iterator();
		Road include;
		if(vEdges!=null)
		{
			while(check.hasNext())
			{
				include=check.next();
				removeEdge(include.getSource(),include.getDestination(),include.getWeight(),include.getName());
			}
		}
		for(int i=0;i<townList.size();i++)
		{
			if(townList.get(i).equals(v))
			{
				townList.remove(i);
			}
		}
		return false;
	}
	/**
	 *@param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     * @return The newly created edge if added to the graph, otherwise null.
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
	{
		Road toReturn = null;
		int destIndex = townIndex(destinationVertex);
		int sourceIndex = townIndex(sourceVertex);
		if (sourceIndex != -1 && destIndex != -1) {
			roads[sourceIndex][destIndex] = new Road(sourceVertex, destinationVertex, weight, description);
			roads[destIndex][sourceIndex] = new Road(destinationVertex,sourceVertex, weight, description);
		}
		toReturn = new Road(sourceVertex, destinationVertex, weight, description);
		edges.add(toReturn);
		return toReturn;
	}
	/**
	 *@param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     * @return The removed edge, or null if no edge removed.
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
	{
		Road r = null;
		int destIndex = townIndex(destinationVertex);
		int sourceIndex = townIndex(sourceVertex);
		if (roads[sourceIndex][destIndex] == null || roads[destIndex][sourceIndex] == null)
		{
			return null;
		}
		else
		{
			r = roads[sourceIndex][destIndex];
		}
		if (r.contains(sourceVertex) && r.contains(destinationVertex))
		{
			if ((r.getName().equals(description)) && (r.getWeight() == weight))
			{
				roads[sourceIndex][destIndex] = null;
				roads[destIndex][sourceIndex] = null;
			}
		}
		return r;
	}
	/**
	 *@param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
	 */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
	{
		
		ArrayList<String> output = new ArrayList<String>();
		dijkstraShortestPath(sourceVertex);
		Town nextTown = destinationVertex;
		while (!nextTown.equals(sourceVertex))
		{
			if (!previousVertex.containsKey(nextTown.getName()))
			{
				output.clear();
				break;
			}
			Town previousTown = previousVertex.get(nextTown.getName());
			if (previousTown == null)
			{
				return output;
			}
			Road edge = getEdge(previousTown, nextTown);
			
			
			output.add(0, previousTown.getName() + " via " + edge.getName() + " to " + nextTown.getName() + " " + edge.getWeight() + " mi");
			
			nextTown = previousTown;
		}
		return output;
	}
	/**
	 *@param sourceVertex the vertex to find shortest path from
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex)
	{
		
		
		ArrayList<Town> unchecked = new ArrayList<Town>();
		Set<Town> visisted = new HashSet<Town>();
		
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		previousVertex.clear();
		for (Town thisTown : vertices)
		{
			unchecked.add(thisTown);
			distance.put(thisTown.getName(), Integer.MAX_VALUE);
			previousVertex.put(thisTown.getName(), null);
		}
		distance.put(sourceVertex.getName(), 0);
		while (!unchecked.isEmpty())
		{
			
			int shortest = 0; 
			for (int i = 0; i < unchecked.size(); i++)
			{
				Town uncheckedVertex = unchecked.get(i);
				
				if ( distance.get(unchecked.get(shortest).getName()) > distance.get(uncheckedVertex.getName()) )
					shortest = i;
			}
			Town closest = unchecked.remove(shortest);
			visisted.add(closest);
			if (distance.get(closest.getName())==Integer.MAX_VALUE) {
				return;
			}
			for (Road eachEdge : edgesOf(closest))
			{
				
				Town adjacent = eachEdge.getDestination();
				
				if (adjacent.equals(closest))
				{
					adjacent = eachEdge.getSource();
				}
				if (visisted.contains(adjacent))
				{
					continue;
				}
				int adjDistance = distance.get(closest.getName()) + eachEdge.getWeight();
				if (adjDistance < distance.get(adjacent.getName()))
				{
					distance.put(adjacent.getName(), adjDistance);
					previousVertex.put(adjacent.getName(), closest);
				}
			}
		}
	}

}
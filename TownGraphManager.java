import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Michael DeReggi
 * Uses a graph with data from a file
 */
public class TownGraphManager implements TownGraphManagerInterface
{
	private Graph<Town,Road> graph=new Graph<Town, Road>();
	@Override
	/**
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName)
	{
		try
		{
			Town Town1=new Town(town1);
			Town Town2=new Town(town2);
			Town current;
			Set<Town> find=graph.vertexSet();
			Iterator<Town> locate=find.iterator();
			while(locate.hasNext())
			{
				current=locate.next();
				if(current.getName().equals(town1))
				{
					Town1=current;
				}
				if(current.getName().equals(town2))
				{
					Town2=current;
				}
			}
			graph.addEdge(Town1, Town2, weight, roadName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	/**
	 *@param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v)
	{
		try
		{
			Town town1=new Town(v);
			graph.addVertex(town1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	/**
	 *@param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public Town getTown(String name)
	{
		try
		{
			Town Town1;
			Iterator<Town> iterator=graph.vertexSet().iterator();
			while(iterator.hasNext())
			{
				Town1=iterator.next();
				if(Town1.getName().equals(name))
				{
					return Town1;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *@param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2)
	{
		try
		{
			Town Town1=new Town(town1);
			Town Town2=new Town(town2);
			Town current;
			Set<Town> find=graph.vertexSet();
			Iterator<Town> locate=find.iterator();
			while(locate.hasNext())
			{
				current=locate.next();
				if(current.getName().equals(town1))
				{
					Town1=current;
				}
				if(current.getName().equals(town2))
				{
					Town2=current;
				}
			}
			Road road=graph.getEdge(Town1, Town2);
			return road.getName();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *@param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v)
	{
		Town Town1=new Town(v);
		return graph.containsVertex(Town1);
	}
	/**
	 *@param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2)
	{
		Town Town1=new Town(town1);
		Town Town2=new Town(town2);
		Town current;
		Set<Town> find=graph.vertexSet();
		Iterator<Town> locate=find.iterator();
		while(locate.hasNext())
		{
			current=locate.next();
			if(current.getName().equals(town1))
			{
				Town1=current;
			}
			if(current.getName().equals(town2))
			{
				Town2=current;
			}
		}
		return graph.containsEdge(Town1, Town2);
	}
	/**
	 *@return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads()
	{
		Iterator<Road> iterator=graph.edgeSet().iterator();
		ArrayList<String> roads=new ArrayList<>();
		while(iterator.hasNext())
		{
			roads.add(iterator.next().getName());
		}
		Collections.sort(roads);
		return roads;
	}
	/**
	 *@param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v)
	{
		Town Town1=new Town(v);
		return graph.removeVertex(Town1);
	}
	/**
	 *@return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns()
	{
		Iterator<Town> iterator=graph.vertexSet().iterator();
		ArrayList<String> towns=new ArrayList<>();
		while(iterator.hasNext())
		{
			towns.add(iterator.next().getName());
		}
		Collections.sort(towns);
		return towns;
	}
	/**
	 *@param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2)
	{
		Town Town1=new Town(town1);
		Town Town2=new Town(town2);
		Town current;
		Set<Town> find=graph.vertexSet();
		Iterator<Town> locate=find.iterator();
		while(locate.hasNext())
		{
			current=locate.next();
			if(current.getName().equals(town1))
			{
				Town1=current;
			}
			if(current.getName().equals(town2))
			{
				Town2=current;
			}
		}
		return graph.shortestPath(Town1, Town2);
	}
	/**
	 *@param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road)
	{
		Town Town1=new Town(town1);
		Town Town2=new Town(town2);
		Town current;
		Set<Town> find=graph.vertexSet();
		Iterator<Town> locate=find.iterator();
		while(locate.hasNext())
		{
			current=locate.next();
			if(current.getName().equals(town1))
			{
				Town1=current;
			}
			if(current.getName().equals(town2))
			{
				Town2=current;
			}
		}
		Road road1=graph.getEdge(Town1, Town2);
		graph.removeEdge(road1.getSource(), road1.getDestination(), road1.getWeight(), road1.getName());
		return graph.containsEdge(Town1, Town2);
	}
	/**
	 *@param selectedFile, file used for populating town graph
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException,IOException
	{
		// TODO Auto-generated method stub
        Scanner readInput = new Scanner(selectedFile);
        String text = "";
        while (readInput.hasNextLine()) {
            text += readInput.nextLine() + " ";
        }
        readInput.close();
        
        String[] roads = text.split(" ");
        String[][] roadsInfo = new String[roads.length][];
        
        for (int i = 0; i < roadsInfo.length; i++) {
            
            roadsInfo[i] = new String[4];
            roadsInfo[i][0] = roads[i].split(";")[0].split(",")[0];
            roadsInfo[i][1] = roads[i].split(";")[0].split(",")[1];
            roadsInfo[i][2] = roads[i].split(";")[1];
            roadsInfo[i][3] = roads[i].split(";")[2];
            
            addTown(roadsInfo[i][2]);
            addTown(roadsInfo[i][3]);
            addRoad(roadsInfo[i][2], roadsInfo[i][3], 
                    Integer.parseInt(roadsInfo[i][1]), roadsInfo[i][0]);
        }
		
	}

}
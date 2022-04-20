import java.util.List;
/**
 * @author Michael DeReggi
 * represents the vertices for graph
 *
 */
public class Town implements Comparable<Town>
{
	private String name;
	private List<Town> adjacentTown;
	public Town(String name)
	{
		this.name=name;
	}
	public Town(Town templateTown)
	{
		name=templateTown.name;
	}
	
	
	/**
	 *@param o Town being compared
	 *@return -1 if not equal, 0 if equal
	 */
	public int compareTo(Town o)
	{
		return getName().compareTo(o.getName());
	}
	
	/**
	 * @param x Town being checked if equal
	 * @return if equal
	 */
	public boolean equals(Town x)
	{
		if(getName().equals(x.getName()))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @return adjacentTown
	 */
	public List<Town> getAdj()
	{
		return adjacentTown;
	}
	/**
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 *@return hash
	 */
	public int hashCode()
	{
		return 0;
	}
	/**
	 *@return String of town
	 */
	public String toString()
	{
		return getName();
	}
}
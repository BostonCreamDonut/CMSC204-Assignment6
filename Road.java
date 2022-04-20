/**
 * @author Michael DeReggi
 *Represents the edges of a Grapg of Towns
 */
public class Road implements Comparable<Road>
{
	private Town source;
	private Town destination;
	private int degrees;
	private String name;
	private int weight;
	public Road(Town source, Town destination, int degrees, String name)
	{
		this.source=source;
		this.destination=destination;
		this.degrees=degrees;
		this.name=name;
		weight=degrees;
	}
	public Road(Town source, Town destination, String name)
	{
		this.source=source;
		this.destination=destination;
		this.name=name;
		weight=0;
	}
	
	/**
	 *@param o road being compared
	 *@return 1 if not equal, 0 if equal
	 */
	public int compareTo(Road o)
	{
		if(source.equals(o.getSource())&& destination.equals(o.getDestination()))
		{
			return 0;
		}
		return 1;
	}
	/**
	 * @return destination
	 */
	public Town getDestination()
	{
		return this.destination;
	}
	/**
	 * @return source
	 */
	public Town getSource() {
		return this.source;
	}
	/**
	 * @return degrees
	 */
	public int getDegrees() {
		return this.degrees;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @return weigh (degrees)
	 */
	public int getWeight() {
		return this.weight;
	}
	/**
	 * @param s town checking if connected by road
	 * @return if road contains town s
	 */
	public boolean contains(Town s)
	{
		if(source.equals(s) || destination.equals(s))
		{
			return true;
		}
		return false;
	}
	/**
	 *
	 */
	public boolean equals(Object x)
	{
		if(source.equals(((Road) x).getSource())&& destination.equals(((Road) x).getDestination()))
		{
			return true;
		}
		return false;
	}
	/**
	 *
	 */
	public String toString()
	{
		return "";
	}
}
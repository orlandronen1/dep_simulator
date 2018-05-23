package vector;

/**
 * A 3-dimensional vector with x, y, and z components
 * 
 * @author Ronen Orland
 */
public class Vector
{
	// The x, y, and z components
	private double x;
	private double y;
	private double z;
	
	
	/**
	 * Default constructor, initializes all fields to 0
	 */
	public Vector()
	{
		this(0.0, 0.0, 0.0);
	}
	
	/**
	 * Constructor that takes initial values for all components
	 * 
	 * @param x		the initial x value
	 * @param y 	the initial y value
	 * @param z		the initial z value
	 */
	public Vector(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Constructor that copies an input vector
	 * 
	 * @param vec	the vector to copy
	 */
	public Vector(Vector vec)
	{
		x = vec.getX();
		y = vec.getY();
		z = vec.getZ();
	}
	
	
	/**
	 * Returns the x component
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Returns the y component
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * Returns the z component
	 */
	public double getZ()
	{
		return z;
	}
	
	/**
	 * Sets the x component to a new value
	 * @param x		the new value of the x component
	 */
	public void setX(double x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the y component to a new value
	 * @param y		the new value of the y component
	 */
	public void setY(double y)
	{
		this.y = y;
	}
	
	/**
	 * Sets the z component to a new value
	 * @param z		the new value of the z component
	 */
	public void setZ(double z)
	{
		this.z = z;
	}
	
	/**
	 * Sets all three components to new values
	 */
	public void setAll(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	/**
	 * Adds another vector to this vector
	 * 
	 * @param vec	the vector to add to this vector
	 */
	public void add(Vector vec)
	{
		x += vec.getX();
		y += vec.getY();
		z += vec.getZ();
	}
	
	/**
	 * Adds to each component of this vector
	 * 
	 * @param x    added to the x component
	 * @param y    added to the y component
	 * @param z    added to the z component
	 */
	public void add(double x, double y, double z)
	{
	    this.x += x;
	    this.y += y;
	    this.z += z;
	}
	
	/**
	 * Subtracts another vector from this vector
	 * 
	 * @param vec	the vector to subtract from this vector
	 */
	public void sub(Vector vec)
	{
		x -= vec.getX();
		y -= vec.getY();
		z -= vec.getZ();
	}
    
    /**
     * Subtracts from each component of this vector
     * 
     * @param x    subtracted from the x component
     * @param y    subtracted from the y component
     * @param z    subtracted from the z component
     */
    public void sub(double x, double y, double z)
    {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }
}

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
	 * Constructor that initializes all fields to the same value
	 * 
	 * @param i    value for all fields
	 */
	public Vector(double i)
	{
	    this(i,i,i);
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
	 * Checks if this vector is equal to another
	 * @param vec  the vector to check equality against
	 * @return     true if equal, false if not
	 */
	public boolean equals(Vector vec)
	{
	    if (x == vec.getX()) 
	    {
	        if (y == vec.getY())
	        {
	            if (z == vec.getZ())
	                return true;
	        }
	    }
	    
        return false;
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
    
    /**
     * Multiplies each component by a double
     * 
     * @param i     value to multiply each component by
     */
    public void mult(double i)
    {
       x *= i;
       y *= i;
       z *= i;
    }
    
    /**
     * Multiplies each component to other component values
     * 
     * @param x     x multiplier
     * @param y     y multiplier
     * @param z     z mulitplier
     */
    public void mult(double x, double y, double z)
    {
        this.x *= x;
        this.y *= y;
        this.z *= z;
    }
    
    /**
     * Mulitiplies the components of this vector by the components of another
     * 
     * @param vec   the vector to multiply by component-wise
     */
    public void mult(Vector vec)
    {
        x *= vec.getX();
        y *= vec.getY();
        z *= vec.getZ();
    }
    
    /**
     * Returns the distance between this Vector and another 
     * 
     * @param vec   The Vector to measure the distance to
     * @return      The distance between the Vectors
     */
    public double distance(Vector vec)
    {
        double xTerm = x - vec.getX();
        xTerm *= xTerm;
        
        double yTerm = y - vec.getY();
        yTerm *= yTerm;
        
        double zTerm = z - vec.getZ();
        zTerm *= zTerm;
        
        return Math.sqrt(xTerm + yTerm + zTerm);
    }
    
    /**
     * Returns the unit Vector to this Vector
     * 
     * @return  the unit Vector for this Vector
     */
    public Vector unit()
    {
        double abs = Math.sqrt(x*x + y*y + z*z);
        
        if (abs == 0)
            return null;
        
        return new Vector(x/abs, y/abs, z/abs);
    }
}

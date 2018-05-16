/*
 * An abstract class that represents a particle or cell 
 * 
 * @author Ronen Orland
 */

package particles;

import vector.Vector;

public abstract class Particle 
{
	final static double mass = 1.0;		// Mass of the particle in kilograms
	final static double radius = 1.0;	// Radius of the particle in meters
	
	// TODO create a field(s) describing the dielectric properties of the particle
	
	private Vector position;			// Position of the particle in 3D space
	
	
	/*
	 * Default constructor, initializes position
	 */
	Particle()
	{
		position = new Vector();
	}
	
	/*
	 * Constructor that takes a vector as the initial position
	 * 
	 * @param pos	the Vector to use as the initial position
	 */
	public Particle(Vector pos)
	{
		position = new Vector(pos);
	}
	
	/*
	 * Constructor that takes the initial position vector components
	 * 
	 * @param x		the initial x position
	 * @param y		the initial y position
	 * @param z		the initial z position
	 */
	public Particle(double x, double y, double z)
	{
		position = new Vector(x, y, z);
	}
	
	
	/*
	 * Returns the mass of the particle
	 */
	public static double getMass()
	{
		return mass;
	}
	
	/*
	 * Returns the radius of the particle
	 */
	public static double getRadius()
	{
		return radius;
	}
	
	/*
	 * Returns the position vector of the particle
	 */
	public Vector getPosition()
	{
		return position;
	}
	
	
	/*
	 * Moves the particle based on a given force vector
	 * 
	 * @param force		the vector representing the force acting on the particle
	 */
	public void move(Vector force)
	{
		// TODO finish method
	}
}

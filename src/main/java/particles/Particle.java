/*
 * An abstract class that represents a particle or cell 
 * TODO     If end up using a config file to differentiate cells, make not abstract
 * 
 * @author Ronen Orland
 */

package particles;

import java.util.Random;
import vector.Vector;

/**
 * A generic particle with a mass, radius, and position
 * 
 * @author Ronen Orland
 */
public abstract class Particle 
{
	public final static double avgMass = 0.0000000000001;     // Typical mass of the particle in kilograms
	public final static double massTolerance = 0.000001;      // Tolerance of the particle's mass in kilograms
	
	public final static double avgRadius = 0.000001;          // Typical radius of the particle in meters
	public final static double radiusTolerance = 0.0000005;   // Tolerance of the particle's radius in meters
	
	// TODO create a field(s) describing the dielectric properties of the particle
	
	protected Vector position;           // Position of the particle in 3D space
	protected double mass;         // Mass of this specific particle
	protected double radius;       // Radius of this specific particle
	
	
	/**
	 * Default constructor, initializes position
	 */
	public Particle()
	{
		this(new Vector());
	}
	
	/**
	 * Constructor that takes the initial position vector components
	 * 
	 * @param x		the initial x position
	 * @param y		the initial y position
	 * @param z		the initial z position
	 */
	public Particle(double x, double y, double z)
	{
		this(new Vector(x, y, z));
	}
    
    /**
     * Constructor that takes a vector as the initial position
     * 
     * @param pos   the Vector to use as the initial position
     */
    public Particle(Vector pos)
    {
        position = new Vector(pos);
        
        Random rand = new Random();
        double tolerance = rand.nextGaussian();
        tolerance %= 1.0;
        
        mass = avgMass + (massTolerance * tolerance);
        radius = avgRadius + (radiusTolerance * tolerance);
    }
	
	
    /**
	 * Returns the mass of the particle
	 * 
	 * @return mass of the particle
	 */
	public double getMass()
	{
		return mass;
	}
	
	/**
	 * Returns the radius of the particle
	 * 
	 * @return radius of the particle
	 */
	public double getRadius()
	{
		return radius;
	}
	
	/**
	 * Returns the position vector of the particle
	 * 
	 * @return position Vector of the particle
	 */
	public Vector getPosition()
	{
		return position;
	}
	
	
	/**
	 * Moves the particle based on a given force vector
	 * 
	 * @param force		the vector representing the force acting on the particle
	 * @param time		the time spent moving due to the given force
	 */
	public void move(Vector force, double time)
	{
		/*  TODO check this later, make sure it's correct and accurate
		 *  ---> DON'T want just an approximation
		 *  xf = x0 + v*t + .5*a*t^2
		 *     = x0 + a*t^2 + .5*a*t^2
		 *     = x0 + 1.5*a*t^2
		 *     = x0 + 1.5*t^2*F/m
    	 */
		
		// Move in x direction
		double x = position.getX();
		x = x + ( (1.5 * Math.pow(time, 2) * force.getX()) / mass);
		
		// Move in y direction
		double y = position.getY();
        y = y + ( (1.5 * Math.pow(time, 2) * force.getY()) / mass);
		
		// Move in z direction
        double z = position.getZ();
        z = z + ( (1.5 * Math.pow(time, 2) * force.getZ()) / mass);
        
        // Update position
        position.setX(x);
        position.setY(y);
        position.setZ(z);
	}
}

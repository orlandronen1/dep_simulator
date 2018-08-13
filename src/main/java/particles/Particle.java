package particles;

import java.util.Random;
import vector.Vector;
import medium.Medium;

/**
 * A generic particle with a mass, radius, position, relative permittivity, and conductivity
 * 
 * @author Ronen Orland
 */
public abstract class Particle 
{
    public final static double VACUUM_PERMITTIVITY = Math.pow(8.854187817, -12);    // Permittivity of free space
	public final static double avgMass = 0.0000000000001;          // Typical mass of the particle in kilograms
	public final static double massTolerance = 0.00000000000005;   // Tolerance of the particle's mass in kilograms
	
	public final static double avgRadius = 0.000001;          // Typical radius of the particle in meters
	public final static double radiusTolerance = 0.0000005;   // Tolerance of the particle's radius in meters
	
	public final static double permittivity = 1;       // Relative permittivity of the particle
    public final static double conductivity = 1;       // Conductivity of the particle
	private static double fcmReal;     // Real part of the Clausius-Mossotti factor
	private static double fcmImag;     // Imaginary part of the Clausius-Mossotti factor
	
	protected double velocity;		// Velocity of the particle in m/s
	protected Vector position;		// Position of the particle in 3D space
	protected double mass;          // Mass of this specific particle
	protected double radius;        // Radius of this specific particle
	
	
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
	 * Returns the velocity of the particle
	 * 
	 * @return velocity of the particle
	 */
	public double getVelocity()
	{
		return velocity;
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
	 * Sets the position of the particle
	 * 
	 * @param x    x-coordinate
	 * @param y    y-coordinate
	 * @param z    z-coordinate
	 */
	public void setPosition(double x, double y, double z)
	{
	    position = new Vector(x,y,z);
	}
	
	/**
	 * Sets the position of the particle
	 * 
	 * @param pos  Vector describing the new position
	 */
	public void setPosition(Vector pos)
	{
	    position = new Vector(pos);
	}
	
	/**
	 * 
	 * @return relative permittivity of the particle
	 */
	public double getPermittivity()
	{
	    return permittivity;
	}
	
	/**
	 * 
	 * @return real part of the complex permittivity of the particle
	 */
	public double getFcmReal()
	{
	    return fcmReal;
	}
	
	/**
	 * 
	 * @return imaginary part of the complex permittivity of the particle
	 */
	public double getFcmImag()
	{
	    return fcmImag;
	}
	
	/**
	 * Calculates the real and imaginary parts of the complex 
	 * @param medium
	 * @param frequency
	 */
	public void calcFcm(Medium medium, double frequency)
	{
        if (frequency < 0)
            throw new IllegalArgumentException("Frequency must be a positive number");
        
        double angularFreq = frequency * 2 * Math.PI;   // Angular frequency
        double freq2 = angularFreq * angularFreq;
        double permd = (permittivity - medium.getPermittivity()) * VACUUM_PERMITTIVITY;
        double perms = (permittivity + 2*medium.getPermittivity()) * VACUUM_PERMITTIVITY;
        double condd = conductivity - medium.getConductivity();
        double conds = conductivity + 2*medium.getConductivity();
        double denom = (freq2 * perms * perms) + (conds * conds);
        
        fcmReal = ((freq2 * permd * perms) + (condd * conds)) / denom;
        fcmImag = ((angularFreq * condd * perms) - (permd * conds)) / denom;
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
        
        Vector distance = new Vector(x,y,z);
        
        // Update position and velocity
        position.setX(x);
        position.setY(y);
        position.setZ(z);
        velocity = distance.distance(position) / time;
	}
}

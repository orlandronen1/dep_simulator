/*
 * Info found at https://web.archive.org/web/20110808031933/http://www.weizmann.ac.il/plants/Milo/images/YeastSize-Feb2010.pdf 
 */

package particles;

import java.util.Random;

import vector.Vector;

/**
 * Represents a typical yeast cell
 * 
 * @author Ronen Orland
 */
public class Yeast extends Particle 
{
    public final static double avgMass = 0.00000000000006;
    public final static double massTolerance = 0.000000000000005;       // TODO get a number
	
    public final static double avgRadius = 0.000005;
    public final static double radiusTolerance = 0.0000005;     // TODO get a number
	
	
    /**
     * Default constructor, initializes position
     */
    public Yeast()
    {
        this(new Vector());
    }
    
    /**
     * Constructor that takes the initial position vector components
     * 
     * @param x     the initial x position
     * @param y     the initial y position
     * @param z     the initial z position
     */
    public Yeast(double x, double y, double z)
    {
        this(new Vector(x, y, z));
    }
    
    /**
     * Constructor that takes a vector as the initial position
     * 
     * @param pos   the Vector to use as the initial position
     */
    public Yeast(Vector pos)
    {
        position = new Vector(pos);
        
        Random rand = new Random();
        double tolerance = rand.nextGaussian();
        tolerance %= 1.0;
        
        mass = avgMass + (massTolerance * tolerance);
        radius = avgRadius + (radiusTolerance * tolerance);
    }
}

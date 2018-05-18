/*
 * Info found at https://web.archive.org/web/20110808031933/http://www.weizmann.ac.il/plants/Milo/images/YeastSize-Feb2010.pdf 
 */

package particles;

import vector.Vector;

/**
 * Represents a typical yeast cell
 */
public class Yeast extends Particle 
{
	final static double avgMass = 0.00000000000006;
	final static double massTolerance = 0.0;       // TODO get a number
	
	final static double avgRadius = 0.000005;
	final static double radiusTolerance = 0.0;     // TODO get a number
	
	
	public Yeast()
	{
	    super();
	}
	
	public Yeast(Vector vec)
	{
	    super(vec);
	}
	
	public Yeast(double x, double y, double z)
	{
	    super(x, y, z);
	}
}

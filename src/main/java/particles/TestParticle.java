package particles;

import vector.Vector;

/**
 * Very basic particle with mass of 1kg and radius of 1m simply for testing purposes
 * 
 * @author Ronen Orland
 *
 */
public class TestParticle extends Particle
{
    public final static double avgMass = 1;         // Typical mass of the particle in kilograms
    public final static double massTolerance = 0;   // Tolerance of the particle's mass in kilograms
    
    public final static double avgRadius = 1;       // Typical radius of the particle in meters
    public final static double radiusTolerance = 0; // Tolerance of the particle's radius in meters
    
    public TestParticle()
    {
        this(new Vector());
    }

    public TestParticle(double x, double y, double z)
    {
        this(new Vector(x, y, z));
    }

    public TestParticle(Vector pos)
    {
        position = pos;
        mass = avgMass;
        radius = avgRadius;
    }

}

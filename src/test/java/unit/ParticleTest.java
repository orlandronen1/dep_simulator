package unit;

import static org.junit.Assert.*;
import org.junit.Test;

import medium.*;
import particles.*;
import vector.Vector;

/**
 * Unit testing for Particle and its subclasses
 * 
 * @author Ronen Orland
 */
public class ParticleTest
{

    @Test
    public void constructorTest()
    {
        Particle particle;
        Vector position;
        double mass, radius;
        
        // Default constructor
        particle = new Yeast();
        position = particle.getPosition();
        mass = particle.getMass();
        radius = particle.getRadius();
        
        assertTrue(position.getX() == 0);
        assertTrue(position.getY() == 0);
        assertTrue(position.getZ() == 0);
        assertTrue(mass <= Yeast.avgMass + Yeast.massTolerance);
        assertTrue(mass >= Yeast.avgMass - Yeast.massTolerance);
        assertTrue(radius <= Yeast.avgRadius + Yeast.radiusTolerance);
        assertTrue(radius >= Yeast.avgRadius - Yeast.radiusTolerance);
        
        // Constructor with doubles for position
        particle = new Yeast(1.0, 1.0, 1.0);
        position = particle.getPosition();
        mass = particle.getMass();
        radius = particle.getRadius();
        
        assertTrue(position.getX() == 1.0);
        assertTrue(position.getY() == 1.0);
        assertTrue(position.getZ() == 1.0);
        assertTrue(mass <= Yeast.avgMass + Yeast.massTolerance);
        assertTrue(mass >= Yeast.avgMass - Yeast.massTolerance);
        assertTrue(radius <= Yeast.avgRadius + Yeast.radiusTolerance);
        assertTrue(radius >= Yeast.avgRadius - Yeast.radiusTolerance);
        
        // Constructor with vector for position
        Vector construct = new Vector(1.0, 1.0, 1.0);
        particle = new Yeast(construct);
        position = particle.getPosition();
        mass = particle.getMass();
        radius = particle.getRadius();
        
        assertTrue(position.getX() == construct.getX());
        assertTrue(position.getY() == construct.getY());
        assertTrue(position.getZ() == construct.getZ());
        assertTrue(mass <= Yeast.avgMass + Yeast.massTolerance);
        assertTrue(mass >= Yeast.avgMass - Yeast.massTolerance);
        assertTrue(radius <= Yeast.avgRadius + Yeast.radiusTolerance);
        assertTrue(radius >= Yeast.avgRadius - Yeast.radiusTolerance);
    }
    
    @Test
    public void moveTest()
    {
        Particle particle = new Yeast();
        Vector force = new Vector(1.0, 1.0, 1.0);
        double time = 1.0;
        
        double x = (1.5 * Math.pow(time, 2) * force.getX()) / particle.getMass();
        double y = (1.5 * Math.pow(time, 2) * force.getY()) / particle.getMass();
        double z = (1.5 * Math.pow(time, 2) * force.getZ()) / particle.getMass();
                
        particle.move(force, time);
        Vector position = particle.getPosition();
        
        assertTrue(position.getX() != 0);
        assertTrue(position.getX() == x);
        assertTrue(position.getY() != 0);
        assertTrue(position.getY() == y);
        assertTrue(position.getZ() != 0);
        assertTrue(position.getZ() == z);
    }
    
    @Test
    public void fcmTest()
    {
        Particle particle = new Yeast();
        Medium medium = new DistilledWater();
        
        particle.calcFcm(medium, 10);
        double real1 = particle.getFcmReal();
        double imag1 = particle.getFcmImag();
        
        particle.calcFcm(medium, 5000);
        double real2 = particle.getFcmReal();
        double imag2 = particle.getFcmImag();
        
        assertTrue(real1 != 0);
        assertTrue(imag1 != 0);
        assertTrue(real2 != 0);
        assertTrue(imag2 != 0);
        assertTrue(real1 != real2);
        assertTrue(imag1 != imag2);
    }
}

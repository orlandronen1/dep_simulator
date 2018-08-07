package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import field.Electrode;
import medium.Medium;
import particles.Particle;
import vector.Vector;

public class Simulation
{
    private List<Particle> particles;
    private List<Vector> initialPositions;
    private Medium medium;
    private double frequency;
    private double deltaTime;
    private Vector lowBounds;
    private Vector highBounds;
    private Electrode electrode;
    
    
    /**
     * Checks if a particle is within the bounds of the container. If not, moves the particle
     * so that it is.
     * 
     * @param particle  the Particle to check
     */
    void checkBounds(Particle particle)
    {
        /*
         * get position and radius of particle
         * use these to see if it is outside the bounds of the container
         * if yes, move it to be within the container
         * if no, do nothing
         */
    }
    
    /**
     * Starts the simulation
     */
    void run()
    {
        
    }
    
    /**
     * Pauses the simulation
     */
    void pause()
    {
        // stop simulation
    }
    
    /**
     * Stops the simulation and resets Particles to their initial positions 
     */
    void stop()
    {
        // stop simulation
        // Iterate over each particle and reset to initial position
        for (int i = 0; i < particles.size(); i++)
            particles.get(i).setPosition(initialPositions.get(i));
    }
    
    /**
     * Resets the simulation. Removes particles
     */
    void reset()
    {
        // Stop loop, if needed
        particles = new ArrayList<Particle>();
        initialPositions = new ArrayList<Vector>();
    }
    
    
    /**
     * 
     * @return a List of Particles currently in the simulation
     */
    List<Particle> getParticles()
    {
        return particles;
    }
    
    /**
     * Sets the Electrode type
     * 
     * @param config    the Electrode type to use
     */
    void setElectrode(Electrode elec)
    {
        electrode = elec;
    }
    
    /**
     * 
     * @return  the current Electrode type
     */
    Electrode getElectrode()
    {
        return electrode;
    }
    
    
    /**
     * Sets the lower bounds of the container
     * 
     * @param bounds    a Vector describing the lower bounds of the container
     */
    void setLowBounds(Vector bounds)
    {
        lowBounds = new Vector(bounds);
    }
    
    /**
     * 
     * @return  a Vector describing the lower bounds of the container
     */
    Vector getLowBounds()
    {
        return lowBounds;
    }
    
    
    /**
     * Sets the upper bounds of the container
     * 
     * @param bounds    a Vector describing the upper bounds of the container
     */
    void setHighBounds(Vector bounds)
    {
        highBounds = new Vector(bounds);
    }
    
    /**
     * 
     * @return  a Vector describing the upper bounds of the container
     */
    Vector getHighBounds()
    {
        return highBounds;
    }
    
    
    /**
     * Sets the Medium to be used 
     * 
     * @param med   the Medium to use
     */
    void setMedium(Medium med)
    {
        medium = med;
    }
    
    /**
     * 
     * @return  the current Medium
     */
    Medium getMedium()
    {
        return medium;
    }
    
    
    /**
     * Sets the time increment for simulation
     * 
     * @param time  the time increment to use when simulating
     */
    void setDeltaTime(double time)
    {
        deltaTime = time;
    }
    
    /**
     * 
     * @return  the time increment
     */
    double getDeltaTime()
    {
        return deltaTime;
    }
    
    
    /**
     * Sets the frequency
     * 
     * @param freq  the frequency to use
     */
    void setFrequency(double freq)
    {
        frequency = freq;
    }
    
    /**
     * 
     * @return  the current frequency
     */
    double getFrequency()
    {
        return frequency;
    }
}

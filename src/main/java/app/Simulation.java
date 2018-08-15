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
    public final static int DEFAULT_TIME_STEP = 50;
    
    private int thread_sleep = 50;
    
    private List<Particle> particles;
    private List<Vector> initialPositions;
    private Medium medium;
    private double frequency;
    private double deltaTime = DEFAULT_TIME_STEP;
    private Vector lowBounds;
    private Vector highBounds;
    private Electrode electrode;
    private Thread simulationLoop;
    
    private Runnable loop = new Runnable()
            {
                public void run()
                {
                    while(!Thread.interrupted())
                    {
                        try
                        {
                            Vector fDEP, fDrag, fBouyancy, fTotal;
                            double depCoefficient;
                            
                            for (Particle particle : particles)
                            {
                                fTotal = new Vector();
                                
                                // Calculate DEP force
                                /*
                                 * Fdep = 2 * Pi * particle radius ^ 3 * permittivity of a vaccuum * permittivity of particle *
                                 *          real part of Clausius-Mossotti factor * gradient of electric field squared
                                 */
                                depCoefficient = 2 * Math.PI * Math.pow(particle.getRadius(), 3) * Particle.VACUUM_PERMITTIVITY * particle.getPermittivity() * particle.getFcmReal();
                                fDEP = electrode.getGradientComponent(particle.getPosition());
                                fDEP.mult(depCoefficient);
                                
                                // Calculate buoyant force
                                fBouyancy = new Vector();
                                
                                // Calculate drag force
                                fDrag = new Vector();
                                
                                // Calculate Brownian motion force
                                // TODO brownian motion
                                
                                
                                // Sum forces
                                fTotal.add(fDEP);
                                fTotal.add(fDrag);
                                fTotal.add(fBouyancy);
                                fTotal.add(particle.getGravity());
                                
                                // Move particle
                                particle.move(fTotal, deltaTime);
                                
                                // Check bounds
                                checkBounds(particle);
                                
                                Thread.sleep(thread_sleep);   // TODO change this # to a var and decide on a value
                            }
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            };
    
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
    void play()
    {
        /* TODO collision detection
         * maybe would be better to keep another list of Ftotal's
         * first loop through each particle and get their Ftotal
         * then check for collisions
         * if there are any collisions, calculate the forces due to the collision and then add those to the respective Ftotal's
         * Finally, move() the particles
         * >would probably only check for 1 collision at a time, or it might just get stuck in checking collisions forever(?)
         */
        
        /*
         * Collision detection:
         * Partition 3D space into equal subspaces (based on largest particle?)
         * Each particle is in exactly one subspace, determined by it's position
         * Check collision w/ particles in current and adjacent subspaces
         *   If distance b/w particles is < sum of radii, particles have collided
         */
        
        simulationLoop = new Thread(loop, new String("simulationLoopThread"));
        simulationLoop.run();
    }
    
    /**
     * Pauses the simulation
     */
    void pause()
    {
        simulationLoop.interrupt();
    }
    
    /**
     * Stops the simulation and resets Particles to their initial positions 
     */
    void stop()
    {
        simulationLoop.interrupt();
        
        // Iterate over each particle and reset to initial position
        for (int i = 0; i < particles.size(); i++)
            particles.get(i).setPosition(initialPositions.get(i));
        // reset velocities too?
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
     * Adds a Particle to the simulation
     * 
     * @param particle  the Particle to add
     */
    void addParticle(Particle particle)
    {
        particles.add(particle);
        particle.calcFcm(medium, frequency);
        // TODO: algorithm for placing particles
        /*
         * check medium's level, place particles on that y coordinate
         * check list of initial positions for other particles
         *  ifEmpty() == true, start at center point
         *  else, go to last entry(?)
         */
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
        
        for (Particle particle : particles)
            particle.calcFcm(medium, frequency);
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
        
        for (Particle particle : particles)
            particle.calcFcm(medium, freq);
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

package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import field.Dipole;
import field.Electrode;
import medium.DeionizedWater;
import medium.Medium;
import particles.Particle;
import vector.Vector;

public class Simulation
{
    public final static int DEFAULT_TIME_STEP = 50;
    public final static double DEFAULT_BOUND = 150e-6;  // 150um
    
    private int thread_sleep = 50;
    
    private List<Particle> particles = new ArrayList<Particle>();
    private List<Vector> initialPositions = new ArrayList<Vector>();
    private Medium medium = new DeionizedWater(DEFAULT_BOUND / 2);
    private double frequency = 1000;
    private double deltaTime = DEFAULT_TIME_STEP;
    private Vector lowBounds = new Vector();
    private Vector highBounds = new Vector(DEFAULT_BOUND);
    private Electrode electrode = new Dipole(0, new Vector(Simulation.DEFAULT_BOUND / 5, 0, Simulation.DEFAULT_BOUND / 2), 
            new Vector(Simulation.DEFAULT_BOUND * 4 / 5, 0, Simulation.DEFAULT_BOUND / 2));
    private Thread simulationLoop;
    
    private Runnable loop = new Runnable()
            {
                public void run()
                {
                    while(!Thread.interrupted())
                    {
                        try
                        {
                            System.err.println("loop");
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
                                // TODO do the actual calculation
                                fBouyancy = new Vector(particle.getGravity());
                                fBouyancy.mult(-1);
                                
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
                                
                                Thread.sleep(thread_sleep);
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
        Vector position = particle.getPosition();
        double radius = particle.getRadius();
        double x, y, z;
        
        // Check x position
        if (position.getX() < lowBounds.getX())
            x = lowBounds.getX() + radius;
        else if (position.getX() > highBounds.getX())
            x = highBounds.getX() - radius;
        else
            x = position.getX();
        
        // Check y position
        if (position.getY() < lowBounds.getY())
            y = lowBounds.getY() + radius;
        else if (position.getY() > highBounds.getY())
            y = highBounds.getY() - radius;
        else
            y = position.getY();
        
        // Check z position
        if (position.getZ() < lowBounds.getZ())
            z = lowBounds.getZ() + radius;
        else if (position.getZ() > highBounds.getZ())
            z = highBounds.getZ() - radius;
        else
            z = position.getZ();
        
        // Check if near an electrode
        // Want to maintain some distance from electrodes so numbers don't become unreasonably large
        
        
        particle.setPosition(x, y, z);
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
        
        // If there are particles in the system, run
        if (!particles.isEmpty())
        {
            simulationLoop = new Thread(loop);
            simulationLoop.run();
        }
    }
    
    /**
     * Pauses the simulation
     */
    void pause()
    {
        // Interrupt loop if it has been initialized and isn't interrupted
        if (simulationLoop != null && !simulationLoop.isInterrupted())
            simulationLoop.interrupt();
    }
    
    /**
     * Stops the simulation and resets Particles to their initial positions 
     */
    void stop()
    {
        // Interrupt loop if it has been initialized and isn't interrupted
        if (simulationLoop != null && !simulationLoop.isInterrupted())
            simulationLoop.interrupt();
        
        // Iterate over each particle and reset to initial position
        if (!particles.isEmpty())
        {
            for (int i = 0; i < particles.size(); i++)
            {
                particles.get(i).setPosition(initialPositions.get(i));
                // TODO reset velocities too
            }
        }
    }
    
    /**
     * Resets the simulation. Removes all particles.
     */
    void reset()
    {
        // Interrupt loop if it has been initialized and isn't interrupted
        if (simulationLoop != null && !simulationLoop.isInterrupted())
            simulationLoop.interrupt();
        
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
        // TODO: algorithm for placing particles
        /*
         * check medium's level, place particles on that y coordinate
         * check list of initial positions for other particles
         *  ifEmpty() == true, start at center point
         *  else, go to last entry(?)
         */
        initialPositions.add(particle.getPosition());
        particles.add(particle);
        particle.calcFcm(medium, frequency);
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
    
    
    void setVoltage(double voltage)
    {
        electrode.setVoltage(voltage);
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

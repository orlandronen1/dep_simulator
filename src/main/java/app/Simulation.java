package app;

import java.util.ArrayList;
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
    
    private static int thread_sleep = 50;
    
    private static List<Particle> particles = new ArrayList<Particle>();
    private static List<Vector> initialPositions = new ArrayList<Vector>();
    
    private static Medium medium = new DeionizedWater(DEFAULT_BOUND / 2);
    private static Electrode electrode = new Dipole(0, new Vector(Simulation.DEFAULT_BOUND / 5, 0, Simulation.DEFAULT_BOUND / 2), 
            new Vector(Simulation.DEFAULT_BOUND * 4 / 5, 0, Simulation.DEFAULT_BOUND / 2));
    private static double frequency = 1000;
    private static double deltaTime = DEFAULT_TIME_STEP;
    
    private static Vector lowBounds = new Vector();
    private static Vector highBounds = new Vector(DEFAULT_BOUND);
    private static Vector center = new Vector(DEFAULT_BOUND / 3);
    
    private static boolean loop = false;
    private static Thread simulationLoop;
    
    /**
     * Checks if a particle is within the bounds of the container. If not, moves the particle
     * so that it is.
     * 
     * @param particle  the Particle to check
     */
    static void checkBounds(Particle particle)
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
    static void play()
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
        loop = true;
    }
    
    /**
     * Pauses the simulation
     */
    static void pause()
    {
        loop = false;
    }
    
    /**
     * Stops the simulation and resets Particles to their initial positions 
     */
    static void stop()
    {
        loop = false;
        
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
    static void reset()
    {
        // Interrupt loop if it has been initialized and isn't interrupted
//        if (simulationLoop != null && !simulationLoop.isInterrupted())
//            simulationLoop.interrupt();
        loop = false;
        particles = new ArrayList<Particle>();
        initialPositions = new ArrayList<Vector>();
    }
    
    /**
     * Adds a Particle to the simulation
     * 
     * @param particle  the Particle to add
     */
    static void addParticle(Particle particle)
    {
        // TODO: algorithm for placing particles
        /*
         * check medium's level, place particles on that y coordinate
         * check list of initial positions for other particles
         *  ifEmpty() == true, start at center point
         *  else, go to last entry(?)
         */
        initialPositions.add(center);
        particles.add(particle);
        
        particles.get(particles.size() - 1).setPosition(center);;
        particles.get(particles.size() - 1).calcFcm(medium, frequency);
    }
    
    /**
     * 
     * @return a List of Particles currently in the simulation
     */
    static List<Particle> getParticles()
    {
        return particles;
    }
    
    
    /**
     * Sets the Electrode type
     * 
     * @param config    the Electrode type to use
     */
    static void setElectrode(Electrode elec)
    {
        electrode = elec;
    }
    
    /**
     * 
     * @return  the current Electrode type
     */
    static Electrode getElectrode()
    {
        return electrode;
    }
    
    
    static void setVoltage(double voltage)
    {
        electrode.setVoltage(voltage);
    }
    
    /**
     * Sets the lower bounds of the container
     * 
     * @param bounds    a Vector describing the lower bounds of the container
     */
    static void setLowBounds(Vector bounds)
    {
        // Low bounds must be lower than high bounds
        if (bounds.getX() < highBounds.getX() && bounds.getY() < highBounds.getY() && bounds.getZ() < highBounds.getZ())
            lowBounds = new Vector(bounds);
        else
            throw new IllegalArgumentException("Low bounds not lower than high bounds");
        
        // Recalculate center point
        center = new Vector(highBounds);
        center.add(lowBounds);
        center.mult(0.5);
    }
    
    /**
     * 
     * @return  a Vector describing the lower bounds of the container
     */
    static Vector getLowBounds()
    {
        return lowBounds;
    }
    
    
    /**
     * Sets the upper bounds of the container
     * 
     * @param bounds    a Vector describing the upper bounds of the container
     */
    static void setHighBounds(Vector bounds)
    {
        // High bounds must be higher than low bounds
        if (bounds.getX() > lowBounds.getX() && bounds.getY() > lowBounds.getY() && bounds.getZ() > lowBounds.getZ())
            highBounds = new Vector(bounds);
        else
            throw new IllegalArgumentException("High bounds not higher than low bounds");
        
        // Recalculate center point
        center = new Vector(highBounds);
        center.add(lowBounds);
        center.mult(0.5);
    }
    
    /**
     * 
     * @return  a Vector describing the upper bounds of the container
     */
    static Vector getHighBounds()
    {
        return highBounds;
    }
    
    
    /**
     * Sets the Medium to be used 
     * 
     * @param med   the Medium to use
     */
    static void setMedium(Medium med)
    {
        medium = med;
        
        for (Particle particle : particles)
            particle.calcFcm(medium, frequency);
    }
    
    /**
     * 
     * @return  the current Medium
     */
    static Medium getMedium()
    {
        return medium;
    }
    
    
    /**
     * Sets the time increment for simulation
     * 
     * @param time  the time increment to use when simulating
     */
    static void setDeltaTime(double time)
    {
        deltaTime = time;
    }
    
    /**
     * 
     * @return  the time increment
     */
    static double getDeltaTime()
    {
        return deltaTime;
    }
    
    
    /**
     * Sets the frequency
     * 
     * @param freq  the frequency to use
     */
    static void setFrequency(double freq)
    {
        frequency = freq;
        
        for (Particle particle : particles)
            particle.calcFcm(medium, freq);
    }
    
    /**
     * 
     * @return  the current frequency
     */
    static double getFrequency()
    {
        return frequency;
    }
    
    
    public static void main()
    {
        simulationLoop = new Thread(new Runnable()
        {
            public void run()
            {
                while(true)
                {
                    try
                    {
                        if (loop)
                        {
                            Vector fDEP, fDrag, fBouyancy, fTotal;
                            double depCoefficient;
                            
                            for (Particle particle : particles)
                            {
                                fTotal = new Vector();
                                
                                // Calculate DEP force
                                /*
                                 * Fdep = 2 * Pi * particle radius ^ 3 * permittivity of a vaccuum * permittivity of medium *
                                 *          real part of Clausius-Mossotti factor * gradient of electric field squared
                                 */
                                depCoefficient = 2 * Math.PI * Math.pow(particle.getRadius(), 3) * Particle.VACUUM_PERMITTIVITY * medium.getPermittivity() * particle.getFcmReal();
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
                                System.err.println(particle + "   " + particle.getPosition()); // TODO remove
                            }
                        }
                        
                        Thread.sleep(thread_sleep);
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        simulationLoop.setDaemon(true);
        simulationLoop.start();
    }
}

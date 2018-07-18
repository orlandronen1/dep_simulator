/*
 * https://hypertextbook.com/facts/2007/AllenMa.shtml
 * https://en.wikipedia.org/wiki/Relative_permittivity
 */
package medium;

/**
 * The permittivity, conductivity, and density constants of liquid distilled water at 20 degrees Celsius
 * 
 * @author Ronen Orland
 */
public class DistilledWater extends Medium
{
    public final static double permittivity = 80.1;    // Relative permittivity of water at 20C
    public final static double conductivity = 5.5 * Math.pow(10, -6);  // Conductivity of water at 20C
    public final static double density = 998.2;     // Density of water at 20C in kg/m^3
    public final double level;                         // Water level in m
    
    
    public DistilledWater()
    {
        level = 0;
    }
    
    public DistilledWater(double level)
    {
        this.level = level;
    }
    
    /**
     * 
     * @return  the permittivity of the medium
     */
    public double getPermittivity()
    {
        return permittivity;        
    }

    /**
     * 
     * @return  the conductivity of the medium
     */
    public double getConductivity()
    {
        return conductivity;
    }
    
    /**
     * 
     * @return  the density of the medium
     */
    public double getDensity()
    {
        return density;
    }
    
    /**
     * 
     * @return the level of the medium
     */
    public double getLevel()
    {
        return level;
    }
}

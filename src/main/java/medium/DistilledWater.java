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
public class DistilledWater implements Medium
{
    public final static double permittivity = 80.1;     // Relative permittivity of water at 20C
    public final static double conductivity = 5.5 * Math.pow(10, -6);  // Conductivity of water at 20C
    public final static double density = 998.2;     // Density of water at 20C in kg/m^3
    public double level;                            // Water level in m
    
    
    public DistilledWater()
    {
        level = 0;
    }
    
    public DistilledWater(double level)
    {
        this.level = level;
    }


    public double getPermittivity()
    {
        return permittivity;        
    }

    public double getConductivity()
    {
        return conductivity;
    }

    public double getDensity()
    {
        return density;
    }
    
    public double getLevel()
    {
        return level;
    }
    
    public void setLevel(double level)
    {
        this.level = level;
    }
}

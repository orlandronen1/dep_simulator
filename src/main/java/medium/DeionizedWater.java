package medium;

/**
 * The permittivity, conductivity, and density constants of liquid deionized water
 * 
 * @author Ronen Orland
 *
 */
public class DeionizedWater implements Medium
{
    public final static double permittivity = 78;
    public final static double conductivity = 2 * Math.pow(10, -4);
    public final static double density = 998.2; // Currently just copied from distilled water
    public double level;
    
    
    public DeionizedWater()
    {
        level = 0;
    }
    
    public DeionizedWater(double level)
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

/*
 * https://hypertextbook.com/facts/2007/AllenMa.shtml
 * https://en.wikipedia.org/wiki/Relative_permittivity
 */
package medium;

/**
 * The permittivity, conductivity, and density constants of liquid tap water at 20 degrees Celsius
 * 
 * @author Ronen Orland
 */
public class Water implements Medium
{
	public final static double permittivity = 80.1;    // Relative permittivity of water at 20C
	public final static double conductivity = 0.0275;  // Conductivity of water at 20C
	public final static double density = 998.23;       // Density of water at 20C in kg/m^3
	public final double level;                         // Water level in m
	
	
	public Water()
	{
	    level = 0;
	}
	
	public Water(double level)
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
}

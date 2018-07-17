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
}

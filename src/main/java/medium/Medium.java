package medium;

/**
 * Abstract class representing a generic fluid medium for particles to be suspended within
 * for dielectrophoresis. Has constants for permittivity, conductivity, and density.
 * 
 * @author Ronen Orland
 */
public abstract class Medium
{
	
	public final static double permittivity = 1;   // Relative permittivity of the medium
	public final static double conductivity = 1;   // Conductivity of the medium
	public final static double density = 1;        // Density of the medium in kg/m^3
	
	
	public Medium()
	{
		
	}
	
	/**
	 * 
	 * @return	the permittivity of the medium
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
	 * @return	the density of the medium
	 */
	public double getDensity()
	{
		return density;
	}
}

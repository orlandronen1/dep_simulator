package medium;

/**
 * Interface to get information pertaining to a fluid medium
 * 
 * @author Ronen Orland
 */
public interface Medium
{

	/**
	 * 
	 * @return	the permittivity of the medium
	 */
	public double getPermittivity();

    /**
     * 
     * @return  the conductivity of the medium
     */
    public double getConductivity();
    
	/**
	 * 
	 * @return	the density of the medium
	 */
	public double getDensity();
	
	/**
	 * 
	 * @return the level of the medium
	 */
	public double getLevel();
}

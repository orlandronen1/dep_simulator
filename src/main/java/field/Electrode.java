/*
 * https://en.wikipedia.org/wiki/Electric_field
 * http://hyperphysics.phy-astr.gsu.edu/hbase/electric/elefie.html#c1
 */
package field;

import vector.Vector;

/**
 * Electrode interface.
 * 
 * @author Ronen Orland
 */
public interface Electrode
{
    double k = 8987552000.0;        // Coulomb's constant
    double DEFAULT_CHARGE = 1e-6;   // 1uC
    
    /**
     * Returns a Vector describing the electric field at a coordinate
     * 
     * @return Vector describing the electric field at a coordinate
     */
    public Vector getField(Vector coord);
    
    /**
     * Returns the gradient of the electric field squared rms value. This ends up being
     * ((-4 * k^2 * Q^2)/(x^2 + y^2 + z^2)^3) * 0.7. The coordinate values here are the 
     * differences between the position of the desired coordinate and the position of
     * the charge.
     * 
     * @param coord     the coordinate Vector to calculate the gradient for
     * @return          gradient of the electric field squared rms value
     */
    public Vector getGradientComponent(Vector coord);
}

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
    /**
     * Returns a Vector describing the electric field at a coordinate
     * 
     * @return Vector describing the electric field at a coordinate
     */
    public Vector getField(Vector coord);
}

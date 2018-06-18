package field;

import field.Electrode;
import vector.Vector;

/**
 * Represents a simple point charge creating an electric field
 * 
 * @author Ronen Orland
 */
public class PointCharge implements Electrode
{
    final static double k = 8987552000.0;   // Coulomb's constant 
    private double charge;                  // Charge in C
    private Vector position;                // Position of the PointCharge
    
    /**
     * Creates a new PointCharge with a charge of 0C and position of (0,0,0)
     */
    public PointCharge()
    {
        this(0, new Vector());
    }
    
    /**
     * Creates a new PointCharge with a specified charge and position of (0,0,0)
     * 
     * @param charge    the charge of the PointCharge in C
     */
    public PointCharge(double charge)
    {
        this(charge, new Vector());
    }
    
    /**
     * Creates a new PointCharge with a specified position
     * 
     * @param pos   position Vector
     */
    public PointCharge(Vector pos)
    {
        this(0, pos);
    }
    
    /**
     * Creates a new PointCharge with a specified charge and position
     * 
     * @param charge    charge of the PointCharge in C
     * @param pos       position Vector
     */
    public PointCharge(double charge, Vector pos)
    {
        this.charge = charge;
        position = new Vector(pos);
    }
    
    /**
     * Copies a different PointCharge
     * 
     * @param copy  the PointCharge to copy
     */
    public PointCharge(PointCharge copy)
    {
        charge = copy.getCharge();
        position = new Vector(copy.getPosition());
    }
    
    @Override
    public Vector getField(Vector coord)
    {
        // If on the point charge, field is infinite
        if (coord.equals(position))
        {
            if (charge == 0)        // Zero charge
                return new Vector();
            else if (charge > 0)    // Positive charge
                return new Vector(Double.POSITIVE_INFINITY);
            else                    // Negative charge
                return new Vector(Double.NEGATIVE_INFINITY);
        }
        
        double radius = position.distance(coord);   // Radius from the charge
        
        double field = k * charge / (radius * radius);  // Calculate field
        
        Vector ret = new Vector(coord);
        ret.sub(position);      // Get vector pointing from point charge to given coordinate
        ret = ret.unit();       // Get unit vector
        ret.mult(field);        // Multiply unit vector by field
        
        return ret;
    }
    
    /**
     * Returns a Vector describing the electric field at a coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     * @return  Vector describing the field at a coordinate
     */
    public Vector getField(double x, double y, double z)
    {
        return this.getField(new Vector(x,y,z));
    }
    
    /**
     * Changes the charge of the PointCharge
     * 
     * @param charge    the new charge
     */
    public void setCharge(double charge)
    {
        this.charge = charge;
    }
    
    /**
     * 
     * @return the charge of the PointCharge
     */
    public double getCharge()
    {
        return charge;
    }
    
    /**
     * Updates the position of the PointCharge
     * 
     * @param pos   the new position Vector
     */
    public void setPosition(Vector pos)
    {
        position = new Vector(pos);
    }
    
    /**
     * 
     * @return  the position Vector of the pointCharge
     */
    public Vector getPosition()
    {
        return position;
    }
    
    /**
     * 
     * @return true if the charge is greater than or equal to 0,
     *         false if not
     */
    public boolean isPositive()
    {
        return charge >= 0;
    }
}

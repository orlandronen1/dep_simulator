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
    private double charge;      // Charge in C
    private Vector position;    // Position of the PointCharge
    
    
    /**
     * Creates a new PointCharge with a charge of 0C and position of (0,0,0)
     */
    public PointCharge()
    {
        this(DEFAULT_CHARGE, new Vector());
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
        this(DEFAULT_CHARGE, pos);
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
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     * @return  Vector describing the field at a coordinate
     */
    public Vector getField(double x, double y, double z)
    {
        return this.getField(new Vector(x,y,z));
    }
    
    @Override
    public Vector getGradientComponent(Vector coord)
    {
        if (coord.equals(new Vector()))
            return new Vector();
        
        // Subtract position of charge from coordinate
        Vector component = new Vector(coord);
        component.sub(position);
        
        double mult = -4 * 0.7 * (k * k) * (charge * charge);   // -4(k^2)(Q^2)*0.7     Numerator
        double div = Math.pow(coord.getX(), 2) + Math.pow(coord.getY(), 2) + Math.pow(coord.getZ(), 2);     // (x^2 + y^2 + z^2)^3
        div = Math.pow(div, 3);     // Raise to power of 3      Denominator
        mult /= div;                // Divide to get the coefficient
        
        component.mult(mult);       // Multiply the coefficient to the vector
        return component;
    }
    
    /**
     * Returns the gradient of the electric field squared rms value. This ends up being
     * ((-4 * k^2 * Q^2)/(x^2 + y^2 + z^2)^3) * 0.7. The coordinate values here are the 
     * differences between the position of the desired coordinate and the position of
     * the charge.
     * 
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param z     z-coordinate
     * @return      gradient of the electric field squared rms value
     */
    public Vector getGradientComponent(double x, double y, double z)
    {
        return this.getGradientComponent(new Vector(x,y,z));
    }
    
    public void setVoltage(double voltage)
    {
        double newCharge = voltage * MIN_RADIUS / k;
        this.setCharge(newCharge);
    }
   
    /**
     * Checks if the PointCharge is equal to another PointCharge
     * 
     * @param check     the PointCharge to check against
     * @return          true if the PointCharges are the same,
     *                  false otherwise
     */
    public boolean equals(PointCharge check)
    {
        if (charge != check.getCharge())
            return false;
        
        if (!position.equals(check.getPosition()))
            return false;
        
        return true;
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

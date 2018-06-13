package field;

import vector.Vector;

/**
 * Represents a dipole, a pair of opposite charges of equal magnitude
 * 
 * @author home
 */
public class Dipole implements Electrode
{

    private PointCharge positive;   // The positive charge
    private PointCharge negative;   // The negative charge
    private double distance;        // The distance between the charges
    
    
    /**
     * Default constructor, initializes charge magnitudes and positions to 0 and the origin
     */
    public Dipole()
    {
        this(new PointCharge(), new PointCharge());
    }
    
    /**
     * Constructor that takes a charge magnitude and the position Vectors for the two charges
     * 
     * @param charge    the magnitude of the charge on each PointCharge
     * @param pos       the position Vector for the positive charge
     * @param neg       the position Vector for the negative charge
     */
    public Dipole(double charge, Vector pos, Vector neg)
    {
        this(new PointCharge(charge, pos), new PointCharge(-charge, neg));
    }
    
    /**
     * Constructor that takes two PointCharges. Fails if charge magnitudes are not equal and opposite.
     * 
     * @param pos
     * @param neg
     */
    public Dipole(PointCharge pos, PointCharge neg)
    {
        if (pos.getCharge() != -neg.getCharge())
            throw new IllegalArgumentException("Charges are not equal and opposite");
        
        positive = new PointCharge(pos);
        negative = new PointCharge(neg);
        
        distance = pos.getPosition().distance(neg.getPosition());   // Calculate the distance between the charges
    }
    

    @Override
    public Vector getField(Vector coord)
    {
        // Field of a dipole is just the vector sum of the fields from each point charge
        Vector field = positive.getField(coord);
        field.add(negative.getField(coord));
        
        return field;
    }

    /**
     * 
     * @return  the magnitude of the charge of the dipole
     */
    public double getCharge()
    {
        return positive.getCharge();
    }
    
    /**
     * Sets the charges of the dipole particles to be of the given magnitude
     * 
     * @param charge    magnitude of the charge to set the dipole to
     */
    public void setCharge(double charge)
    {
        positive.setCharge(charge);
        negative.setCharge(-charge);
    }
    
    /**
     * 
     * @return  the distance between the dipole charges
     */
    public double getDistance()
    {
        return distance;
    }
    
    /**
     * 
     * @return  the position Vector of the positive charge
     */
    public Vector getPositivePosition()
    {
        return positive.getPosition();
    }
    
    /**
     * Sets the position of the positive charge
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param z     z-coordinate
     */
    public void setPositivePosition(double x, double y, double z)
    {
        setPositivePosition(new Vector(x,y,z));
    }
    
    /**
     * Sets the position of the positive charge
     * 
     * @param pos   the new position Vector of the positive charge
     */
    public void setPositivePosition(Vector pos)
    {
        positive.setPosition(pos);
        
        distance = positive.getPosition().distance(negative.getPosition());   // Calculate the distance between the charges
    }
    
    /**
     * 
     * @return  the position Vector of the negative charge
     */
    public Vector getNegativePosition()
    {
        return negative.getPosition();
    }
    
    /**
     * Sets the position of the negative charge
     * @param x     x-coordinate
     * @param y     y-coordinate
     * @param z     z-coordinate
     */
    public void setNegativePosition(double x, double y, double z)
    {
        setNegativePosition(new Vector(x,y,z));
    }
    
    /**
     * Sets the position of the negative charge
     * 
     * @param pos   the new position Vector of the negative charge
     */
    public void setNegativePosition(Vector pos)
    {
        negative.setPosition(pos);
        
        distance = positive.getPosition().distance(negative.getPosition());   // Calculate the distance between the charges
    }
}

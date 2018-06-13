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
        this(new PointCharge(charge, pos), new PointCharge(charge * -1,neg));
    }
    
    /**
     * Constructor that takes two PointCharges. Fails if charge magnitudes are not equal and opposite.
     * 
     * @param pos
     * @param neg
     */
    public Dipole(PointCharge pos, PointCharge neg)
    {
        if (pos.getCharge() != neg.getCharge() * -1)
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

}

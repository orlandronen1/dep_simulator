package field;

import vector.Vector;

/**
 * Represents a quadrupole, or four charged particles, 2 positive and 2 negative, 
 * of equal charge magnitudes.
 * 
 * @author Ronen Orland
 *
 */
public class Quadrupole implements Electrode
{
    private PointCharge positive1;  // The first positive charge
    private PointCharge positive2;  // The second positive charge
    private PointCharge negative1;  // The first negative charge
    private PointCharge negative2;  // The second negative charge

    
    /**
     * Default constructor. Sets charges to be of magnitude 1uC. Sets positions of 
     * the charges to be the corners of a square from (0,0,0) to (1,1,0).
     */
    public Quadrupole()
    {
        positive1 = new PointCharge(DEFAULT_CHARGE, new Vector(0,0,0));
        positive2 = new PointCharge(DEFAULT_CHARGE, new Vector(1,1,0));
        negative1 = new PointCharge(-DEFAULT_CHARGE, new Vector(1,0,0));
        negative2 = new PointCharge(-DEFAULT_CHARGE, new Vector(0,1,0));
    }
    
    /**
     * Constructor that takes four PointCharges to create the individual charges in the Quadrupole.
     * 
     * @param   pos1    the first positive PointCharge
     * @param   pos2    the second positive PointCharge
     * @param   neg1    the first negative PointCharge
     * @param   neg2    the second negative PointCharge
     * @throws IllegalArgumentException     if all charges are not equal magnitude or if signs are wrong
     */
    public Quadrupole(PointCharge pos1, PointCharge pos2, PointCharge neg1, PointCharge neg2)
    {
        // Check if all charges are equal magnitude and that signs are correct
        if (pos1.getCharge() == pos2.getCharge() && neg1.getCharge() == neg2.getCharge() 
                && pos1.getCharge() == -neg1.getCharge() && pos2.getCharge() == -neg2.getCharge()
                && pos1.getCharge() >= 0)
        {
            positive1 = new PointCharge(pos1);
            positive2 = new PointCharge(pos2);
            negative1 = new PointCharge(neg1);
            negative2 = new PointCharge(neg2);
        }
        else throw new IllegalArgumentException("Invalid charges, either unequal magnitudes or bad signs");
    }
    
    /**
     * Constructor that takes two Dipoles to create the charges in the Quadrupole
     * 
     * @param  one   the first Dipole
     * @param  two   the second Dipole
     * @throws IllegalArgumentException     if charges are not equal magnitude between the Dipoles
     */
    public Quadrupole(Dipole one, Dipole two)
    {
        // Check if charges are equal magnitude
        if (one.getPositive().getCharge() == two.getPositive().getCharge())
        {
            positive1 = new PointCharge(one.getPositive());
            positive2 = new PointCharge(two.getPositive());
            negative1 = new PointCharge(one.getNegative());
            negative2 = new PointCharge(two.getNegative());
        }
        else throw new IllegalArgumentException("Invalid charges, magnitudes unequal");
    }

    @Override
    public Vector getField(Vector coord)
    {
        Vector field = positive1.getField(coord);
        field.add(positive2.getField(coord));
        field.add(negative1.getField(coord));
        field.add(negative2.getField(coord));
        
        return field;
    }
    
    public Vector getField(double x, double y, double z)
    {
        return this.getField(new Vector(x,y,z));
    }
    
    @Override
    public Vector getGradientComponent(Vector coord)
    {
        Vector componentSum = positive1.getGradientComponent(coord);
        componentSum.add(positive2.getGradientComponent(coord));
        componentSum.add(negative1.getGradientComponent(coord));
        componentSum.add(negative2.getGradientComponent(coord));
        
        return componentSum;
    }
    
    public void setVoltage(double voltage)
    {
        // TODO calculate what needs
    }
   
    /**
     * 
     * @return an array of the PointCharges in the Quadrupole. The charges are in the following order:
     * positive1, positive2, negative1, negative2.
     */
    public PointCharge[] getPointCharges()
    {
        return new PointCharge[] {positive1, positive2, negative1, negative2};
    }
    
    /**
     * 
     * @return an array of the positions of the PointCharges in the Quadrupole. The positions are in the 
     * order of positive1, positive2, negative1, negative2.
     */
    public Vector[] getPositions()
    {
        return new Vector[] {positive1.getPosition(), positive2.getPosition(), negative1.getPosition(), negative2.getPosition()};
    }
    
    /**
     * 
     * @return  the magnitude of charge on each PointCharge in the Quadrupole
     */
    public double getCharge()
    {
        return positive1.getCharge();
    }
}

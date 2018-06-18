package unit;

import static org.junit.Assert.*;
import org.junit.Test;
import vector.Vector;
import field.PointCharge;
import field.Dipole;
import field.Quadrupole;

public class QuadrupoleTest
{
    @Test
    public void constructorTest()
    {
        Quadrupole quad;
        PointCharge pos1, pos2, neg1, neg2;
        PointCharge[] charges;
        Vector[] positions;
        Dipole dipole1, dipole2;
        
        // Default constructor
        quad = new Quadrupole();
        assertTrue(quad.getCharge() == Quadrupole.DEFAULT_CHARGE);
        positions = quad.getPositions();
        assertTrue(positions[0].equals(new Vector(0,0,0)));
        assertTrue(positions[1].equals(new Vector(1,1,0)));
        assertTrue(positions[2].equals(new Vector(1,0,0)));
        assertTrue(positions[3].equals(new Vector(0,1,0)));
        
        // 4 PointCharge constructor, equal charges
        pos1 = new PointCharge(1, new Vector(0,0,0));
        pos2 = new PointCharge(1, new Vector(1,1,1));
        neg1 = new PointCharge(-1, new Vector(2,2,2));
        neg2 = new PointCharge(-1, new Vector(3,3,3));
        quad = new Quadrupole(pos1, pos2, neg1, neg2);
        charges = quad.getPointCharges();
        assertTrue(charges[0].equals(pos1));
        assertTrue(charges[1].equals(pos2));
        assertTrue(charges[2].equals(neg1));
        assertTrue(charges[3].equals(neg2));
        
        // 4 PointCharge constructor, unequal charges
        pos2.setCharge(2);
        try
        {
            quad = new Quadrupole(pos1, pos2, neg1, neg2);
        }
        catch (IllegalArgumentException e)
        {
            quad = null;
        }
        pos2.setCharge(1);
        neg1.setCharge(-4);
        try
        {
            quad = new Quadrupole(pos1, pos2, neg1, neg2);
        }
        catch (IllegalArgumentException e)
        {
            quad = null;
        }
        
        // 4 PointCharge constructor, charges with wrong signs
        neg1.setCharge(1);
        try
        {
            quad = new Quadrupole(pos1, pos2, neg1, neg2);
        }
        catch (IllegalArgumentException e)
        {
            quad = null;
        }
        neg1.setCharge(-1);
        pos1.setCharge(-1);
        try
        {
            quad = new Quadrupole(pos1, pos2, neg1, neg2);
        }
        catch (IllegalArgumentException e)
        {

        }        
        
        // 2 Dipole constructor, equal charges
        pos1 = new PointCharge(1, new Vector(0,0,0));
        pos2 = new PointCharge(1, new Vector(1,1,1));
        neg1 = new PointCharge(-1, new Vector(2,2,2));
        neg2 = new PointCharge(-1, new Vector(3,3,3));
        dipole1 = new Dipole(pos1, neg1);
        dipole2 = new Dipole(pos2, neg2);
        quad = new Quadrupole(dipole1, dipole2);
        charges = quad.getPointCharges();
        assertTrue(charges[0].equals(pos1));
        assertTrue(charges[1].equals(pos2));
        assertTrue(charges[2].equals(neg1));
        assertTrue(charges[3].equals(neg2));
        
        // 2 Dipole constructor, unequal charges
        dipole1.setCharge(2);
        try
        {
            quad = new Quadrupole(dipole1, dipole2);
        }
        catch(IllegalArgumentException e)
        {

        }
    }
    
    @Test
    public void fieldTest()
    {
        Quadrupole quad = new Quadrupole();
        Vector coord, field;
        
        // In center of charges
        coord = new Vector(0.5, 0.5, 0);
        field = quad.getField(coord);
        assertTrue(field.equals(new Vector(0,0,0)));
        
        // Between dipole (X)
        coord = new Vector(0.5,0,0);
        field = quad.getField(coord);
        assertTrue(field.getX() > 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        
        // Between dipole (Y)
        coord = new Vector(0,0.5,0);
        field = quad.getField(coord);
        assertTrue(field.getX() == 0);
        assertTrue(field.getY() > 0);
        assertTrue(field.getZ() == 0);
    }
}

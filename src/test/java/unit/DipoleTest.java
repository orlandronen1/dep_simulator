package unit;

import static org.junit.Assert.*;
import org.junit.Test;
import vector.Vector;
import field.PointCharge;
import field.Dipole;

public class DipoleTest
{
    @Test
    public void constructorTest()
    {
        Dipole dipole;
        PointCharge pos, neg;
        Vector posVec, negVec;
        
        // Default constructor
        dipole = new Dipole();
        pos = dipole.getPositive();
        neg = dipole.getNegative();
        assertTrue(dipole.getCharge() == Dipole.DEFAULT_CHARGE);
        assertTrue(pos.getCharge() == Dipole.DEFAULT_CHARGE);
        assertTrue(neg.getCharge() == -Dipole.DEFAULT_CHARGE);
        posVec = pos.getPosition();
        negVec = neg.getPosition();
        assertTrue(posVec.equals(new Vector()));
        assertTrue(negVec.equals(new Vector(1,0,0)));
        
        // Constructor w/ charge magnitude and position Vectors
        dipole = new Dipole(1, new Vector(1,1,1), new Vector(2,2,2));
        pos = dipole.getPositive();
        neg = dipole.getNegative();
        assertTrue(dipole.getCharge() == 1);
        assertTrue(pos.getCharge() == 1);
        assertTrue(neg.getCharge() == -1);
        posVec = pos.getPosition();
        negVec = neg.getPosition();
        assertTrue(posVec.equals(new Vector(1,1,1)));
        assertTrue(negVec.equals(new Vector(2,2,2)));
        
        // Constructor w/ 2 PointCharges
        pos = new PointCharge(1, new Vector(1,1,1));
        neg = new PointCharge(-1, new Vector(2,2,2));
        dipole = new Dipole(pos, neg);
        assertTrue(dipole.getCharge() == pos.getCharge());
        assertTrue(dipole.getCharge() == -neg.getCharge());
        posVec = pos.getPosition();
        negVec = neg.getPosition();
        assertTrue(posVec.equals(new Vector(1,1,1)));
        assertTrue(negVec.equals(new Vector(2,2,2)));
        
        // Constructor w/ 2 invalid PointCharges
        pos = new PointCharge(1);
        neg = new PointCharge(1);
        try
        {
            dipole = new Dipole(pos, neg);
        }
        catch(IllegalArgumentException e)
        {
            
        }
    }
    
    @Test 
    public void fieldTest()
    {
        // Default Dipole, position Vectors are (0,0,0) and (1,0,0)
        Dipole dipole = new Dipole();
        Vector coord, field;
        PointCharge pos, neg;
        
        // Field at a point next to the positive charge
        coord = new Vector(-1,0,0);
        field = dipole.getField(coord);
        assertTrue(field.getX() < 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        
        // Field at a point next to the negative charge
        coord = new Vector(2,0,0);
        field = dipole.getField(coord);
        assertTrue(field.getX() < 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        
        // Field exactly between the charges
        coord = new Vector(0.5,0,0);
        field = dipole.getField(coord);
        assertTrue(field.getX() > 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        pos = dipole.getPositive();
        neg = dipole.getNegative();
        assertTrue(pos.getField(coord).equals(neg.getField(coord)));
        
        // Field at some point between the charges
        coord = new Vector(0.25, 0.04, 1);
        field = dipole.getField(coord);
        assertTrue(field.getX() > 0);
        assertTrue(field.getY() > 0);
        assertTrue(field.getZ() > 0);
    }
}

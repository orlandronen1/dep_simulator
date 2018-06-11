package unit;

import static org.junit.Assert.*;
import org.junit.Test;
import vector.Vector;
import field.PointCharge;

public class PointChargeTest
{

    @Test
    public void constructorTest()
    {
        PointCharge point;
        Vector check;
        
        // Default constructor
        point = new PointCharge();
        check = new Vector(0,0,0);
        assertTrue(point.getCharge() == 0);
        assertTrue(point.getPosition().equals(check));
        
        // Constructor w/ charge input
        point = new PointCharge(1);
        assertTrue(point.getCharge() == 1.0);
        assertTrue(point.getPosition().equals(check));
        
        // Constructor w/ position input
        point = new PointCharge(new Vector(1,1,1));
        check.setAll(1, 1, 1);
        assertTrue(point.getCharge() == 0);
        assertTrue(point.getPosition().equals(check));
        
        // Constructor w/ both inputs
        point = new PointCharge(2, new Vector(2,2,2));
        check.setAll(2,2,2);
        assertTrue(point.getCharge() == 2.0);
        assertTrue(point.getPosition().equals(check));
    }
    
    @Test
    public void fieldTest()
    {
        PointCharge charge = new PointCharge();
        Vector field;
        
        // Zero charge
        field = charge.getField(0,0,0);
        assertTrue(field.getX() == 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        field = charge.getField(1,1,1);
        assertTrue(field.getX() == 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        field = charge.getField(-1,-1,-1);
        assertTrue(field.getX() == 0);
        assertTrue(field.getY() == 0);
        assertTrue(field.getZ() == 0);
        
        // Positive charge
        charge.setCharge(1);
        field = charge.getField(1,1,1);
        assertTrue(field.getX() > 0);
        assertTrue(field.getY() > 0);
        assertTrue(field.getZ() > 0);
        field = charge.getField(-1,-1,-1);
        assertTrue(field.getX() < 0);
        assertTrue(field.getY() < 0);
        assertTrue(field.getZ() < 0);
        field = charge.getField(0,0,0);
        assertTrue(field.getX() == Double.POSITIVE_INFINITY);
        assertTrue(field.getY() == Double.POSITIVE_INFINITY);
        assertTrue(field.getZ() == Double.POSITIVE_INFINITY);
        
        // Negative charge
        charge.setCharge(-1);
        field = charge.getField(1,1,1);
        assertTrue(field.getX() < 0);
        assertTrue(field.getY() < 0);
        assertTrue(field.getZ() < 0);
        field = charge.getField(-1,-1,-1);
        assertTrue(field.getX() > 0);
        assertTrue(field.getY() > 0);
        assertTrue(field.getZ() > 0);
        field = charge.getField(0,0,0);
        assertTrue(field.getX() == Double.NEGATIVE_INFINITY);
        assertTrue(field.getY() == Double.NEGATIVE_INFINITY);
        assertTrue(field.getZ() == Double.NEGATIVE_INFINITY);
    }
}

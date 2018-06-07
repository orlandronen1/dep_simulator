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
        
    }
}

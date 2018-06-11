package unit;

import static org.junit.Assert.*;
import org.junit.Test;
import vector.Vector;

/**
 * Unit testing for Vector
 * 
 * @author Ronen Orland
 */
public class VectorTest
{
    
    @Test
    public void constructorTest()
    {
        // Default constructor
        Vector vector = new Vector();
        assertTrue(vector.getX() == 0);
        assertTrue(vector.getY() == 0);
        assertTrue(vector.getZ() == 0);
        
        // Constructor with components all equal
        vector = new Vector(1.0);
        assertTrue(vector.getX() == 1.0);
        assertTrue(vector.getY() == 1.0);
        assertTrue(vector.getZ() == 1.0);
        assertTrue(vector.getX() == vector.getY() && vector.getX() == vector.getZ());
        
        // Constructor with doubles
        double one = 1.0;
        double two = 2.0;
        double three = 3.0;
        vector = new Vector(one, two, three);
        assertTrue(vector.getX() == one);
        assertTrue(vector.getY() == two);
        assertTrue(vector.getZ() == three);
        
        // Constructor from another vector
        Vector duplicate = new Vector(vector);
        assertTrue(duplicate.getX() == one);
        assertTrue(duplicate.getY() == two);
        assertTrue(duplicate.getZ() == three);
    }
    
    @Test
    public void changeComponents()
    {
        Vector vector = new Vector();
        double x = 5.55;
        double y = 6.66;
        double z = 7.77;
        double all = 1.11;
        
        // Change x
        assertTrue(vector.getX() == 0);
        vector.setX(x);
        assertTrue(vector.getX() == x);
        
        // Change y
        assertTrue(vector.getY() == 0);
        vector.setY(y);
        assertTrue(vector.getY() == y);
        
        // Change z
        assertTrue(vector.getZ() == 0);
        vector.setZ(z);
        assertTrue(vector.getZ() == z);
        
        // Change all components
        vector.setAll(all, all, all);
        assertTrue(vector.getX() == all);
        assertTrue(vector.getY() == all);
        assertTrue(vector.getZ() == all);
    }
    
    @Test
    public void addTest()
    {
        // Add with vectors
        Vector one = new Vector(1.0, 1.0, 1.0);
        Vector two = new Vector(2.0, 2.0, 2.0);
        Vector sum = new Vector();
        
        // Add first vector to 0 vector
        sum.add(one);
        assertTrue(sum.getX() == one.getX());
        assertTrue(sum.getY() == one.getY());
        assertTrue(sum.getZ() == one.getZ());
        
        // Add second vector to first sum
        sum.add(two);
        assertTrue(sum.getX() == 3.0);
        assertTrue(sum.getY() == 3.0);
        assertTrue(sum.getZ() == 3.0);
        
        // Add with doubles
        sum = new Vector();
        
        // Add one
        sum.add(1.0, 1.0, 1.0);
        assertTrue(sum.getX() == one.getX());
        assertTrue(sum.getY() == one.getY());
        assertTrue(sum.getZ() == one.getZ());
        
        // Add two
        sum.add(2.0, 2.0, 2.0);
        assertTrue(sum.getX() == 3.0);
        assertTrue(sum.getY() == 3.0);
        assertTrue(sum.getZ() == 3.0);
    }
    
    @Test
    public void subTest()
    {
        // Subtract with vectors
        Vector sub = new Vector(3.0, 3.0, 3.0);
        Vector one = new Vector(1.0, 1.0, 1.0);
        Vector two = new Vector(2.0, 2.0, 2.0);
        
        // Subtract first vector
        sub.sub(one);
        assertTrue(sub.getX() == 2.0);
        assertTrue(sub.getY() == 2.0);
        assertTrue(sub.getZ() == 2.0);
        
        // Subtract second vector
        sub.sub(two);
        assertTrue(sub.getX() == 0.0);
        assertTrue(sub.getY() == 0.0);
        assertTrue(sub.getZ() == 0.0);
        
        // Subtract with doubles
        sub = new Vector(3.0, 3.0, 3.0);
        
        // Subtract one
        sub.sub(1.0, 1.0, 1.0);
        assertTrue(sub.getX() == 2.0);
        assertTrue(sub.getY() == 2.0);
        assertTrue(sub.getZ() == 2.0);
        
        // Subtract two
        sub.sub(2.0, 2.0, 2.0);
        assertTrue(sub.getX() == 0.0);
        assertTrue(sub.getY() == 0.0);
        assertTrue(sub.getZ() == 0.0);
    }
    
    @Test
    public void multTest()
    {
        Vector vec = new Vector(1,1,1);
        
        // Multiply each component by a single value
        vec.mult(2);
        assertTrue(vec.getX() == 2.0);
        assertTrue(vec.getY() == 2.0);
        assertTrue(vec.getZ() == 2.0);
        
        // Multiply each component by different values
        vec.mult(1,2,3);
        assertTrue(vec.getX() == 2.0);
        assertTrue(vec.getY() == 4.0);
        assertTrue(vec.getZ() == 6.0);
        
        // Multiply by another Vector
        vec.mult(new Vector(1,2,3));
        assertTrue(vec.getX() == 2.0);
        assertTrue(vec.getY() == 8.0);
        assertTrue(vec.getZ() == 18.0);
    }
    
    @Test
    public void distanceTest()
    {
        Vector a = new Vector();
        Vector b = new Vector(1,0,0);
        double dist = a.distance(b);
        assertTrue(dist == 1.0);
        
        b.setY(1.0);
        dist = a.distance(b);
        assertTrue(dist == Math.sqrt(2.0));
        
        b.setZ(1.0);
        dist = a.distance(b);
        assertTrue(dist == Math.sqrt(3.0));
    }
    
    @Test
    public void unitVectorTest()
    {
        Vector a = new Vector(1,0,0);
        Vector unit = a.unit();
        assertTrue(unit.equals(a));
        
        Vector b = new Vector(2,0,0);
        unit = b.unit();
        assertTrue(unit.equals(a));
        
        Vector c = new Vector(2,2,2);
        Vector unitC = c.unit();
        Vector d = new Vector(4,4,4);
        Vector unitD = d.unit();
        assertTrue(unitC.equals(unitD));        
    }
}

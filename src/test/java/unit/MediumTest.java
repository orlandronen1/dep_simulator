package unit;

import static org.junit.Assert.*;
import org.junit.Test;
import medium.*;

/**
 * Unit testing for Medium and its subclasses
 * 
 * @author Ronen Orland
 */
public class MediumTest
{
    @Test
    public void constructorTest()
    {
        Medium medium = new Water();
        assertTrue(medium.getLevel() == 0);
        assertTrue(medium.getPermittivity() == Water.permittivity);
        assertTrue(medium.getConductivity() == Water.conductivity);
        assertTrue(medium.getDensity() == Water.density);
        
        medium = new Water(15);
        assertTrue(medium.getLevel() == 15);
        assertTrue(medium.getPermittivity() == Water.permittivity);
        assertTrue(medium.getConductivity() == Water.conductivity);
        assertTrue(medium.getDensity() == Water.density);
        
        medium = new DistilledWater();
        assertTrue(medium.getLevel() == 0);
        assertTrue(medium.getPermittivity() == DistilledWater.permittivity);
        assertTrue(medium.getConductivity() == DistilledWater.conductivity);
        assertTrue(medium.getDensity() == DistilledWater.density);
        
        medium = new DistilledWater(0.08);
        assertTrue(medium.getLevel() == 0.08);
        assertTrue(medium.getPermittivity() == DistilledWater.permittivity);
        assertTrue(medium.getConductivity() == DistilledWater.conductivity);
        assertTrue(medium.getDensity() == DistilledWater.density);
    }
}

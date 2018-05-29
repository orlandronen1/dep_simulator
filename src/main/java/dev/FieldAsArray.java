package dev;

import vector.Vector;
import particles.*;

/**
 * Testing movement of particle(s) in a field. The field is represented as a 3D array of Vectors, where the 
 * components of the Vectors are the force the field exerts there.
 * 
 * @author Ronen Orland
 */
public class FieldAsArray
{

    public static void main(String[] args) throws InterruptedException
    {
        int fieldSize = 10;         // Length of a side of the field
        double timeStep = 0.10;     // Increment of time to advance by per tick
        Vector[][][] field = new Vector[fieldSize][fieldSize][fieldSize];   // Field is a 3D array, as a cube
        // Can change starting position of particle by changing the numbers in the TestParticle constructor (up to a max of fieldSize)
        Particle particle = new TestParticle(0,0,0);    // Particle to move
        Vector position;            // Position of the particle
        int x, y, z;                // Coordinates of the particle
        
        
        // *** Make sure only one of these cases is not commented out when trying them out ***
        
        // Field with all vectors pointing one way
        for (int i = 0; i < fieldSize; i++)
        {
            for (int j = 0; j < fieldSize; j++)
            {
                for (int k = 0; k < fieldSize; k++)
                    field[i][j][k] = new Vector(1,0,0);
            }
        }
        
        // Field where the vectors form a square loop, so the particle will never stop
        // Note: particle must start on the origin/on an edge
//        for (int i = 0; i < fieldSize - 1; i++)
//            field[i][0][0] = new Vector(1,0,0);
//        
//        for (int i = 0; i < fieldSize - 1; i++)
//            field[fieldSize - 1][i][0] = new Vector(0,1,0);
//        
//        for (int i = 0; i < fieldSize - 1; i++)
//            field[fieldSize - i - 1][fieldSize - 1][0] = new Vector(-1,0,0);
//        
//        for (int i = 0; i < fieldSize - 1; i++)
//            field[0][fieldSize - 1 - i][0] = new Vector(0,-1,0);
        
        // Field with opposing halves
//        for (int i = 0; i < fieldSize; i++)
//        {
//            if (i < fieldSize/2)    x = 1;
//            else                    x = -1;
//            
//            for (int j = 0; j < fieldSize; j++)
//            {
//                for (int k = 0; k < fieldSize; k++)
//                    field[i][j][k] = new Vector(x,0,0);
//            }
//        }
        
        // Field where all vectors point to the center
//        for (int i = 0; i < fieldSize; i++)
//        {
//            if (i < fieldSize/2)        x = 1;
//            else                        x = -1;
//            
//            for (int j = 0; j < fieldSize; j++)
//            {
//                if (j < fieldSize/2)    y = 1;
//                else                    y = -1;
//                
//                for (int k = 0; k < fieldSize; k++)
//                {
//                    if (k < fieldSize/2)    z = 1;
//                    else                    z = -1;
//                    
//                    field[i][j][k] = new Vector(x,y,z);
//                }
//            }
//        }
        
        while(true)
        {
            Thread.sleep(50);
            
            position = particle.getPosition();
            x = (int) position.getX();
            y = (int) position.getY();
            z = (int) position.getZ();
            
            System.err.println("X: " + position.getX());
            System.err.println("Y: " + position.getY());
            System.err.println("Z: " + position.getZ());
            System.err.println();
            
            if (x < fieldSize && y < fieldSize && z < fieldSize && x >= 0 && y >= 0 && z >= 0)
                particle.move(field[x][y][z], timeStep);
            else
                break;
        }
        
        System.err.println("Simulation over");
    }

}

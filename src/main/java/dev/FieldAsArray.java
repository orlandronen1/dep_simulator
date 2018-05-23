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
        int fieldSize = 10;
        double timeStep = 0.05;
        Vector[][][] field = new Vector[fieldSize][fieldSize][fieldSize];
        Particle particle = new TestParticle(0,0,0);
        Vector position;
        int x, y, z;
        
        for (int i = 0; i < fieldSize; i++)
        {
            for (int j = 0; j < fieldSize; j++)
            {
                for (int k = 0; k < fieldSize; k++)
                {
                    field[i][j][k] = new Vector(fieldSize - i,0,0);
                }
            }
        }
        
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

package dev;

import medium.*;
import particles.*;

public class PolystyreneFcm
{
    public static void main(String[] args)
    {
        Medium medium = new DeionizedWater();
        Particle particle = new Polystyrene1um();
        double frequency = .000000001;
        
        for (int i = 0; i < 10; i++)
        {
            frequency *= 10;
            particle.calcFcm(medium, frequency);
            System.err.println("Frequency: " + frequency);
            System.err.println("Real fCM: " + particle.getFcmReal());
            System.err.println("Imag fCM: " + particle.getFcmImag());
        }

        frequency = 0.000044558966479511;
        particle.calcFcm(medium, frequency);
        System.err.println("Frequency: " + frequency);
        System.err.println("Real fCM: " + particle.getFcmReal());
        System.err.println("Imag fCM: " + particle.getFcmImag());
    }
}
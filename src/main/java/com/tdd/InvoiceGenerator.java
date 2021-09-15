package com.tdd;

/**
 * Invoice Generator
 *
 */
public class InvoiceGenerator
{
    private static final double RATE_PER_KM=10.0,RATE_PER_MIN=1.0;

    public static void main( String[] args )
    {
        System.out.println( "Welcome to cab invoice generator!");
    }

    public double calculateFare(double distanceInKm, double timeInMin) {
        int minRate = 5;
        double totalFare = RATE_PER_KM*distanceInKm+RATE_PER_MIN*timeInMin;
        return minRate>totalFare?minRate:totalFare;
    }
}

package com.tdd;

/**
 * Invoice Generator
 *
 */
public class InvoiceGenerator
{
    private static final double RATE_PER_KM=10.0,RATE_PER_MIN=1.0;
    private static final int MIN_RATE = 5;

    public static void main( String[] args )
    {
        System.out.println( "Welcome to cab invoice generator!");
    }

    public double calculateFare(double distanceInKm, double timeInMin) {
        double totalFare = RATE_PER_KM*distanceInKm+RATE_PER_MIN*timeInMin;
        return MIN_RATE>totalFare?MIN_RATE:totalFare;
    }

    public double calculateAggregateFare(double[] distanceInKm, double[] timeInMin) {
        double fare=0.0;
        for (int i=0;i<distanceInKm.length;i++){
            fare += calculateFare(distanceInKm[i],timeInMin[i]);
        };
        return fare;
    }
}

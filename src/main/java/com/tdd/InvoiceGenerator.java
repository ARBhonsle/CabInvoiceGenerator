package com.tdd;

/**
 * Invoice Generator
 *
 */
public class InvoiceGenerator
{

    public static void main( String[] args )
    {
        System.out.println( "Welcome to cab invoice generator!");
    }

    public double calculateFare(double distanceInKm, double timeInMin) {
        int minRate = 5;
        double ratePerKm=10,ratePerMin=1;
        return minRate+ratePerKm*distanceInKm+ratePerMin*timeInMin;
    }
}

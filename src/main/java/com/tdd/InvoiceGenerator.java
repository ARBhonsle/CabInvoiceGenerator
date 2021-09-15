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

    public double calculateFare(Rides ride) {
        double totalFare = RATE_PER_KM*ride.getDistance()+RATE_PER_MIN*ride.getTime();
        return MIN_RATE>totalFare?MIN_RATE:totalFare;
    }

    public double calculateAggregateFare(Rides[] rides) {
        double fare=0.0;
        for (int i=0;i<rides.length;i++){
            fare += calculateFare(rides[i]);
        };
        return fare;
    }

    public String invoiceGenerator(Rides[] rides){
        StringBuilder showInvoice = new StringBuilder();
        double fare = calculateAggregateFare(rides);
        showInvoice.append("Total Number of rides: ").append(rides.length).append("\nTotal Fare: ").append(fare).append("\nAverage Fare per Ride: ").append(fare/rides.length);
        return showInvoice.toString();
    }
}

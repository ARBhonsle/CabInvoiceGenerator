package com.tdd;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple InvoiceGenerator.
 */
public class InvoiceGeneratorTest {
    InvoiceGenerator invoice = new InvoiceGenerator();

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0, time = 5.0;
        Rides ride = new Rides(distance,time);
        double fare = invoice.calculateFare(ride);
        Assert.assertTrue(fare == 25);
    }
    @Test
    public void givenMultipleRides_shouldReturnAggregateTotalFare() {
        Rides[] rides = {
                new Rides(2.0,5.0),
                new Rides(3.0,8.0),
                new Rides(4.0,10.0)
        };
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(rides);
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(totalAggregateFare/3, fare/3,0.0);
    }

    @Test
    public void generateInvoice_returnsStringInvoice(){
        Rides[] rides = {
                new Rides(2.0,5.0),
                new Rides(3.0,8.0),
                new Rides(4.0,10.0)
        };
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(rides);
        String invoiceString = "Total Number of rides: "+ String.valueOf(rides.length)+"\nTotal Fare: "+String.valueOf(fare)+"\nAverage Fare per Ride: "+String.valueOf(fare/rides.length);
        String generatedInvoice = invoice.invoiceGenerator(rides);
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
    }
}

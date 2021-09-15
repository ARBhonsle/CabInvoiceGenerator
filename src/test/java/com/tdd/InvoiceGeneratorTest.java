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
        double fare = invoice.calculateFare(distance, time);
        Assert.assertTrue(fare == 25);
    }
    @Test
    public void givenMultipleRides_shouldReturnAggregateTotalFare() {
        double[] distance = {2.0, 3.0, 4.0};
        double[] time = {5.0, 8.0, 10.0};
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(distance,time);
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(totalAggregateFare/3, fare/3,0.0);
    }
}

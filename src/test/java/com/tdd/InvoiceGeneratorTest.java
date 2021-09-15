package com.tdd;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple InvoiceGenerator.
 */
public class InvoiceGeneratorTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare()
    {
        InvoiceGenerator invoice = new InvoiceGenerator();
        double distance=2.0, time=5.0;
        double fare = invoice.calculateFare(distance,time);
        Assert.assertTrue(fare==25);
    }
}

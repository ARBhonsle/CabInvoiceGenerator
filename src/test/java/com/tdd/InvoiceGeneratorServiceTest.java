package com.tdd;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple InvoiceGeneratorService.
 */
public class InvoiceGeneratorServiceTest {
    InvoiceGeneratorService invoice = new InvoiceGeneratorService();

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0, time = 5.0;
        Rides ride = new Rides(distance,time);
        double fare = invoice.calculateFare(ride);
        Assert.assertTrue(fare == 25);
    }
    @Test
    public void givenMultipleRides_shouldReturnAggregateTotalFare() throws Exception {
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0));
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(totalAggregateFare/3, fare/3,0.0);
    }

    @Test
    public void generateInvoice_returnsStringInvoice() throws Exception {
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0));
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        int length = invoice.getUserRidesMap().get(String.valueOf(1)).size();
        String invoiceString = "\nTotal Number of rides: "+ String.valueOf(length)+"\nTotal Fare: "+String.valueOf(totalAggregateFare)+"\nAverage Fare per Ride: "+String.valueOf(totalAggregateFare/length);
        String generatedInvoice = invoice.invoiceGenerator(String.valueOf(1));
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
    }

    @Test
    public void givenUserId_InvoiceGeneratorServiceReturnsRidesListAndStringInvoice() throws Exception {
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0));
        StringBuilder invoiceRidesList = new StringBuilder();
        invoiceRidesList.append("User Id:").append(String.valueOf(1));
        for(Rides ride : invoice.getUserRidesMap().get(String.valueOf(1))){
            invoiceRidesList.append("\nDistance: ");
            invoiceRidesList.append(ride.getDistance());
            invoiceRidesList.append("\tTime: ");
            invoiceRidesList.append(ride.getTime());
        }
        double totalAggregateFare=113;
        int length = invoice.getUserRidesMap().get(String.valueOf(1)).size();
        String invoiceString = "\nTotal Number of rides: "+ String.valueOf(length)+"\nTotal Fare: "+String.valueOf(totalAggregateFare)+"\nAverage Fare per Ride: "+String.valueOf(totalAggregateFare/length);
        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        String generatedInvoice = invoice.invoiceGenerator(String.valueOf(1));
        String generateRidesList=invoice.showRideList(String.valueOf(1));
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
        Assert.assertEquals(invoiceRidesList.toString(),generateRidesList);
    }
}

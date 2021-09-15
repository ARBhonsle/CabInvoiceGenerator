package com.tdd;

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
        String rideType = "NORMAL";
        Rides ride = new Rides(distance,time,rideType);
        double fare = invoice.calculateFare(ride,rideType);
        Assert.assertEquals(25, fare, 0.0);
    }
    @Test
    public void givenMultipleRides_shouldReturnAggregateTotalFare() throws Exception {
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0,"NORMAL"));
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(totalAggregateFare/3, fare/3,0.0);
    }

    @Test
    public void generateInvoice_returnsStringInvoice() throws Exception {
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0,"NORMAL"));
        double totalAggregateFare=113;
        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        int length = invoice.getUserRidesMap().get(String.valueOf(1)).size();
        String invoiceString = "\nTotal Number of rides: "+ length+"\nTotal Fare: "+totalAggregateFare+"\nAverage Fare per Ride: "+totalAggregateFare/length;
        String generatedInvoice = invoice.invoiceGenerator(String.valueOf(1));
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
    }

    @Test
    public void givenUserId_InvoiceGeneratorServiceReturnsRidesListAndStringInvoice() throws Exception {
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0,"NORMAL"));
        StringBuilder invoiceRidesList = new StringBuilder();
        invoiceRidesList.append("User Id:").append(1);
        for(Rides ride : invoice.getUserRidesMap().get(String.valueOf(1))){
            invoiceRidesList.append("\tType: ");
            invoiceRidesList.append(ride.getRideType());
            invoiceRidesList.append("\nDistance: ");
            invoiceRidesList.append(ride.getDistance());
            invoiceRidesList.append("\tTime: ");
            invoiceRidesList.append(ride.getTime());
        }
        double totalAggregateFare=113;
        int length = invoice.getUserRidesMap().get(String.valueOf(1)).size();
        String invoiceString = "\nTotal Number of rides: "+ length+"\nTotal Fare: "+totalAggregateFare+"\nAverage Fare per Ride: "+totalAggregateFare/length;
        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        String generatedInvoice = invoice.invoiceGenerator(String.valueOf(1));
        String generateRidesList=invoice.showRideList(String.valueOf(1));
        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
        Assert.assertEquals(invoiceRidesList.toString(),generateRidesList);
    }

    @Test
    public void givenRides_NormalAndPremiumTypes_returnInvoiceAndRideDetails() throws Exception {
        // Normal rides
        invoice.addRides(String.valueOf(1),new Rides(2.0,5.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(3.0,8.0,"NORMAL"));
        invoice.addRides(String.valueOf(1),new Rides(4.0,10.0,"NORMAL"));

        StringBuilder invoiceRidesList = new StringBuilder();
        invoiceRidesList.append("User Id:").append(1);
        for(Rides ride : invoice.getUserRidesMap().get(String.valueOf(1))){
            invoiceRidesList.append("\tType: ");
            invoiceRidesList.append(ride.getRideType());
            invoiceRidesList.append("\nDistance: ");
            invoiceRidesList.append(ride.getDistance());
            invoiceRidesList.append("\tTime: ");
            invoiceRidesList.append(ride.getTime());
        }
        double totalAggregateFare=113;

        int length = invoice.getUserRidesMap().get(String.valueOf(1)).size();
        String invoiceString = "\nTotal Number of rides: "+ length+"\nTotal Fare: "+totalAggregateFare+"\nAverage Fare per Ride: "+totalAggregateFare/length;

        double fare = invoice.calculateAggregateFare(String.valueOf(1));
        String generatedInvoice = invoice.invoiceGenerator(String.valueOf(1));
        String generateRidesList=invoice.showRideList(String.valueOf(1));

        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
        Assert.assertEquals(invoiceRidesList.toString(),generateRidesList);

        // Premium rides
        invoice.addRides(String.valueOf(2),new Rides(2.0,5.0,"PREMIUM"));
        invoice.addRides(String.valueOf(2),new Rides(3.0,8.0,"PREMIUM"));
        invoice.addRides(String.valueOf(2),new Rides(4.0,10.0,"PREMIUM"));
        StringBuilder premiumInvoiceRidesList = new StringBuilder();
        premiumInvoiceRidesList.append("User Id:").append(2);
        for(Rides ride : invoice.getUserRidesMap().get(String.valueOf(1))){
            premiumInvoiceRidesList.append("\tType: ");
            premiumInvoiceRidesList.append(ride.getRideType());
            premiumInvoiceRidesList.append("\nDistance: ");
            premiumInvoiceRidesList.append(ride.getDistance());
            premiumInvoiceRidesList.append("\tTime: ");
            premiumInvoiceRidesList.append(ride.getTime());
        }
        totalAggregateFare=294.0;
        length = invoice.getUserRidesMap().get(String.valueOf(2)).size();
        invoiceString = "\nTotal Number of rides: "+ length+"\nTotal Fare: "+totalAggregateFare+"\nAverage Fare per Ride: "+totalAggregateFare/length;

        fare = invoice.calculateAggregateFare(String.valueOf(2));
        generatedInvoice = invoice.invoiceGenerator(String.valueOf(2));
        generateRidesList=invoice.showRideList(String.valueOf(2));

        Assert.assertEquals(totalAggregateFare,fare,0.0);
        Assert.assertEquals(invoiceString,generatedInvoice);
        Assert.assertEquals(premiumInvoiceRidesList.toString(),generateRidesList);
    }
}

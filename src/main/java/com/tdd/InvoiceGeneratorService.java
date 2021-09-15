package com.tdd;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invoice Generator
 */
public class InvoiceGeneratorService {
    private static final double RATE_PER_KM = 10.0, RATE_PER_MIN = 1.0;
    private static final int MIN_RATE = 5;

    private static ArrayList<Rides> rides;
    private static HashMap<String, ArrayList<Rides>> userRidesMap;

    public InvoiceGeneratorService() {
        rides = new ArrayList<>();
        userRidesMap = new HashMap<>();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to cab invoice generator!");
    }

    public double calculateFare(Rides ride) {
        double totalFare = RATE_PER_KM * ride.getDistance() + RATE_PER_MIN * ride.getTime();
        return MIN_RATE > totalFare ? MIN_RATE : totalFare;
    }

    public double calculateAggregateFare(String userId) throws Exception {
        double fare = 0.0;
        if(getUserRidesMap().containsKey(userId)){
            for (Rides ride : getUserRidesMap().get(userId)) {
                fare += calculateFare(ride);
            }
        } else{
            throw new Exception("Invalid User Id");
        }
        return fare;
    }

    public String invoiceGenerator(String userId) throws Exception {
        StringBuilder showInvoice = new StringBuilder();
        if(getUserRidesMap().containsKey(userId)){
            ArrayList<Rides> rideList = getUserRidesMap().get(userId);
            double fare = calculateAggregateFare(userId);
            showInvoice.append("\nTotal Number of rides: ").append(rideList.size()).append("\nTotal Fare: ").append(fare).append("\nAverage Fare per Ride: ").append(fare / rideList.size());
        }else {
            throw new Exception("Invalid User Id");
        }
        return showInvoice.toString();
    }

    public void addRides(String userId, Rides ride) {
        if (userRidesMap.containsKey(userId)) {
            ArrayList<Rides> rideList = userRidesMap.get(userId);
            rideList.add(ride);
        } else {
            rides.add(ride);
            userRidesMap.put(userId, rides);
        }
    }

    public String showRideList(String userId) throws Exception {
        StringBuilder showRideDetails = new StringBuilder();
        showRideDetails.append("User Id:").append(userId);
        for (Rides ride : userRidesMap.get(userId)) {
            showRideDetails.append("\nDistance: ");
            showRideDetails.append(ride.getDistance());
            showRideDetails.append("\tTime: ");
            showRideDetails.append(ride.getTime());
        }
        return showRideDetails.toString();
    }

    public HashMap<String, ArrayList<Rides>> getUserRidesMap() {
        return userRidesMap;
    }
}

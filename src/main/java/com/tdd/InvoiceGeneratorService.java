package com.tdd;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Invoice Generator
 */
public class InvoiceGeneratorService {
    private static final double RATE_PER_KM_NORMAL = 10.0, RATE_PER_MIN_NORMAL = 1.0;
    private static final double RATE_PER_KM_PREMIUM = 15.0, RATE_PER_MIN_PREMIUM = 2.0;
    private static final int MIN_RATE_NORMAL = 5,MIN_RATE_PREMIUM = 20;

    private static ArrayList<Rides> rides;
    private static HashMap<String, ArrayList<Rides>> userRidesMap;

    public InvoiceGeneratorService() {
        rides = new ArrayList<>();
        userRidesMap = new HashMap<>();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to cab invoice generator!");
    }

    public double calculateFare(Rides ride,String rideType) {
        double ratePerKm = rideType.equals("NORMAL")?RATE_PER_KM_NORMAL:RATE_PER_KM_PREMIUM;
        double ratePerMin = rideType.equals("NORMAL")?RATE_PER_MIN_NORMAL:RATE_PER_MIN_PREMIUM;
        double minRate = rideType.equals("NORMAL")?MIN_RATE_NORMAL:MIN_RATE_PREMIUM;

        double totalFare = ratePerKm * ride.getDistance() + ratePerMin * ride.getTime();
        return Math.max(minRate,totalFare);
    }

    public double calculateAggregateFare(String userId) throws Exception {
        double fare = 0.0;
        if(getUserRidesMap().containsKey(userId)){
            for (Rides ride : getUserRidesMap().get(userId)) {
                fare += calculateFare(ride,ride.getRideType());
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

    public String showRideList(String userId) {
        StringBuilder showRideDetails = new StringBuilder();
        showRideDetails.append("User Id:").append(userId);
        for (Rides ride : userRidesMap.get(userId)) {
            showRideDetails.append("\tType: ");
            showRideDetails.append(ride.getRideType());
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

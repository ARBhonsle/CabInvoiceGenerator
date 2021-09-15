package com.tdd;

public class Rides {
    private double distance, time;
    private String rideType;

    public Rides(double distanceInKm, double timeInMin, String rideType){
        this.distance=distanceInKm;
        this.time=timeInMin;
        this.rideType=rideType;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    public String getRideType() {
        return rideType;
    }
}

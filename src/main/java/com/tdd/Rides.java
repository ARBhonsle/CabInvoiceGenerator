package com.tdd;

public class Rides {
    private double distance, time;

    public Rides(double distanceInKm, double timeInMin){
        this.distance=distanceInKm;
        this.time=timeInMin;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }
}

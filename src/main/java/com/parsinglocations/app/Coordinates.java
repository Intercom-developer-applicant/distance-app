package com.parsinglocations.app;

public class Coordinates 
{
    private double latitude;
    private double longitude;

    Coordinates(String coords) {
        String comma = ",";

        // Return false if input is not as expected
        if(coords.isEmpty() || !coords.contains(comma))
            return;

        latitude = Calculations.deg2rad(coords.split(comma)[0]);
        longitude = Calculations.deg2rad(coords.split(comma)[1]);
    }

    public double getLat() {
        return latitude;
    }

    public double getLong() {
        return longitude;
    }
}
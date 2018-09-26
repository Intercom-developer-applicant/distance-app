package com.parsinglocations.app;

public class Calculations 
{
    public Boolean within100km(Coordinates base, Coordinates dest) {

        double centralAngle = sphericalLaw(base.getLat(), base.getLong(), dest.getLat(), dest.getLong());
        double distance = arcLength(centralAngle);
        
        return distance <= 100;
    }

    // Radians = Degrees * PI / 180
    public static double deg2rad(String deg) {
        double degNum;
        try {
            degNum = Double.parseDouble(deg);
        } catch (NumberFormatException ex) {
            // If not a double - return 0.0
            ex.printStackTrace();
            degNum = 0.0;
        }
        return degNum * (Math.PI / 180);
    }

    // centralAngle = arccos( (sin(lat1) * sin(lat2)) + (cos(lat1) * cos(lat2) * cos( |long1 - long 2| )) )
    private double sphericalLaw(double baseLat, double baseLong, double destLat, double destLong) {

        double result = Math.acos( (Math.sin(baseLat) * Math.sin(destLat)) + 
            (Math.cos(baseLat) * Math.cos(destLat) * Math.cos(Math.abs(baseLong - destLong))) );

        return result;
    }

    // arcLength = Radius * centralAngle
    private double arcLength(double centralAngle) {
        double earthRadius = 6371.0;
        return earthRadius * centralAngle;
    }
}
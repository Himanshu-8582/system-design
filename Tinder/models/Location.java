package Tinder.models;

public class Location {
    private double latitude;
    private double longitude;

    public Location() {
        latitude = 0.0;
        longitude = 0.0;
    }

    public Location(double lat, double lon) {
        latitude = lat;
        longitude = lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double lat) {
        latitude = lat;
    }

    public void setLongitude(double lon) {
        longitude = lon;
    }

    // Calculate distance in kilometers between two locations using Haversine
    // formula
    public double distanceInKm(Location other) {
        final double earthRadiusKm = 6371.0;
        double dLat = (other.latitude - latitude) * Math.PI / 180.0;
        double dLon = (other.longitude - longitude) * Math.PI / 180.0;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(latitude * Math.PI / 180.0) * Math.cos(other.latitude * Math.PI / 180.0) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * c;
    }
}

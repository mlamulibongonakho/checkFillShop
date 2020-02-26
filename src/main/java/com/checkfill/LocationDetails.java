package com.checkfill;

public class LocationDetails {
    private String locationName;
    private String city;
    private String state;
    private String phoneNumber;

    public LocationDetails() {
    }

    public LocationDetails(String locationName, String city, String state, String phoneNumber) {
        this.locationName = locationName;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "LocationDetails{" +
                "locationName='" + locationName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

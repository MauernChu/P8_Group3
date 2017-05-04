package p8.group3.ida.aau.p8_group3.Model;


import java.sql.Blob;

public class Location {
    private int locationID;
    private String locationName;
    private String locationDescription;
    private double locationLongitude;
    private double locationLatitude;
    private String locationCategory;
    private String locationAddress;
    private String locationCity;



    /*
    Constructor for location objects.
    locationID is included in the constructor for future usage.
    */

    public Location(int locationID, String locationName, String locationDescription, Double locationLongitude,
                    Double locationLatitude, String locationCategory, String locationAddress, String locationCity ) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.locationCategory = locationCategory;
        this.locationAddress = locationAddress;
        this.locationCity = locationCity;
    }


    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(String locationCategory) {
        this.locationCategory = locationCategory;
    }
}

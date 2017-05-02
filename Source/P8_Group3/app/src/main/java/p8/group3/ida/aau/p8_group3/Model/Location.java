package p8.group3.ida.aau.p8_group3.Model;


import java.sql.Blob;

public class Location {
    private int locationID;
    private String locationName;
    private String locationDescription;
    private double locationLongitude;
    private double locationLatitude;
    private String locationCategory;



    /*
    Constructor for location objects.
    locationID is included in the constructor for future usage.
    */

    public Location(int locationID, String locationName, String locationDescription, Double locationLongitude, Double locationLatitude, String locationCategory) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.locationCategory = locationCategory;
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

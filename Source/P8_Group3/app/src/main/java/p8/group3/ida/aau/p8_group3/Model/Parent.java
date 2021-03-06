package p8.group3.ida.aau.p8_group3.Model;

import java.util.Date;


public class Parent {
    private int parentID;
    private String username;
    private int numberOfChildren;
    private String ageOfChildren;
    private String password;
    private String profilePicture;
    private String infoAboutParent;
    private Date timeChekedIn;
    private int locationIdCheckin;
    private String email;
    private String cityOfResidence;
    private String hobbyList;
    private String languageList;


    /*
    Constructor for parent objects.
    parentID is not included in contstructor because this value is autoincremented
    infoAboutParent is not included in onstructor because the parents doesn't have to enter the
    information immediatly when they are creating a profile.
    */

    public Parent(int parentID, String username, String password, String email, int numberOfChildren, String ageOfChildren, String cityOfResidence) {
        this.parentID = parentID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.numberOfChildren = numberOfChildren;
        this.ageOfChildren = ageOfChildren;
        this.cityOfResidence = cityOfResidence;
    }

    public Parent(int parentID, String username, int numberOfChildren, String ageOfChildren, String password, String profilePicture, String infoAboutParent, int locationIdCheckin, String email, String cityOfResidence, Date timeChekedIn, String hobbyList, String languageList) {
        this.parentID = parentID;
        this.username = username;
        this.numberOfChildren = numberOfChildren;
        this.ageOfChildren = ageOfChildren;
        this.password = password;
        this.profilePicture = profilePicture;
        this.infoAboutParent = infoAboutParent;
        this.locationIdCheckin = locationIdCheckin;
        this.email = email;
        this.cityOfResidence = cityOfResidence;
        this.timeChekedIn = timeChekedIn;
        this.hobbyList = hobbyList;
        this.languageList = languageList;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getAgeOfChildren() {
        return ageOfChildren;
    }

    public void setAgeOfChildren(String ageOfChildren) {
        this.ageOfChildren = ageOfChildren;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getInfoAboutParent() {
        return infoAboutParent;
    }

    public void setInfoAboutParent(String infoAboutParent) {
        this.infoAboutParent = infoAboutParent;
    }

    public Date getTimeChekedIn() {
        return timeChekedIn;
    }

    public void setTimeChekedIn(Date timeChekedIn) {
        this.timeChekedIn = timeChekedIn;
    }

    public int getLocationIdCheckin() {
        return locationIdCheckin;
    }

    public void setLocationIdCheckin(int locationIdCheckin) {
        this.locationIdCheckin = locationIdCheckin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public String getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(String hobbyList) {
        this.hobbyList = hobbyList;
    }

    public String getLanguageList() {
        return languageList;
    }

    public void setLanguageList(String languageList) {
        this.languageList = languageList;
    }
}

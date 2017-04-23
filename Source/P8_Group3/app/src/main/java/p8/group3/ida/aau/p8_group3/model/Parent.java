package p8.group3.ida.aau.p8_group3.model;


public class Parent {
    private int parentID;
    private String username;
    private int numberOfChildren;
    private int ageOfChildren;
    private String infoAboutParent;


    public Parent(String username, int numberOfChildren, int ageOfChildren, String infoAboutParent) {
        this.username = username;
        this.numberOfChildren = numberOfChildren;
        this.ageOfChildren = ageOfChildren;
        this.infoAboutParent = infoAboutParent;
    }

    public int getParentID() {
        return parentID;
    }

    public String getUsername() {
        return username;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public int getAgeOfChildren() {
        return ageOfChildren;
    }

    public String getInfoAboutParent() {
        return infoAboutParent;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setAgeOfChildren(int ageOfChildren) {
        this.ageOfChildren = ageOfChildren;
    }

    public void setInfoAboutParent(String infoAboutParent) {
        this.infoAboutParent = infoAboutParent;
    }
}

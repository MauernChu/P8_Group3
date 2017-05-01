package p8.group3.ida.aau.p8_group3.Model;


public class Parent {
    private int parentID;
    private String username;
    private String password;
    private String email;
    private int numberOfChildren;
    private String ageOfChildren;
    private String infoAboutParent;

    /*
    Constructor for parent objects.
    parentID is not included in contstructor because this value is autoincremented
    infoAboutParent is not included in onstructor because the parents doesn't have to enter the
    information immediatly when they are creating a profile.
    */

    public Parent(int parentID, String username, String password, String email, int numberOfChildren, String ageOfChildren) {
        this.parentID = parentID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.numberOfChildren = numberOfChildren;
        this.ageOfChildren = ageOfChildren;
    }

    public int getParentID()
    {
        return parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfChildren()
    {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren)
    {
        this.numberOfChildren = numberOfChildren;
    }

    public String getAgeOfChildren()
    {
        return ageOfChildren;
    }

    public void setAgeOfChildren(String ageOfChildren)
    {
        this.ageOfChildren = ageOfChildren;
    }

    public String getInfoAboutParent()
    {
        return infoAboutParent;
    }

    public void setInfoAboutParent(String infoAboutParent) {
        this.infoAboutParent = infoAboutParent;
    }
}

package p8.group3.ida.aau.p8_group3.model;


public class Parent {
    private int _parentID;
    private String _username;
    private String _password;
    private int _numberOfChildren;
    private int _ageOfChildren;
    private String _infoAboutParent;

    /*
    Constructor for parent objects.
    parentID is not included in contstructor because this value is autoincremented
    infoAboutParent is not included in onstructor because the parents doesn't have to enter the
    information immediatly when they are creating a profile.
    */
    public Parent(String username) {
        this._username = username;


    }

    public int get_parentID() {
        return _parentID;
    }

    public String get_username() {
        return _username;
    }

    public String get_password() {
        return _password;
    }

    public int get_numberOfChildren() {
        return _numberOfChildren;
    }

    public int get_ageOfChildren() {
        return _ageOfChildren;
    }

    public String get_infoAboutParent() {
        return _infoAboutParent;
    }

    public void set_parentID(int _parentID) {
        this._parentID = _parentID;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_numberOfChildren(int _numberOfChildren) {
        this._numberOfChildren = _numberOfChildren;
    }

    public void set_ageOfChildren(int _ageOfChildren) {
        this._ageOfChildren = _ageOfChildren;
    }

    public void set_infoAboutParent(String _infoAboutParent) {
        this._infoAboutParent = _infoAboutParent;
    }
}

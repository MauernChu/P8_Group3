package p8.group3.ida.aau.p8_group3.Database.DAO;

import android.database.SQLException;

import com.google.android.gms.maps.model.Marker;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import p8.group3.ida.aau.p8_group3.Model.Parent;


public interface ParentDAO {
    void open() throws SQLException;

    void close();

    Parent createParent(Parent parent);

    String loginCheckCredentials(String username);

    Parent retrieveInformationAboutParent(String password);

    void editProfile (Parent parent);

    void checkInNow(Parent parent, int markerCity2);

    void checkOut(String parent, Marker marker);

    void checkOut(Parent parent, Marker marker);
}

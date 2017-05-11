package p8.group3.ida.aau.p8_group3.Database.DAO;

import android.database.SQLException;

import java.util.List;

import p8.group3.ida.aau.p8_group3.Model.Parent;


public interface ParentDAO {
    void open() throws SQLException;

    void close();

    Parent createParent(Parent parent);

    String loginCheckCredentials(String username);

    Parent retrieveInformationAboutParent(String password);

    void editProfile (Parent parent);
}

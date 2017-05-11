package p8.group3.ida.aau.p8_group3.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO;
import p8.group3.ida.aau.p8_group3.Database.ParentDAOImpl;
import p8.group3.ida.aau.p8_group3.Model.Parent;
import p8.group3.ida.aau.p8_group3.R;

/**
 * Created by karatinka on 9.5.2017.
 */

public class EditProfilePage extends AppCompatActivity{

    EditText editName;
    EditText editChildren;
    EditText editAgeOfChildren;
    EditText editCity;
    EditText editAllLanguages;
    EditText editTextAbout;
    EditText editTextHobbies;

    private ParentDAO parentDAO;

    private String loginUsername;
    private String loginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_page);

        editName = (EditText) findViewById(R.id.editName);
        editChildren = (EditText) findViewById(R.id.editChildren);
        editAgeOfChildren = (EditText) findViewById(R.id.editAge);
        editCity = (EditText) findViewById(R.id.editCity);
        editAllLanguages = (EditText) findViewById(R.id.editAllLanguages);
        editTextAbout = (EditText) findViewById(R.id.editTextAbout);
        editTextHobbies = (EditText) findViewById(R.id.editTextHobbies);

        parentDAO = new ParentDAOImpl(this);
        parentDAO.open();

        Bundle bundle = getIntent().getExtras();
        loginUsername = bundle.getString("loginUsername");
        loginPassword = bundle.getString("loginPassword");

        final Parent editProfileParent = parentDAO.retrieveInformationAboutParent(loginPassword);
        String profileUserName = editProfileParent.getUsername();
        int profileChildren = editProfileParent.getNumberOfChildren();
        String profileAgeOfChildren = editProfileParent.getAgeOfChildren();
        String profileCity = editProfileParent.getCityOfResidence();
        //String profileAllLanguages = editProfileParent.getUsername(); we have to add this to a table
        String profileTextAbout = editProfileParent.getInfoAboutParent();
        //String profileTextHobbies = editProfileParent.getUsername();

        editName.setText(profileUserName);
        editChildren.setText(Integer.toString(profileChildren));
        editAgeOfChildren.setText(profileAgeOfChildren);
        editCity.setText(profileCity);
        editTextAbout.setText(profileTextAbout);

        Button doneEditingButton = (Button) findViewById(R.id.doneEditingButton);
        doneEditingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String editNameAsString = editName.getText().toString();
                int profileChildrenAsString = Integer.parseInt(editChildren.getText().toString());
                String editAgeOfChildrenAsString = editAgeOfChildren.getText().toString();
                String editCityAsString = editCity.getText().toString();
                String editTextAboutAsString = editTextAbout.getText().toString();
                editProfileParent.setUsername(editNameAsString);
                editProfileParent.setNumberOfChildren(profileChildrenAsString);
                editProfileParent.setAgeOfChildren(editAgeOfChildrenAsString);
                editProfileParent.setParentCity(editCityAsString);
                editProfileParent.setInfoAboutParent(editTextAboutAsString);
                parentDAO.editProfile(editProfileParent);
                Intent doneEdittingIntent = new Intent(view.getContext(), ProfilePage.class);
                doneEdittingIntent.putExtra("loginUsername", loginUsername);
                doneEdittingIntent.putExtra("loginPassword", loginPassword);
                startActivityForResult(doneEdittingIntent, 0);
            }
        });


    }
}

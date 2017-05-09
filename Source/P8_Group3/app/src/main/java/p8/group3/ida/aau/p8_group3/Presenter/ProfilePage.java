package p8.group3.ida.aau.p8_group3.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO;
import p8.group3.ida.aau.p8_group3.Database.ParentDAOImpl;
import p8.group3.ida.aau.p8_group3.Model.Parent;
import p8.group3.ida.aau.p8_group3.R;

public class ProfilePage extends AppCompatActivity {
    TextView txProfileUsername;
    TextView txProfileChildren;
    TextView txProfileChildrenAge;
    TextView txProfileCity;

    private String loginUsername;
    private String loginPassword;

    private ParentDAO parentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Bundle bundle = getIntent().getExtras();
        loginUsername = bundle.getString("loginUsername");
        loginPassword = bundle.getString("loginPassword");

        parentDAO = new ParentDAOImpl(this);
        parentDAO.open();

        Parent profileParent = parentDAO.retrieveInformationAboutParent(loginUsername, loginPassword);

        String profileUsername = profileParent.getUsername();
        String profileParentAgeOfChildren = profileParent.getAgeOfChildren();
        int profileChildren = profileParent.getNumberOfChildren();
        String profileEmail = profileParent.getEmail();
        String profileParentDescription = profileParent.getInfoAboutParent();
        String profileParentCity = profileParent.getCityOfResidence();


        txProfileChildrenAge = (TextView) findViewById(R.id.editAge);
        txProfileChildrenAge.setText(profileParentAgeOfChildren);

        txProfileUsername = (TextView) findViewById(R.id.editName);
        txProfileUsername.setText(profileUsername);

        txProfileChildren = (TextView) findViewById(R.id.editChildren);
        txProfileChildren.setText(Integer.toString(profileChildren));

        txProfileCity = (TextView) findViewById(R.id.editCity);
        txProfileCity.setText(profileParentCity);


        //Variables that change visibility.
        final TextView name = (TextView) findViewById(R.id.editName);
        final TextView children = (TextView) findViewById(R.id.editChildren);
        final TextView ageOfChildren = (TextView) findViewById(R.id.editAge);
        final TextView city = (TextView) findViewById(R.id.editCity);
        final ImageView pencil = (ImageView) findViewById(R.id.pencilIconEdit);
        final TextView languages = (TextView) findViewById(R.id.allLanguages);
        final TextView about = (TextView) findViewById(R.id.textAbout);

        //methods for menu bar
        Button logoutButton = (Button) findViewById(R.id.logoutButtonProfilePage);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent profileIntent = new Intent(view.getContext(), LoginPage.class);
                startActivityForResult(profileIntent, 0);
                Toast.makeText(ProfilePage.this, "You have been logged out.", Toast.LENGTH_SHORT).show();
            }
        });

        Button mapButton = (Button) findViewById(R.id.mapButtonProfilePage);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent profileIntent = new Intent(view.getContext(), MapsPage.class);
                startActivityForResult(profileIntent, 0);
            }
        });

        //method for going to EditProfilePage
        Button editButton = (Button) findViewById(R.id.editProfile);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent editProfileIntent = new Intent(view.getContext(), EditProfilePage.class);
                editProfileIntent.putExtra("loginUsername", loginUsername);
                editProfileIntent.putExtra("loginPassword", loginPassword);
                startActivityForResult(editProfileIntent, 0);
            }
        });
    }
}

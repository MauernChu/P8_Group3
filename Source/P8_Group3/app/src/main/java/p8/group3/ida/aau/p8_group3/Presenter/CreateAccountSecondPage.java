package p8.group3.ida.aau.p8_group3.Presenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO;
import p8.group3.ida.aau.p8_group3.Database.ParentDAOImpl;
import p8.group3.ida.aau.p8_group3.Model.Parent;
import p8.group3.ida.aau.p8_group3.R;

public class CreateAccountSecondPage extends AppCompatActivity {
    private ParentDAO parentDAO;

    EditText numberOfChildren;
    EditText ageOfChildren;

    private String username;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_second_page);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        password = bundle.getString("password");
        email = bundle.getString("email");

        parentDAO = new ParentDAOImpl(this);
        parentDAO.open();

        numberOfChildren = (EditText) findViewById(R.id.numberOfChildren);
        ageOfChildren = (EditText) findViewById(R.id.ageOfChildren);

        TextView txchimp = (TextView) findViewById(R.id.loginTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView) findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txFinish = (TextView) findViewById(R.id.finish);
        txFinish.setTypeface(custom_font);

        TextView txCity = (TextView) findViewById(R.id.city);
        txCity.setTypeface(custom_font);

        TextView txNumberOfChildren = (TextView) findViewById(R.id.numberOfChildren);
        txNumberOfChildren.setTypeface(custom_font);

        TextView txAgeOfChildren = (TextView) findViewById(R.id.ageOfChildren);
        txAgeOfChildren.setTypeface(custom_font);

        Button createAccountButton = (Button) findViewById(R.id.finish);

        //Method for creating a new parent object with informations from the user.
        //And for changing page to profile page (you can only change to profile page if you
        // have entered informations in the create profile)
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String numberOfChildrenAsString = numberOfChildren.getText().toString();
                int numberofchildren = Integer.parseInt(numberOfChildrenAsString);
                String ageofchildren = ageOfChildren.getText().toString();
                Parent newParent = new Parent(0, username, password, email, numberofchildren, ageofchildren);
                Parent dbParent = parentDAO.createParent(newParent);
                Intent myIntent = new Intent(view.getContext(), LoginPage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

}


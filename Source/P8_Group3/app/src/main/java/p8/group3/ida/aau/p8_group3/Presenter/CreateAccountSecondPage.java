package p8.group3.ida.aau.p8_group3.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
    EditText cityOfResidence;

    private String username;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_second_page);
        setupUI(findViewById(R.id.CreateAccount2));

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        password = bundle.getString("password");
        email = bundle.getString("email");

        parentDAO = new ParentDAOImpl(this);
        parentDAO.open();

        numberOfChildren = (EditText) findViewById(R.id.numberOfChildren);
        ageOfChildren = (EditText) findViewById(R.id.ageOfChildren);
        cityOfResidence = (EditText) findViewById(R.id.profileParentCity);

        TextView txchimp = (TextView) findViewById(R.id.loginTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView) findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txFinish = (TextView) findViewById(R.id.finish);
        txFinish.setTypeface(custom_font);

        TextView txCity = (TextView) findViewById(R.id.profileParentCity);
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
                String cityOfResidenceAsString = cityOfResidence.getText().toString();
                int numberofchildren = Integer.parseInt(numberOfChildrenAsString);
                String ageofchildren = ageOfChildren.getText().toString();
                Parent newParent = new Parent(0, username, password, email, numberofchildren, ageofchildren, cityOfResidenceAsString);
                Parent dbParent = parentDAO.createParent(newParent);
                Intent myIntent = new Intent(view.getContext(), LoginPage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(CreateAccountSecondPage.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}


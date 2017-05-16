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
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import p8.group3.ida.aau.p8_group3.Database.DAO.ParentDAO;
import p8.group3.ida.aau.p8_group3.Database.ParentDAOImpl;
import p8.group3.ida.aau.p8_group3.R;

public class LoginPage extends AppCompatActivity {
    private ParentDAO parentDAO;

    EditText loginUsername;
    EditText loginPassword;

    Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setupUI(findViewById(R.id.activity_main));

        parentDAO = new ParentDAOImpl(this);
        parentDAO.open();

        //Log-in variables
        loginUsername = (EditText) findViewById(R.id.createUsername);
        loginPassword = (EditText) findViewById(R.id.createPassword);



        //Font variables
        TextView txchimp = (TextView) findViewById(R.id.loginTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView) findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txForgotPass = (TextView) findViewById(R.id.forgotPassword);
        txForgotPass.setTypeface(custom_font);

        TextView txPass = (TextView) findViewById(R.id.createPassword);
        txPass.setTypeface(custom_font);

        TextView txSignIn = (TextView) findViewById(R.id.signIn);
        txSignIn.setTypeface(custom_font);

        TextView txCreateAcc = (TextView) findViewById(R.id.createAccount);
        txCreateAcc.setTypeface(custom_font);


        //Clicking Create Account to start creating one
        Button createAccountButton = (Button) findViewById(R.id.createAccount);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CreateAccountPage.class);
                startActivityForResult(myIntent, 0);
            }
        });


        //Method for checking the login credentials and changing view if the credentials is correct.
        Button logIn = (Button) findViewById(R.id.signIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsernameAsString = loginUsername.getText().toString();
                String loginPasswordAsString = loginPassword.getText().toString();
                String password = parentDAO.loginCheckCredentials(loginUsernameAsString);
                if (loginPasswordAsString.equals(password)) {
                    //parentDAO.checkOut(password, marker); //Here it has the fastest response time and doesn't hide the toast!, but it takes 2 clicks on the marker to update!
                    Toast.makeText(LoginPage.this, "Username and Password accepted", Toast.LENGTH_SHORT).show();
                    Intent mapIntent = new Intent(view.getContext(), MapsPage.class);
                    mapIntent.putExtra("loginUsername", loginUsername.getText().toString());
                    mapIntent.putExtra("loginPassword", loginPassword.getText().toString());
                    startActivityForResult(mapIntent, 0);

                } else {
                    Toast.makeText(LoginPage.this, "Username and Password does not match existing user", Toast.LENGTH_SHORT).show();
                }
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
                    hideSoftKeyboard(LoginPage.this);
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


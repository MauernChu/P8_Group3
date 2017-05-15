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

import p8.group3.ida.aau.p8_group3.R;


public class CreateAccountPage extends AppCompatActivity {

    EditText createUsername;
    EditText createPassword;
    EditText createEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);
        setupUI(findViewById(R.id.createAccount));

        createUsername = (EditText) findViewById(R.id.createUsername);
        createPassword = (EditText) findViewById(R.id.createPassword);
        createEmail = (EditText) findViewById(R.id.email);

        TextView txchimp = (TextView) findViewById(R.id.loginTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView) findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txUsername = (TextView) findViewById(R.id.createUsername);
        txUsername.setTypeface(custom_font);

        TextView txPassword = (TextView) findViewById(R.id.createPassword);
        txPassword.setTypeface(custom_font);

        TextView txRepPass = (TextView) findViewById(R.id.repeat_password);
        txRepPass.setTypeface(custom_font);

        TextView txEmail = (TextView) findViewById(R.id.email);
        txEmail.setTypeface(custom_font);

        TextView txNext = (TextView) findViewById(R.id.next);
        txNext.setTypeface(custom_font);

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateAccountSecondPage.class);
                intent.putExtra("username", createUsername.getText().toString());
                intent.putExtra("password", createPassword.getText().toString());
                intent.putExtra("email", createEmail.getText().toString());
                startActivityForResult(intent, 0);
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
                    hideSoftKeyboard(CreateAccountPage.this);
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

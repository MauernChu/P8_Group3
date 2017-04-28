package p8.group3.ida.aau.p8_group3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import p8.group3.ida.aau.p8_group3.Database.databaseHandler;
import p8.group3.ida.aau.p8_group3.model.Parent;

public class LoginPage extends AppCompatActivity {

    EditText databaseTesttwo;
    TextView databaseTest;
    databaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        databaseTesttwo = (EditText)findViewById(R.id.testtwo);
        databaseTest = (TextView) findViewById(R.id.test);
        dbHandler = new databaseHandler(this, null, null, 1);
        printDatabase();

        TextView txchimp = (TextView)findViewById(R.id.loginTitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView)findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txForgotPass = (TextView)findViewById(R.id.forgotPassword);
        txForgotPass.setTypeface(custom_font);

        TextView txPass = (TextView)findViewById(R.id.password);
        txPass.setTypeface(custom_font);

        TextView txSignIn = (TextView)findViewById(R.id.signIn);
        txSignIn.setTypeface(custom_font);

        TextView txCreateAcc = (TextView)findViewById(R.id.createAccount);
        txCreateAcc.setTypeface(custom_font);


        //Clicking Create Account to start creating one
        Button createAccountButton = (Button)findViewById(R.id.createAccount);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CreateAccountPage.class);
                startActivityForResult(myIntent, 0);
            }
        });


        //Using the sign in button from the LoginPage to test the maps view

        Button mapsTest = (Button)findViewById(R.id.signIn);
        mapsTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MapsPage.class);
                startActivityForResult(myIntent, 0);

            }
        });


        //Put code here to hardcode a log-in
        



        Button dbtest = (Button)findViewById(R.id.add);
        dbtest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Parent parent = new Parent(databaseTesttwo.getText().toString());
                dbHandler.addParentToDatabase(parent);
                printDatabase();
            }
        });

        Button dbDeleteTest = (Button) findViewById(R.id.delete);
        dbDeleteTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String InputText = databaseTesttwo.getText().toString();
                dbHandler.deleteParentFromDatabase(InputText);
                printDatabase();
            }
        });


    }

    //add to the database

    /*public void addButtonClicked() {
        Parent parent = new Parent(testtwo.getText().toString());
        dbHandler.addParentToDatabase(parent);
        printDatabase();
    }*/


    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        databaseTest.setText(dbString);
        databaseTesttwo.setText("");
    }
}
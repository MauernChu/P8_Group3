package p8.group3.ida.aau.p8_group3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //Database variables
        databaseTesttwo = (EditText)findViewById(R.id.testtwo);
        databaseTest = (TextView) findViewById(R.id.test);
        dbHandler = new databaseHandler(this, null, null, 1);
        printDatabase();

        //Log-in variables
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        //Font variables
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



        //Hardcoded a log-in
        Button logIn = (Button) findViewById(R.id.signIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
                    Toast.makeText(LoginPage.this, "Username and Password accepted", Toast.LENGTH_SHORT).show();
                    Intent mapIntent = new Intent(view.getContext(), MapsPage.class);
                    startActivityForResult(mapIntent, 0);
                }
                else {
                    Toast.makeText(LoginPage.this, "Username and Password does not match existing user", Toast.LENGTH_SHORT).show();
                }
            }
        });



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
package p8.group3.ida.aau.p8_group3;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        TextView txchimp = (TextView)findViewById(R.id.loginTitle);
        Typeface custom_font= Typeface.createFromAsset(getAssets(),"KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView)findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txLogin = (TextView)findViewById(R.id.login);
        txLogin.setTypeface(custom_font);

        TextView txForgotPass = (TextView)findViewById(R.id.forgotPassword);
        txForgotPass.setTypeface(custom_font);

        TextView txPass = (TextView)findViewById(R.id.password);
        txPass.setTypeface(custom_font);

        TextView txSignIn = (TextView)findViewById(R.id.signIn);
        txSignIn.setTypeface(custom_font);

        TextView txCreateAcc= (TextView)findViewById(R.id.createAccount);
        txCreateAcc.setTypeface(custom_font);




        Button createAccountButton = (Button) findViewById(R.id.createAccount);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CreateAccountPage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
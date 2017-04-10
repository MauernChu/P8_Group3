package p8.group3.ida.aau.p8_group3;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        TextView txLogin= (TextView)findViewById(R.id.Login);
        txLogin.setTypeface(custom_font);

        TextView txForgotPass= (TextView)findViewById(R.id.ForgotPassword);
        txForgotPass.setTypeface(custom_font);

        TextView txPass= (TextView)findViewById(R.id.Password);
        txPass.setTypeface(custom_font);

        TextView txSignIn= (TextView)findViewById(R.id.SignIn);
        txSignIn.setTypeface(custom_font);

        TextView txCreateAcc= (TextView)findViewById(R.id.CreateAccount);
        txCreateAcc.setTypeface(custom_font);

    }
}
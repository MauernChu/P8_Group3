package p8.group3.ida.aau.p8_group3.Presenter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import p8.group3.ida.aau.p8_group3.R;


public class CreateAccountPage extends AppCompatActivity {

    EditText createUsername;
    EditText createPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);

        createUsername = (EditText) findViewById(R.id.createUsername);
        createPassword = (EditText) findViewById(R.id.createPassword);

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
                startActivityForResult(intent, 0);
            }
        });
    }
}

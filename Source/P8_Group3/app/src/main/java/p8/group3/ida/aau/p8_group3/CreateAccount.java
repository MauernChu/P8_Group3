package p8.group3.ida.aau.p8_group3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by christosmentzelos on 10/04/2017.
 */

public class CreateAccount extends LoginPage {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        TextView txchimp = (TextView)findViewById(R.id.loginTitle);
        Typeface custom_font= Typeface.createFromAsset(getAssets(),"KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView)findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txLogin2 = (TextView)findViewById(R.id.login2);
        txLogin2.setTypeface(custom_font);

        TextView txPass = (TextView)findViewById(R.id.password);
        txPass.setTypeface(custom_font);

        TextView txRepPass = (TextView)findViewById(R.id.repeat_password);
        txRepPass.setTypeface(custom_font);

        TextView txEmail = (TextView)findViewById(R.id.email);
        txEmail.setTypeface(custom_font);

        TextView txNext = (TextView)findViewById(R.id.next);
        txNext.setTypeface(custom_font);

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateAccount2.class);
                startActivityForResult(intent, 0);
            }
        });

}
}

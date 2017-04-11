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

public class CreateAccountSecondPage extends CreateAccountPage {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_second_page);

        TextView txchimp = (TextView)findViewById(R.id.loginTitle);
        Typeface custom_font= Typeface.createFromAsset(getAssets(),"KGCorneroftheSky.ttf");
        txchimp.setTypeface(custom_font);

        TextView txSocforPa = (TextView)findViewById(R.id.purpose);
        txSocforPa.setTypeface(custom_font);

        TextView txFinish = (TextView)findViewById(R.id.finish);
        txFinish.setTypeface(custom_font);

        TextView txCity = (TextView)findViewById(R.id.city);
        txCity.setTypeface(custom_font);

        TextView txNumberOfChildren = (TextView)findViewById(R.id.numberOfChildren);
        txNumberOfChildren.setTypeface(custom_font);

        TextView txAgeOfChildren = (TextView)findViewById(R.id.ageOfChildren);
        txAgeOfChildren.setTypeface(custom_font);

        Button createAccountButton = (Button) findViewById(R.id.finish);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ProfilePage.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}


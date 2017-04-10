package p8.group3.ida.aau.p8_group3;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by christosmentzelos on 10/04/2017.
 */

public class CreateAccount2 extends CreateAccount {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account2);

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
    }
}


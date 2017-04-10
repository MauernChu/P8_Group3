package p8.group3.ida.aau.p8_group3;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by karatinka on 10.4.2017.
 */

public class ProfilePage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Typeface custom_font= Typeface.createFromAsset(getAssets(),"KGCorneroftheSky.ttf");

        TextView txName = (TextView) findViewById(R.id.name);
        txName.setTypeface(custom_font);

        TextView txChildren = (TextView) findViewById(R.id.children);
        txChildren.setTypeface(custom_font);

        TextView txAge = (TextView) findViewById(R.id.age);
        txAge.setTypeface(custom_font);

        TextView txCity = (TextView) findViewById(R.id.city);
        txCity.setTypeface(custom_font);

        TextView txLanguages = (TextView) findViewById(R.id.languages);
        txLanguages.setTypeface(custom_font);

        TextView txAllLanguages = (TextView) findViewById(R.id.all_languages);
        txAllLanguages.setTypeface(custom_font);

        TextView txAbout = (TextView) findViewById(R.id.about);
        txAbout.setTypeface(custom_font);

        TextView txTextAbout = (TextView) findViewById(R.id.text_about);
        txTextAbout.setTypeface(custom_font);

        TextView txHobbies = (TextView) findViewById(R.id.hobbies);
        txHobbies.setTypeface(custom_font);
    }
}

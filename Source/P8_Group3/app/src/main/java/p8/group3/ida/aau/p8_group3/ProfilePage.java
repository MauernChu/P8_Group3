package p8.group3.ida.aau.p8_group3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by karatinka on 10.4.2017.
 */

public class ProfilePage extends BaseActivity{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        getSupportActionBar().setTitle("Profile");

       /* Typeface custom_font= Typeface.createFromAsset(getAssets(),"KGCorneroftheSky.ttf");

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

        TextView txAllLanguages = (TextView) findViewById(R.id.allLanguages);
        txAllLanguages.setTypeface(custom_font);

        TextView txAbout = (TextView) findViewById(R.id.about);
        txAbout.setTypeface(custom_font);

        TextView txTextAbout = (TextView) findViewById(R.id.textAbout);
        txTextAbout.setTypeface(custom_font);

        TextView txHobbies = (TextView) findViewById(R.id.hobbies);
        txHobbies.setTypeface(custom_font);
        */

       //Button for finishing editing
        final Button done = (Button) findViewById(R.id.doneEdit);

        //Variables that change visibility.
        final TextView name = (TextView) findViewById(R.id.name);
        final EditText editedName = (EditText) findViewById(R.id.editName);
        final TextView children = (TextView) findViewById(R.id.children);
        final EditText editedChildren = (EditText) findViewById(R.id.editChildren);
        final TextView ageOfChildren = (TextView) findViewById(R.id.age);
        final EditText editedAge = (EditText) findViewById(R.id.editAge);
        final TextView city = (TextView) findViewById(R.id.city);
        final EditText editedCity = (EditText) findViewById(R.id.editCity);
        final ImageView pencil = (ImageView) findViewById(R.id.pencilIconEdit);
        final TextView languages = (TextView) findViewById(R.id.allLanguages);
        final EditText editedLanguages = (EditText) findViewById(R.id.editLanguages);
        final TextView about = (TextView) findViewById(R.id.textAbout);
        final EditText editedAbout = (EditText) findViewById(R.id.editAbout);

        //Button to edit
        final Button editProfileButton = (Button) findViewById(R.id.editProfile);
        editProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name.setVisibility(View.GONE);
                editedName.setVisibility(View.VISIBLE);
                children.setVisibility(View.GONE);
                editedChildren.setVisibility(View.VISIBLE);
                ageOfChildren.setVisibility(View.GONE);
                editedAge.setVisibility(View.VISIBLE);
                city.setVisibility(View.GONE);
                editedCity.setVisibility(View.VISIBLE);
                languages.setVisibility(View.GONE);
                editedLanguages.setVisibility(View.VISIBLE);
                about.setVisibility(View.GONE);
                editedAbout.setVisibility(View.VISIBLE);

                //Changing visibility of edit and done buttons
                editProfileButton.setVisibility(View.GONE);
                done.setVisibility(View.VISIBLE);
                pencil.setVisibility(View.GONE);

            }
        });

        //Remainder of code for button to finish editing
        done.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                name.setVisibility(View.VISIBLE);
                editedName.setVisibility(View.GONE);
                children.setVisibility(View.VISIBLE);
                editedChildren.setVisibility(View.GONE);
                ageOfChildren.setVisibility(View.VISIBLE);
                editedAge.setVisibility(View.GONE);
                city.setVisibility(View.VISIBLE);
                editedCity.setVisibility(View.GONE);
                languages.setVisibility(View.VISIBLE);
                editedLanguages.setVisibility(View.GONE);
                about.setVisibility(View.VISIBLE);
                editedAbout.setVisibility(View.GONE);

                //Changing visibility of edit and done buttons
                editProfileButton.setVisibility(View.VISIBLE);
                done.setVisibility(View.GONE);
                pencil.setVisibility(View.VISIBLE);

            }
        });
    }
}

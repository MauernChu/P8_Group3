package p8.group3.ida.aau.p8_group3;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Camil on 25/04/2017.
 */

public class CreateEvent extends BaseActivity implements AdapterView.OnItemSelectedListener{


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);


        //These are just for the font changes for the text
        TextView txCreateEvent = (TextView)findViewById(R.id.createEventTitle);
        Typeface custom_font= Typeface.createFromAsset(getAssets(),"KGCorneroftheSky.ttf");
        txCreateEvent.setTypeface(custom_font);

        TextView txEventTitle = (TextView) findViewById(R.id.eventTitle);
        txEventTitle.setTypeface(custom_font);

        TextView txDate = (TextView) findViewById(R.id.eventDate);
        txDate.setTypeface(custom_font);

        TextView txTime = (TextView) findViewById(R.id.eventTime);
        txTime.setTypeface(custom_font);

        TextView txAddress = (TextView) findViewById(R.id.eventAddress);
        txAddress.setTypeface(custom_font);

        TextView txEventDescription = (TextView) findViewById(R.id.eventDescription);
        txEventDescription.setTypeface(custom_font);



        //This is for the dropdown menu (a spinner) for Event Type selection by the user
        Spinner dropdown = (Spinner) findViewById(R.id.eventType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.event_types_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

    }

    //These methods should be filled out according to how we want to respond to the selections from the dropdown menu

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }
}

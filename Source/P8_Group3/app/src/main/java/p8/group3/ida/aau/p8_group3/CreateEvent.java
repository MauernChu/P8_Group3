package p8.group3.ida.aau.p8_group3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Camil on 25/04/2017.
 */

public class CreateEvent extends BaseActivity{


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);


        Spinner dropdown = (Spinner) findViewById(R.id.eventType);
        /* First attempt!
        String[] eventTypes = new String[]{"Playground", "Library", "Swimming Pool"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, eventTypes);
        */

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.event_types_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

    }




}

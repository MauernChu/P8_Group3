package p8.group3.ida.aau.p8_group3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by christosmentzelos on 10/04/2017.
 */

public class CreateAccount extends LoginPage {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);


        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateAccount2.class);
                startActivityForResult(intent, 0);
            }
        });

}
}

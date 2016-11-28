package cs514.iastate.edu.beaconminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BeaconDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_detail);
        Intent intent = getIntent();
        String beaconID = intent.getStringExtra(MainActivity.EXTRA_BEACON_ID);
        String binding = intent.getStringExtra(MainActivity.EXTRA_BINDING);
        TextView beaconIDValue = (TextView) findViewById(R.id.beacon_id_value);
        TextView bindingValue = (TextView) findViewById(R.id.binding_value);
        beaconIDValue.setText(beaconID);
        bindingValue.setText(binding);
    }
}

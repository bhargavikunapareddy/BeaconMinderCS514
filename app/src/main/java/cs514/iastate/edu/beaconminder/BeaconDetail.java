package cs514.iastate.edu.beaconminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BeaconDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_detail);
        final Intent intent = getIntent();
        final String beaconID = intent.getStringExtra(MainActivity.EXTRA_BEACON_ID);
        String binding = intent.getStringExtra(MainActivity.EXTRA_BINDING);
        TextView beaconIDValue = (TextView) findViewById(R.id.beacon_id_value);
        TextView bindingValue = (TextView) findViewById(R.id.binding_value);
        Button deleteBtn = (Button) findViewById(R.id.delete_btn);
        beaconIDValue.setText(beaconID);
        bindingValue.setText(binding);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BeaconDetail.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to delete this beacon?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent1 = new Intent();
                                intent1.putExtra("BEACON_ID", beaconID);
                                setResult(1, intent1);
                                BeaconDetail.this.finish();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}

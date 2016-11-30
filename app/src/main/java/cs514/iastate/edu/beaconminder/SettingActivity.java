package cs514.iastate.edu.beaconminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class SettingActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView textView;
    private Button deleteBtn;
    private ArrayList<String> sourceItem;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final ArrayList<ItemData> list;
        sourceItem = new ArrayList<>();
        list = (ArrayList<ItemData>) getIntent().getSerializableExtra("sourceItem");
        for (ItemData i : list) {
            sourceItem.add(i.getBinding());
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.beacons_id_value);
        deleteBtn = (Button) findViewById(R.id.delete_beacon);
        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setChecked(list.get(0).getIndicatorID() == R.drawable.online);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sourceItem);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData item = list.get(i);
                textView.setText(item.getBeaconID());
                mSwitch.setChecked(item.getIndicatorID() == R.drawable.online);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to delete this beacon?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent1 = new Intent();
                                intent1.putExtra("BEACON_ID_FROM_SETTING", textView.getText());
                                setResult(2, intent1);
                                SettingActivity.this.finish();
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

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent intent = new Intent();
                intent.putExtra("BEACON_ID_FROM_SETTING", textView.getText());
                intent.putExtra("beacon_switch", b);
                setResult(4, intent);
            }
        });
    }
}

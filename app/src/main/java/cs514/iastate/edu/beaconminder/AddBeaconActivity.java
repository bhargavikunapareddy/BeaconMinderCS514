package cs514.iastate.edu.beaconminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBeaconActivity extends AppCompatActivity {

    private Button addBtn;
    private EditText beaconIDInput;
    private EditText beaconBindingInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);
        addBtn = (Button) findViewById(R.id.add_btn);
        beaconIDInput = (EditText) findViewById(R.id.editText);
        beaconBindingInput = (EditText) findViewById(R.id.editText2);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beaconID = beaconIDInput.getText().toString();
                String binding = beaconBindingInput.getText().toString();
                int iconID = R.drawable.default_icon;
                int indicatorID = R.drawable.offline;
                ItemData itemData = new ItemData(binding, beaconID, binding, iconID, indicatorID);
                Intent intent1 = new Intent();
                intent1.putExtra("newly_added_beacon", itemData);
                setResult(MainActivity.ADD_REQUEST_CODE, intent1);
                AddBeaconActivity.this.finish();
            }
        });
    }
}

package cs514.iastate.edu.beaconminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_BEACON_ID = "edu.iastate.com514.BEACON_ID";
    public final static String EXTRA_BINDING = "edu.iastate.com514.BINDING";
    public final static int DETAIL_REQUEST_CODE = 1;
    public final static int SETTING_REQUEST_CODE = 2;
    public static ArrayList<ItemData> sourceItem;
    public static Map<String, ItemData> beaconMap;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;
    private TextView logText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceItem = new ArrayList<>();
        ItemData item1 = new ItemData("wallet", "aaaaaaaaaa", "Wallet", R.drawable.wallet, R.drawable.offline);
        ItemData item2 = new ItemData("keys", "bbbbbbbbbb", "Keys", R.drawable.keys, R.drawable.online);
        ItemData item3 = new ItemData("glasses", "cccccccccc", "Glasses", R.drawable.glasses, R.drawable.offline);
//        ItemData item4 = new ItemData("meds", "dddddddddd", "Meds",R.drawable.meds, R.drawable.offline);
        final ItemData item4 = new ItemData("meds", "dddddddddd", "Meds",R.drawable.meds, R.drawable.offline);
        beaconMap = new HashMap< >();
        beaconMap.put("X3VP-G6UR1", item1);
//        ItemData item5 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
//        ItemData item6 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
//        ItemData item7 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
//        ItemData item8 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
//        ItemData item9 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
//        ItemData item10 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
//        ItemData item11 = new ItemData("meds", R.drawable.meds, R.drawable.offline);
        sourceItem.add(item1);
        sourceItem.add(item2);
        sourceItem.add(item3);
        sourceItem.add(item4);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(sourceItem, new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemData itemData) {
                Intent intent = new Intent(MainActivity.this, BeaconDetail.class);
                intent.putExtra(MainActivity.EXTRA_BEACON_ID, itemData.getBeaconID());
                intent.putExtra(MainActivity.EXTRA_BINDING, itemData.getBinding());
                startActivityForResult(intent, DETAIL_REQUEST_CODE);
            }
        });
        mRecyclerView.setAdapter(adapter);
        logText = (TextView)findViewById(R.id.textView);
        logText.setText("Place Holder");
        InitializeBeacon.intializeGimbal(this.getApplication());
        InitializeBeacon.initializeBeaconMapping(beaconMap, mRecyclerView, adapter, logText);

        Button settingBtn = (Button) findViewById(R.id.settings);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra("sourceItem", sourceItem);
                startActivityForResult(intent, SETTING_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED && data != null) {
            if (requestCode == DETAIL_REQUEST_CODE) {
                String s = data.getStringExtra("BEACON_ID");
                for (int i = 0; i < sourceItem.size(); i++) {
                    if (sourceItem.get(i).getBeaconID().equals(s)) {
                        sourceItem.remove(i);
                        adapter.notifyItemRemoved(i);
                        break;
                    }
                }
            }
            if (requestCode == SETTING_REQUEST_CODE) {
                String s = data.getStringExtra("BEACON_ID_FROM_SETTING");
                for (int i = 0; i < sourceItem.size(); i++) {
                    if (sourceItem.get(i).getBeaconID().equals(s)) {
                        sourceItem.remove(i);
                        adapter.notifyItemRemoved(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        InitializeBeacon.destroyGimbal(this.getApplication());
        super.onDestroy();
    }
}

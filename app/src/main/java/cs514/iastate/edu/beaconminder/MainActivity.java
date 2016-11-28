package cs514.iastate.edu.beaconminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_BEACON_ID = "edu.iastate.com514.BEACON_ID";
    public final static String EXTRA_BINDING = "edu.iastate.com514.BINDING";
    private List<ItemData> sourceItem;
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
        final ItemData item4 = new ItemData("meds", "dddddddddd", "Meds",R.drawable.meds, R.drawable.offline);
        final Map<String,ItemData> beaconMap = new HashMap< >();
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
//        sourceItem.add(item5);
//        sourceItem.add(item6);
//        sourceItem.add(item7);
//        sourceItem.add(item8);
//        sourceItem.add(item9);
//        sourceItem.add(item10);
//        sourceItem.add(item11);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(MainActivity.this, sourceItem);
        mRecyclerView.setAdapter(adapter);
        logText = (TextView)findViewById(R.id.textView);
        logText.setText("Place Holder");
        InitializeBeacon.intializeGimbal(this.getApplication());
        InitializeBeacon.initializeBeaconMapping(beaconMap, mRecyclerView, adapter, logText);
    }

    @Override
    protected void onDestroy() {
        InitializeBeacon.destroyGimbal(this.getApplication());
        super.onDestroy();
    }
}

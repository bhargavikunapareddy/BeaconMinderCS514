package cs514.iastate.edu.beaconminder;

import android.app.Application;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gimbal.android.Beacon;
import com.gimbal.android.BeaconEventListener;
import com.gimbal.android.BeaconManager;
import com.gimbal.android.BeaconSighting;
import com.gimbal.android.Gimbal;

import java.util.Map;

/**
 * Created by nishanth on 11/28/16.
 */

public class InitializeBeacon {
    public static BeaconManager manager;
    public static void intializeGimbal(Application app){
        Gimbal.setApiKey(app, "45894d63-3040-4c34-9e92-a720c9971f0f");
        if(!Gimbal.isStarted()) {
            Gimbal.start();
        }
        manager = new BeaconManager();
        manager.startListening();
    }

    public static void destroyGimbal(Application app){
        if(manager != null){
            manager.stopListening();
        }
        if(Gimbal.isStarted()){
            Gimbal.stop();
        }
    }

    public static void initializeBeaconMapping(final Map<String,ItemData> beaconMapping, final RecyclerView view,
                                               final MyRecyclerViewAdapter adapter, final TextView logText){
        BeaconEventListener eventListener = new BeaconEventListener() {
            @Override
            public void onBeaconSighting(BeaconSighting beaconSighting) {
                Beacon beacon = beaconSighting.getBeacon();
                int rssi = beaconSighting.getRSSI();
                logText.setText(rssi+"");
                int indicator = R.drawable.online;
                if(rssi > -30 || rssi < -50){
                    indicator = R.drawable.offline;
                }
                beaconMapping.get(beacon.getIdentifier()).setIndicatorID(indicator);
                view.setAdapter(adapter);
            }
        };
        manager.addListener(eventListener);
    }
}

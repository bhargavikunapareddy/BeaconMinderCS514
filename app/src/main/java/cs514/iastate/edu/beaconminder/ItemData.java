package cs514.iastate.edu.beaconminder;


import java.io.Serializable;

/**
 * Created by yi on 11/27/16.
 */

public class ItemData implements Serializable{
    private String title;
    private String beaconID;
    private String binding;
    private int iconID;
    private int indicatorID;

    public String getBeaconID() {
        return beaconID;
    }


    public String getBinding() {
        return binding;
    }


    public ItemData(String title, String beaconID, String binding, int iconID, int indicatorID) {
        this.beaconID = beaconID;
        this.binding = binding;
        this.title = title;
        this.iconID = iconID;
        this.indicatorID = indicatorID;
    }

    public String getTitle() {
        return title;
    }


    public int getIconID() {
        return iconID;
    }


    public int getIndicatorID() {
        return indicatorID;
    }

}

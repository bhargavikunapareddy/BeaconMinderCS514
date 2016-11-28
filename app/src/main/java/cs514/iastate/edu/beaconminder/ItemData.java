package cs514.iastate.edu.beaconminder;

/**
 * Created by yi on 11/27/16.
 */

public class ItemData {
    private String title;
    private String beaconID;
    private String binding;
    private int iconID;
    private int indicatorID;

    public String getBeaconID() {
        return beaconID;
    }

    public void setBeaconID(String beaconID) {
        this.beaconID = beaconID;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getIndicatorID() {
        return indicatorID;
    }

    public void setIndicatorID(int indicatorID) {
        this.indicatorID = indicatorID;
    }
}

package cs514.iastate.edu.beaconminder;

/**
 * Created by yi on 11/27/16.
 */

public class ItemData {
    private String title;
    private int iconID;
    private int indicatorID;

    public ItemData(String title, int iconID, int indicatorID) {
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

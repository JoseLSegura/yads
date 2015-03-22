package yads.vigilando.org.yetanotherdailyselfie;

public class DrawerRow {
    private int iconId;
    private String title;
    private boolean isSeparator = false;

    public DrawerRow() {
        isSeparator = true;
    }

    public DrawerRow(int resIcon, String the_title) {
        title = the_title;
        iconId = resIcon;
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public String getTitle() {
        return title;
    }

    public int getIconId() {
        return iconId;
    }
}

package yads.vigilando.org.yetanotherdailyselfie;

public class ImageCell {
    private String mDate;
    private String mUri;

    public ImageCell(String date, String uri) {
        mDate = date;
        mUri = uri;
    }

    public String getDate() {
        return mDate;
    }
    public String getUri() { return mUri; }
}

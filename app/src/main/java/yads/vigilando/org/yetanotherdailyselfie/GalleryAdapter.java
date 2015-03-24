package yads.vigilando.org.yetanotherdailyselfie;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {
    private ArrayList<ImageCell> list = new ArrayList<>();
    private ArrayList<Integer> drawableIds = new ArrayList<>();
    private Context mContext;

    private String mDirectory;

    public GalleryAdapter(Context context, String directory) {
        mContext = context;
        mDirectory = directory;

        File dir = new File(directory);
        File[] files = dir.listFiles();

        for(File f: files) {
            String date = f.getName();

            ImageCell cell = new ImageCell(date, f.getAbsolutePath());

        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        ViewHolder holder;

        ImageCell curr = list.get(position);

        if (null == convertView) {
            holder = new ViewHolder();
            newView = new ImageView(mContext);
            holder.photo = (ImageView) newView;
            holder.title = null;
            newView.setTag(holder);
        }

        else {
            holder = (ViewHolder) newView.getTag();
        }

        holder.photo.setImageURI(Uri.parse(curr.getUri()));


        return newView;
    }

    static class ViewHolder {
        ImageView photo;
        TextView title;
    }

}

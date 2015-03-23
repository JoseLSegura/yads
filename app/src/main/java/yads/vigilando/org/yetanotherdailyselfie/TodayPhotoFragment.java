package yads.vigilando.org.yetanotherdailyselfie;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class TodayPhotoFragment extends Fragment {
    private static final String ARG_PHOTO = "data";

    private Activity mActivity;
    private ImageView mImageView = null;

    public TodayPhotoFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_today_photo, container, false);

        mImageView = (ImageView) rootView.findViewById(R.id.dailySelfie);

        if (getArguments() != null) {
            Bitmap bitmap = (Bitmap) getArguments().get(ARG_PHOTO);
            mImageView.setImageBitmap(bitmap);
            File outFile = new File(mActivity.getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "test.jpg");
            storeBitmap(outFile, bitmap);
        } else {
            mImageView.setImageResource(android.R.drawable.presence_online);
        }
        return rootView;
    }

    private boolean storeBitmap(File outFile, Bitmap bitmap) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                FileOutputStream os = new FileOutputStream(outFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                return true;
            }
            catch(FileNotFoundException e) {
                return false;
            }
        }

        return false;
    }
}

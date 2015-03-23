package yads.vigilando.org.yetanotherdailyselfie;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class TodayPhotoFragment extends Fragment {
    private static final String ARG_PHOTO = "data";
    private Bitmap mBitmap = null;

    public TodayPhotoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_today_photo, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.dailySelfie);

        if (getArguments() != null) {
            Bitmap bitmap = (Bitmap) getArguments().get(ARG_PHOTO);
            imageView.setImageBitmap(bitmap);
        }
        else {
            imageView.setImageResource(android.R.drawable.presence_online);
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

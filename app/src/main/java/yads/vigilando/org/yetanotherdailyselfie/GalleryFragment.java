package yads.vigilando.org.yetanotherdailyselfie;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class GalleryFragment extends Fragment {
    private GridView mGridView = null;
    private GalleryAdapter mAdapter = null;

    public GalleryFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        mAdapter = new GalleryAdapter(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        mGridView = (GridView) rootView.findViewById(R.id.gallery_grid);
        mGridView.setAdapter(mAdapter);
        return rootView;
    }
}

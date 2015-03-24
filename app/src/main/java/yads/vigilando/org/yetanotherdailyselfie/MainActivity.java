package yads.vigilando.org.yetanotherdailyselfie;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private DrawerRowAdapter mDrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    /* Variables used for restore the application status when re-creating the activity */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private int mCurrentSelectedPosition = 0;

    /* For take picture logic */
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navigation_drawer);

        mDrawerAdapter = new DrawerRowAdapter(this);
        setupNavDrawerItems();
        mDrawerList.setAdapter(mDrawerAdapter);
        mDrawerList.setItemChecked(mCurrentSelectedPosition, true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
        }

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setupNavDrawerItems() {
        mDrawerAdapter.add(new DrawerRow(android.R.drawable.ic_menu_camera,
                getString(R.string.today_photo)));
        mDrawerAdapter.add(new DrawerRow(android.R.drawable.ic_menu_gallery,
                getString(R.string.view_photos)));
        mDrawerAdapter.add(new DrawerRow(R.drawable.ic_action,
                getString(R.string.view_timelapse)));
        mDrawerAdapter.add(new DrawerRow(android.R.drawable.ic_menu_preferences,
                getString(R.string.settings)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.take_photo).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.take_photo) {
            dispatchTakePictureIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            // Bitmap imageBitmap = (Bitmap) extras.get("data");
            // mImageView.setImageBitmap(imageBitmap);
            /* TODO:
            * 1.- Load the today's picture fragment
            * 2.- Update the selected drawer item
            * 3.- put the bitmap into the fragment's ImageView            *
            * */

            Fragment fragment = new TodayPhotoFragment();
            fragment.setArguments(extras);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
         }
    }
    private void selectItem(int position) {
        Fragment fragment = null;

        switch(position) {
            case 0:
                fragment = new TodayPhotoFragment();
                break;

            case 1:
                fragment = new GalleryFragment();
                break;

            case 2:
                Toast.makeText(this, "Position 2", Toast.LENGTH_LONG).show();
                break;

            case 3:
                Toast.makeText(this, "Position 3", Toast.LENGTH_LONG).show();
                break;

            default:
                Toast.makeText(this, "Position " + position, Toast.LENGTH_LONG).show();
                break;
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);

        if (fragment == null) return;

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();


    }
}

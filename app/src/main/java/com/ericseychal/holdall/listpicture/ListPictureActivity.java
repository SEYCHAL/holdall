package com.ericseychal.holdall.listpicture;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.ericseychal.holdall.R;
import com.ericseychal.holdall.dbflow.PicturesManager;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class ListPictureActivity extends AppCompatActivity implements FlickrResponseListener, AdapterView.OnItemSelectedListener {
    public static final String NUMBER_PHOTOS = "number_photos";
    public static final String SPINNER_POSITION = "spinner_position";
    public static final String IS_SEARCH_BUTTON = "is_search_button";
    public static final String PICTURE = "picture";
    public static final String MSTB_BUTTON1 = "Find";
    public static final String MSTB_BUTTON2 = "Historic";

    private FlickrService flickrService;
    boolean bound = false;

    private AdapterListPicture adapterListPicture;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SharedPreferences sharedPreferences;
    private EditText editText;
    private FloatingActionButton fab;
    private LinearLayout linearLayoutSearch;
    private DrawerLayout drawerLayout;

    private String numberPhotos = "5";
    private int spinnerSelectionPosition = 0;
    private boolean isSearchButton = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_picture);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        initSharePreference();
        initLinearLayoutSearch();
        initListView();
        initDrawer();
        intitMultiStateToggleButton();
        initSpinner();
        visibleButtonSearch(isSearchButton);
    }

    // ============================== Bound Management ====================================
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, FlickrService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            flickrService = binder.getService();
            flickrService.setFlickrResponseListener(ListPictureActivity.this);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    @Override
    public void onPicturesReceived(List<Pictures> picturesList) {
        adapterListPicture.setPicturesList(picturesList);
    }

    // ============================== Search Layout ====================================
    private void initLinearLayoutSearch() {
        editText = (EditText) findViewById(R.id.list_picture_edittext);
        fab = (FloatingActionButton) findViewById(R.id.list_picture_fab);
        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);
        linearLayoutSearch = (LinearLayout) findViewById(R.id.linear_layout_search);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ListPictureActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                String query = editText.getText().toString();

                if (bound && !query.equals("")) {
                    flickrService.getListPicture(editText.getText().toString(), numberPhotos);
                }
            }
        });
    }

    // ============================== ListView ====================================
    private void initListView() {
        adapterListPicture = new AdapterListPicture(this);
        final PicturesManager picturesManager = new PicturesManager(this);
        ListView listView = (ListView) findViewById(R.id.list_picture);
        listView.setAdapter(adapterListPicture);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savePictureToHistoric(adapterListPicture.getItem(position));
                Intent intent = new Intent(ListPictureActivity.this, ShowOnePictureActivity.class);
                intent.putExtra(PICTURE, adapterListPicture.getItem(position));
                startActivity(intent);
            }
        });

    }

    // ============================== Drawer ====================================
    private void initDrawer() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        LinearLayout leftDrawer = (LinearLayout) findViewById(R.id.left_drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.app_name,
                R.string.app_name
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

        // ============================== MultiStateToggleButton ====================================
    private void intitMultiStateToggleButton() {
        MultiStateToggleButton mstbButton = (MultiStateToggleButton) findViewById(R.id.mstb_button);
        List<String> titleButton = new ArrayList<>();
        titleButton.add(MSTB_BUTTON1);
        titleButton.add(MSTB_BUTTON2);
        boolean[] value = new boolean[titleButton.size()];

        if (isSearchButton) {
            value[0] = true;
        } else {
            value[1] =true;
        }
        mstbButton.setElements(titleButton,value);
        mstbButton.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if (position == 0) {                // Find button
                    visibleButtonSearch(true);
                    saveSharePreference();
                    drawerLayout.closeDrawers();
                    adapterListPicture.setHistoric(false);
                    adapterListPicture.setPicturesList(new ArrayList<Pictures>());
                } else if(position == 1) {          // Historic button
                    visibleButtonSearch(false);
                    saveSharePreference();
                    showHistoric();
                    drawerLayout.closeDrawers();
                }
            }
        });
    }

    // ======================== DrawerLayout ===============================
    // Methode tell by Drawer
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    // Methode tell by Drawer
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    // Methode tell by Drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    // ============================== Spinner ====================================
    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nbre_pictures_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(spinnerSelectionPosition);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        numberPhotos = parent.getItemAtPosition(position).toString();
        spinnerSelectionPosition = parent.getSelectedItemPosition();

        saveSharePreference();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    // ============================== other methodes ====================================
    private void initSharePreference() {
        numberPhotos = sharedPreferences.getString(NUMBER_PHOTOS, "5");
        spinnerSelectionPosition = sharedPreferences.getInt(SPINNER_POSITION, 0);
        isSearchButton = sharedPreferences.getBoolean(IS_SEARCH_BUTTON,true);
    }

    private void saveSharePreference() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NUMBER_PHOTOS, numberPhotos);
        editor.putInt(SPINNER_POSITION, spinnerSelectionPosition);
        editor.putBoolean(IS_SEARCH_BUTTON, isSearchButton);
        editor.commit();
    }

    private void visibleButtonSearch(boolean value) {
        if (value) {
            linearLayoutSearch.setVisibility(View.VISIBLE);
            adapterListPicture.setHistoric(false);
        } else {
            linearLayoutSearch.setVisibility(View.GONE);
            adapterListPicture.setHistoric(true);
            showHistoric();
        }
        isSearchButton = value;
    }

    private void showHistoric() {
        PicturesManager picturesManager = new PicturesManager(this);
        List<Pictures> picturesList = new ArrayList<>();
        adapterListPicture.setPicturesList(picturesManager.getAll());
    }

    private void savePictureToHistoric(Pictures pictures) {
        PicturesManager picturesManager = new PicturesManager(this);
        Pictures pictures1 = picturesManager.getByUrl(pictures.getUrl());
        if (pictures1 == null) {
            picturesManager.save(pictures);
        }
    }
}

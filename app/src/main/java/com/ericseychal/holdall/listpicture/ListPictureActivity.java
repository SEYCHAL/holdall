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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ericseychal.holdall.R;

import java.util.List;

public class ListPictureActivity extends AppCompatActivity implements FlickrResponseListener, AdapterView.OnItemSelectedListener {
    final String NUMBER_PHOTOS = "number_photos";
    final String SPINNER_POSITION = "spinner_position";

    private FlickrService flickrService;
    boolean bound = false;
//    List<Pictures> listPicture = new ArrayList<>();

    private AdapterListPicture adapterListPicture;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private SharedPreferences sharedPreferences;

    private Spinner spinner;
    private String numberPhotos = "5";
    private int spinnerSelectionPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_picture);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        initSharePreference();
        initDrawer();
        initSpinner();

        adapterListPicture = new AdapterListPicture(this);
        final EditText editText = (EditText) findViewById(R.id.list_picture_edittext);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.list_picture_fab);
        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);

        ListView listView = (ListView) findViewById(R.id.list_picture);
        listView.setAdapter(adapterListPicture);
//        adapterListPicture.setPicturesList(listPicture);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListPictureActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                String query = editText.getText().toString();

                if (bound && !query.equals("")) {
                    flickrService.getListPicture(editText.getText().toString(), numberPhotos);
//                    adapterListPicture.setPicturesList(listPicture);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListPictureActivity.this, ShowOnePictureActivity.class);
                intent.putExtra("picture", adapterListPicture.getItem(position));
//                intent.putExtra("picture", listPicture.get(position));
                startActivity(intent);
            }
        });
    }


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
        spinner = (Spinner) findViewById(R.id.spinner);
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

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NUMBER_PHOTOS, numberPhotos);
        editor.putInt(SPINNER_POSITION, spinnerSelectionPosition);
        editor.commit();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void initSharePreference() {
        numberPhotos = sharedPreferences.getString(NUMBER_PHOTOS, "5");
        spinnerSelectionPosition = sharedPreferences.getInt(SPINNER_POSITION, 0);
    }
}

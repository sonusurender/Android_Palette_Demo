package com.androidpalette_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by sonu on 27/03/17.
 */

public class RecyclerView_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);
        setUpToolbar();
        setUpRecyclerView();
    }

    /*  Set Up toolbar  */
    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.recycler_view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*  Set Up Recycler View */
    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        getRecyclerViewArrayList(recyclerView);
    }

    /*  generate array list and set adapter over recycler view  */
    private void getRecyclerViewArrayList(RecyclerView recyclerView) {
        ArrayList<RecyclerItemModel> recyclerItemModels = new ArrayList<>();
        recyclerItemModels.add(new RecyclerItemModel("Google Chrome", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Google_Chrome_for_Android_Icon_2016.svg/2000px-Google_Chrome_for_Android_Icon_2016.svg.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Now", "https://cdn.pixabay.com/photo/2015/10/31/12/56/google-1015752_960_720.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Plus", "https://upload.wikimedia.org/wikipedia/commons/f/fb/Google-plus-circle-icon-png.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Photos", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Google-Picasa-icon-logo.png/1016px-Google-Picasa-icon-logo.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Maps", "http://maxpixel.freegreatpicture.com/static/photo/1x/Maps-Gps-Map-Icon-Google-Maps-Logo-Navigation-1797882.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Play Music", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Play_music_triangle.svg/2000px-Play_music_triangle.svg.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Play Store", "https://cdn.pixabay.com/photo/2016/08/31/00/49/google-1632434_960_720.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Drive", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Google_Drive_Logo.svg/1804px-Google_Drive_Logo.svg.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Allo", "https://upload.wikimedia.org/wikipedia/commons/d/d0/Google_Allo_Logo.png"));
        recyclerItemModels.add(new RecyclerItemModel("Google Contacts", "https://upload.wikimedia.org/wikipedia/commons/b/b7/Google_Contacts_logo.png"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, recyclerItemModels);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Finish activity on home back button click
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

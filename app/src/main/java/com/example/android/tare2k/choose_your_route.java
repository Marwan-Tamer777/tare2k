package com.example.android.tare2k;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.tare2k.DataAndUtillity.MicrobusesContract;
import com.example.android.tare2k.DataAndUtillity.MicrobusesSQLiteHelper;
import com.example.android.tare2k.DataAndUtillity.MicrobussesCursorAdapter;
import com.example.android.tare2k.DataAndUtillity.Utillity;

import java.util.concurrent.Callable;

public class choose_your_route extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener {

    private final Integer  LOADERID = 0;
    private String[] selectionArgs;
    MicrobussesCursorAdapter microbussesCursorAdapter;
    private  String selection;
    private Spinner fromSpinner;
    private Spinner toSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_route);

        ListView microbusesList = findViewById(R.id.choose_your_route_list);
        LinearLayout emptyCase = findViewById(R.id.choose_empty_case);
        microbusesList.setEmptyView(emptyCase);
        microbussesCursorAdapter = new MicrobussesCursorAdapter(this, null,MicrobusesContract.mainListItem);
        microbusesList.setAdapter(microbussesCursorAdapter);

        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        Utillity.setupSpinner(this, fromSpinner);
        Utillity.setupSpinner(this, toSpinner);
        fromSpinner.setOnItemSelectedListener(this);
        toSpinner.setOnItemSelectedListener(this);

        TextView emptyCaseTextView = findViewById(R.id.empty_case_text);
        emptyCaseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, "tare2ak@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "i would like to share a stop i know");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        microbusesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(choose_your_route.this, microbus_info.class);
                Uri microbus_uri = ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI, id);
                intent.setData(microbus_uri);
                startActivity(intent);
            }
        });
        initLodaer();
    }

    public void initLodaer(){
        getLoaderManager().initLoader(LOADERID,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.choose_your_route_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            case R.id.action_make_your_route:
                Intent intent0 = new Intent(choose_your_route.this,make_your_route.class);
                startActivity(intent0);
                return true;

            case R.id.action_favourited_routes:
                Intent intent1 = new Intent(choose_your_route.this,favourited.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        selection = MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE + " = ?" + " AND " +
                MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE + " = ?";
        selectionArgs = new String[]{fromSpinner.getSelectedItem().toString(),toSpinner.getSelectedItem().toString()};
        Integer zonesIndicator = Utillity.checkSpinner(fromSpinner,toSpinner);

        if(zonesIndicator == 0){
            selection = null; selectionArgs = null;
        } else if (zonesIndicator == 1) {
            selection = MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE + " = ?"; selectionArgs = new String[]{toSpinner.getSelectedItem().toString()};
        } else if (zonesIndicator == 2) {
            selection = MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE + " = ? "; selectionArgs = new String[]{fromSpinner.getSelectedItem().toString()};
        }

        return new CursorLoader(
                this,
                MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI,
                MicrobusesContract.microbusesEntry.LIST_ITEM_PROJECTION,
                selection,
                selectionArgs,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        microbussesCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        microbussesCursorAdapter.swapCursor(null);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getLoaderManager().restartLoader(LOADERID,null,this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectionArgs = null;selection = null;
    }
}
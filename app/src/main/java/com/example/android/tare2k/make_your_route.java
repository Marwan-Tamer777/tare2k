package com.example.android.tare2k;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tare2k.DataAndUtillity.MicrobusesContract;
import com.example.android.tare2k.DataAndUtillity.MicrobussesCursorAdapter;
import com.example.android.tare2k.DataAndUtillity.Utillity;

import java.util.Currency;
import java.util.Locale;

public class make_your_route extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener {

    String startZoneValue = "";
    String endZoneValue = "";
    Double cost = 0.0;
    Spinner startPointSpinner;
    Spinner endPointSpinner;
    TextView routeStartZone;
    TextView routeEndZone;
    TextView routeCost;
    String selection;
    String[] selectionArgs;
    ListView unselectList;
    ListView selectedList;
    MicrobussesCursorAdapter unselectedAdapter = new MicrobussesCursorAdapter(this,null,MicrobusesContract.unselectedListItem);
    MicrobussesCursorAdapter selectedAdapter = new MicrobussesCursorAdapter(this,null,MicrobusesContract.selectedListItem);
    String currencySymbol = "ج";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_your_route);
        Toast.makeText(this,R.string.info_on_open_message,Toast.LENGTH_LONG).show();

        routeStartZone = findViewById(R.id.route_start_zone);
        routeEndZone = findViewById(R.id.route_end_zone);
        routeCost = findViewById(R.id.route_cost);
        startPointSpinner = findViewById(R.id.start_point_spinner);
        endPointSpinner = findViewById(R.id.end_point_spinner);
        unselectList = findViewById(R.id.unselected_microbusses_list);
        selectedList = findViewById(R.id.selected_microbuses_list);
        LinearLayout unselectedEmptyCase = findViewById(R.id.make_route_unselected_empty_case);
        LinearLayout selectedEmptyCase = findViewById(R.id.make_route_selected_empty_case);
        TextView emptyCaseTextView = findViewById(R.id.make_empty_case_text);

        Utillity.setupSpinner(this,startPointSpinner);
        Utillity.setupSpinner(this,endPointSpinner);
        startPointSpinner.setOnItemSelectedListener(this);
        endPointSpinner.setOnItemSelectedListener(this);
        unselectList.setAdapter(unselectedAdapter);
        selectedList.setAdapter(selectedAdapter);
        unselectList.setEmptyView(unselectedEmptyCase);
        selectedList.setEmptyView(selectedEmptyCase);

        emptyCaseTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,"tare2ak@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT,"i would like to share a stop i know");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        selectedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(make_your_route.this, microbus_info.class);
                Uri microbus_uri = ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI, id);
                intent.setData(microbus_uri);
                startActivity(intent);
            }
        });

        unselectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(make_your_route.this, microbus_info.class);
                Uri microbus_uri = ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI, id);
                intent.setData(microbus_uri);
                startActivity(intent);
            }
        });

        unselectList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = getInfoCursor(id);
                cursor.moveToFirst();
                if(selectedList.getAdapter().getCount() == 0) {
                    startZoneValue = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE));
                }
                endZoneValue = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE));
                if(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE)).equals("0.0")){
                    cost = cost + Double.valueOf(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE)));
                  } else {
                    cost = cost + Double.valueOf(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE)));
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_SELECTED,1);
                getContentResolver().update(ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI,id),contentValues,
                        MicrobusesContract.microbusesEntry._ID + " = ?",new String[]{String.valueOf(id)});
                intiLoader();
                updateInfo();
                return false;
            }
        });

        selectedList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = getInfoCursor(id);
                cursor.moveToFirst();
                if(selectedList.getAdapter().getCount() == 1) {
                    startZoneValue = "";
                    endZoneValue = "";
                }
                if(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE)).equals("0.0")){
                    cost = cost - Double.valueOf(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE)));
                } else {
                    cost = cost - Double.valueOf(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE)));
                }

                ContentValues contentValues = new ContentValues();
                contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_SELECTED,0);
                getContentResolver().update(ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI,id),contentValues,
                        MicrobusesContract.microbusesEntry._ID + " = ?",new String[]{String.valueOf(id)});
                intiLoader();
                updateInfo();
                return false;
            }
        });
        getSupportLoaderManager().initLoader(0,null,this);
        getSupportLoaderManager().initLoader(1,null,this);
    }

    public void intiLoader(){getSupportLoaderManager().initLoader(0,null,this);
    getSupportLoaderManager().initLoader(1,null,this);}

    public void updateInfo(){
        routeStartZone.setText(startZoneValue);
        routeEndZone.setText(endZoneValue);
        routeCost.setText(cost + currencySymbol);
        if(routeCost.getText().toString().equals("0.0" + currencySymbol)) {
            routeCost.setText("");
        }
    }

    public Cursor getInfoCursor(Long id){
        Cursor cursor = getContentResolver().query(
                ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI,id),
                MicrobusesContract.microbusesEntry.INFO_PROJECTION,
                MicrobusesContract.microbusesEntry._ID + " = ?",
                new String[]{String.valueOf(id)},
                null);
        return cursor;
    }

    @Override
    public void onBackPressed() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_SELECTED,0);
        for(int i = 0;i < selectedList.getAdapter().getCount();i++) {
            getContentResolver().update(
                    ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI, selectedList.getAdapter().getItemId(i)),
                    contentValues,
                    null,
                    null);
        }
        super.onBackPressed();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        if(id == 0){
            selection = MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE + " = ? AND " +
                    MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE + " = ? AND " +
                    MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " = ?";
            selectionArgs = new String[]{startPointSpinner.getSelectedItem().toString(),endPointSpinner.getSelectedItem().toString(),"0"};
            Integer zonesIndicator = Utillity.checkSpinner(startPointSpinner,endPointSpinner);

            if(zonesIndicator == 0){
                selection = MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " = ?";
                selectionArgs = new String[]{"0"};
            } else if (zonesIndicator == 1) {
                selection = MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE + " = ? AND " + MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " = ?";
                selectionArgs = new String[]{endPointSpinner.getSelectedItem().toString(),"0"};
            } else if (zonesIndicator == 2) {
                selection = MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE + " = ? AND " + MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " = ?";
                selectionArgs = new String[]{startPointSpinner.getSelectedItem().toString(),"0"};
            }
            return new CursorLoader(
                    this,
                    MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI,
                    MicrobusesContract.microbusesEntry.LIST_ITEM_PROJECTION,
                    selection,
                    selectionArgs,
                    null
            );
        } else {
            return new CursorLoader(this,
                    MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI,
                    MicrobusesContract.microbusesEntry.LIST_ITEM_PROJECTION,
                    MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " = ?",
                    new String[]{"1"},
                    null);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        int i = loader.getId();
        if ( i == 0){
            unselectedAdapter.swapCursor(cursor);
        } else {
            selectedAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        unselectedAdapter.swapCursor(null);
        selectedAdapter.swapCursor(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getSupportLoaderManager().restartLoader(0,null,this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selection = MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " = ?";selectionArgs = new String[]{"0"};
    }
}

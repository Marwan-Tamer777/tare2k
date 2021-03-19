package com.example.android.tare2k;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tare2k.DataAndUtillity.MicrobusesContract;
import com.example.android.tare2k.DataAndUtillity.MicrobussesCursorAdapter;

import java.util.Currency;
import java.util.Locale;

public class microbus_info extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    Intent reciviedIntent;
    Uri intentData;
    ImageView microbusPic;
    ImageView favouritedView;
    TextView microbusLocation;
    TextView microbusStartPoint;
    TextView microbusEndPoint;
    TextView microbusThrough;
    TextView microbusGoogleMaps;
    TextView microbusCost;
    TextView showRouteToStop;
    Cursor microbusInfo;
    MicrobussesCursorAdapter microbussesCursorAdapter;
    String selection;
    String[] selectionArgs;
    String currencySymbol = "ج";
    LocationManager locationManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microbus_info);
        microbussesCursorAdapter = new MicrobussesCursorAdapter(this,null,MicrobusesContract.mainListItem);

        ListView nearStopsList = findViewById(R.id.near_stops_list);
        LinearLayout infoEmptyCase = findViewById(R.id.info_empty_case);
        nearStopsList.setEmptyView(infoEmptyCase);
        nearStopsList.setAdapter(microbussesCursorAdapter);
        microbusPic = findViewById(R.id.microbus_info_pic);
        favouritedView = findViewById(R.id.info_favourited);
        microbusLocation = findViewById(R.id.microbus_info_location);
        microbusStartPoint = findViewById(R.id.microbus_info_from);
        microbusEndPoint = findViewById(R.id.microbus_info_to);
        microbusThrough = findViewById(R.id.microbus_info_through);
        microbusGoogleMaps = findViewById(R.id.info_google_maps);
        microbusCost = findViewById(R.id.microbus_info_cost);
        showRouteToStop = findViewById(R.id.show_route_to_stop);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        reciviedIntent = getIntent();
        intentData = reciviedIntent.getData();
        microbusInfo = getContentResolver().query(intentData,null,null,null,null);
        microbusInfo.moveToFirst();


        selection = MicrobusesContract.microbusesEntry._ID + " != ? AND " + MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE + " = ? OR "+
        MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE + " = ?";
        selectionArgs = new String[]{   String.valueOf(ContentUris.parseId(intentData)),
                microbusInfo.getString(microbusInfo.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE)),
                microbusInfo.getString(microbusInfo.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE))};
        setupActivity(microbusInfo);
        favouritedView.setOnClickListener(new View.OnClickListener() {

             @Override
            public void onClick(View v) {

                 Cursor cursor0 = getContentResolver().query(intentData,new String[]{MicrobusesContract.microbusesEntry._ID,MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED},
                         MicrobusesContract.microbusesEntry._ID + " = ?" + new String[]{String.valueOf(ContentUris.parseId(intentData))},null,null);
                 cursor0.moveToFirst();
                if(cursor0.getInt(cursor0.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED)) == 0){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED,1);
                    getContentResolver().update(intentData,contentValues,MicrobusesContract.microbusesEntry._ID + " = ?",new String[]{String.valueOf(ContentUris.parseId(intentData))});
                    favouritedView.setImageResource(R.drawable.star_on);
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED,0);
                    getContentResolver().update(intentData,contentValues,MicrobusesContract.microbusesEntry._ID + " = ?",new String[]{String.valueOf(ContentUris.parseId(intentData))});
                    favouritedView.setImageResource(R.drawable.star_off);
                }
            }

        });

        microbusGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    Intent googleMapsIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(microbusInfo.getString(microbusInfo.getColumnIndex(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI))));
                    startActivity(googleMapsIntent);
                }
                else {
                    Toast.makeText(microbus_info.this,"Sorry it seems that you don't have an internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        });

        nearStopsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(microbus_info.this, microbus_info.class);
                Uri microbus_uri = ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI, id);
                intent.setData(microbus_uri);
                startActivity(intent);
            }
        });

        showRouteToStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                    Uri uri = Uri.parse("http://www.google.com/maps/dir/?api=1&travelmode=walking&destination=" + microbusInfo.getString(microbusInfo.getColumnIndex(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON)));
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(microbus_info.this,R.string.network_or_gps_error,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setupActivity(Cursor cursor){
        cursor.moveToFirst();
        microbusPic.setImageResource(cursor.getInt(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_PIC)));
        if (cursor.getInt(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED)) == 0){
            favouritedView.setImageResource(R.drawable.star_off);
        } else {
            favouritedView.setImageResource(R.drawable.star_on);
        }
        microbusLocation.setText(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_LOCATION)));
        microbusStartPoint.setText(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_START_POINT))+
                "(" + cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE)) +")");
        microbusEndPoint.setText(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_END_POINT))+
                "(" + cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE)) +")");
        microbusThrough.setText(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_THROUGH)));
        if(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE)).equals("0.0")){
            microbusCost.setText(MicrobusesContract.from +cursor.getString(cursor.getColumnIndex( MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE)) + currencySymbol +
                    "  " + MicrobusesContract.to  +cursor.getString(cursor.getColumnIndex( MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE)) + currencySymbol);
        } else {
            microbusCost.setText(cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE))+ currencySymbol);
        }
        getSupportLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                this,
                MicrobusesContract.microbusesEntry.BASE_CONTENT_URI,
                MicrobusesContract.microbusesEntry.LIST_ITEM_PROJECTION,
                selection,
                selectionArgs,
                null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        microbussesCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        microbussesCursorAdapter.swapCursor(null);
    }
}

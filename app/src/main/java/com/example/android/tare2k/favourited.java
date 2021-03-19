package com.example.android.tare2k;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.android.tare2k.DataAndUtillity.MicrobusesContract;
import com.example.android.tare2k.DataAndUtillity.MicrobusesProvider;
import com.example.android.tare2k.DataAndUtillity.MicrobussesCursorAdapter;


public class favourited extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    String selection = MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED + " = ?";
    String[] selectionArgs = {"1"};
    MicrobussesCursorAdapter microbussesCursorAdapter = new MicrobussesCursorAdapter(this,null,MicrobusesContract.mainListItem);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourited);

        ListView favouritedList = findViewById(R.id.favourited_list);
        LinearLayout favouritedEmptyCase = findViewById(R.id.favourited_empty_case);
        favouritedList.setEmptyView(favouritedEmptyCase);
        favouritedList.setAdapter(microbussesCursorAdapter);
        getLoaderManager().initLoader(0,null,this);

        favouritedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(favourited.this, microbus_info.class);
                Uri microbus_uri = ContentUris.withAppendedId(MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_URI, id);
                intent.setData(microbus_uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
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
}

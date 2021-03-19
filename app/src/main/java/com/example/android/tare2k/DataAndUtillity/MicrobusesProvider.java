package com.example.android.tare2k.DataAndUtillity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.CursorAdapter;
import android.widget.Toast;


public class MicrobusesProvider extends ContentProvider {

    private MicrobusesSQLiteHelper microbusesHelper;
    private static final Integer MICROBUSES_GROUP = 100;
    private static final Integer SINGLE_MICROBUS = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.
        sUriMatcher.addURI(MicrobusesContract.microbusesEntry.CONTENT_AUTHORITY,MicrobusesContract.microbusesEntry.PATH_MICROBUSES,MICROBUSES_GROUP);
        sUriMatcher.addURI(MicrobusesContract.microbusesEntry.CONTENT_AUTHORITY,MicrobusesContract.microbusesEntry.PATH_MICROBUSES+ "/#",SINGLE_MICROBUS);
    }

    @Override
    public boolean onCreate() {
        microbusesHelper = new MicrobusesSQLiteHelper(this.getContext());
        SQLiteDatabase db = microbusesHelper.getReadableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri,String[] projection, String selection,  String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = microbusesHelper.getReadableDatabase();
        Cursor cursor;
        Integer match = sUriMatcher.match(uri);

        if(match == SINGLE_MICROBUS) {
            selection = MicrobusesContract.microbusesEntry._ID + "=?";
            selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        }

        cursor = db.query(
                MicrobusesContract.microbusesEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public Uri insert( Uri uri,ContentValues values) {
        SQLiteDatabase db = microbusesHelper.getWritableDatabase();
        Long rowID;

        try {
            rowID = db.insert(MicrobusesContract.microbusesEntry.TABLE_NAME, null, values);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("couldn't insert these vales " + values + "to this table " +MicrobusesContract.microbusesEntry.TABLE_NAME);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,rowID);
    }

    @Override
    public int delete( Uri uri,String selection,String[] selectionArgs) {

        SQLiteDatabase db = microbusesHelper.getWritableDatabase();
        Integer rowsDeleted;

        selection = MicrobusesContract.microbusesEntry._ID +"=?";
        selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
        try {
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + MicrobusesContract.microbusesEntry.TABLE_NAME + "'");
            rowsDeleted = db.delete(MicrobusesContract.microbusesEntry.TABLE_NAME, selection, selectionArgs);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("couldn't delete this uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {

        SQLiteDatabase db  = microbusesHelper.getWritableDatabase();
        Integer rowsUpdated;
        selection = MicrobusesContract.microbusesEntry._ID + "=?";
        selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        try {
           rowsUpdated = db.update(MicrobusesContract.microbusesEntry.TABLE_NAME,values,selection,selectionArgs);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("couldn't update this uri:" + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }

    @Override
    public String getType( Uri uri) {
        return MicrobusesContract.microbusesEntry.MICROBUSES_CONTENT_ITEM_TYPE;
    }
}

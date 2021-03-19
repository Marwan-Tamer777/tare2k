package com.example.android.tare2k.DataAndUtillity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MicrobusesSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="microbussesData.db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_CMD = "CREATE TABLE " + MicrobusesContract.microbusesEntry.TABLE_NAME + "(" +
            MicrobusesContract.microbusesEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            MicrobusesContract.microbusesEntry.COLUMN_LOCATION + " TEXT NOT NULL," +
            MicrobusesContract.microbusesEntry.COLUMN_START_POINT + " TEXT," +
            MicrobusesContract.microbusesEntry.COLUMN_END_POINT + " TEXT," +
            MicrobusesContract.microbusesEntry.COLUMN_THROUGH + " TEXT DEFAULT \"NOT KNOWN\"," +
            MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE + " TEXT NOT NULL," +
            MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE + " TEXT NOT NULL," +
            MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE + " TEXT  DEFAULT \"0.0\"," +
            MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE + " TEXT DEFAULT \"0.0\"," +
            MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE + " TEXT DEFAULT \"0.0\"," +
            MicrobusesContract.microbusesEntry.COLUMN_PIC + " INTEGER DEFAULT 0,"+
            MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED + " INTEGER DEFAULT 0,"+
            MicrobusesContract.microbusesEntry.COLUMN_SELECTED + " INTEGER DEFAULT 0,"+
            MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI + " TEXT,"+
            MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON + " TEXT,"+
            MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON + " TEXT);";
    private static final String DROP_TABLE_CMD = "DROP TABLE " + MicrobusesContract.microbusesEntry.TABLE_NAME + ";";

    public MicrobusesSQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CMD);
        for(int i= 1;i<=Utillity.DBItemsAmount();i++){
            db.insert(MicrobusesContract.microbusesEntry.TABLE_NAME,null,Utillity.getData(i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CMD);
        onCreate(db);
    }
}
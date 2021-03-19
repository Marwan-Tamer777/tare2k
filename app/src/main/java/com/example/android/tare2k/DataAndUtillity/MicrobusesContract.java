package com.example.android.tare2k.DataAndUtillity;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MicrobusesContract {

    private MicrobusesContract(){}

    public static final String from = "من:";
    public static final String to = "الي:";
    public static final String only = "فقط:";
    public static final String allZones = "كل المناطق";
    public static final Integer mainListItem = 0;
    public static final Integer unselectedListItem = 1;
    public static final Integer selectedListItem = 2;

    public abstract static class microbusesEntry implements BaseColumns {

        public static final String TABLE_NAME = "microbuses";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_START_POINT = "start_point";
        public static final String COLUMN_END_POINT = "end_point";
        public static final String COLUMN_THROUGH = "through";
        public static final String COLUMN_FROM_ZONE = "from_zone";
        public static final String COLUMN_TO_ZONE = "to_zone";
        public static final String COLUMN_STATIC_PRICE = "static_price";
        public static final String COLUMN_MIN_PRICE = "min_price";
        public static final String COLUMN_MAX_PRICE = "max_price";
        public static final String COLUMN_PIC = "pic";
        public static final String COLUMN_FAVOURITED = "favourited";
        public static final String GOOGLE_MAPS_URI = "google_maps_uri";
        public static final String COLUMN_SELECTED = "selected";
        public static final String COLUM_START_POINT_LAT_LON = "start_point_lat_lon";
        public static final String COLUM_END_POINT_LAT_LON = "end_point_lat_lon";

        public static final String PATH_MICROBUSES = "microbuses";
        public static final String CONTENT_AUTHORITY = "com.example.android.tare2k";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final Uri MICROBUSES_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MICROBUSES);

        /**
         * The MIME type of the {@link #MICROBUSES_CONTENT_URI} for a single pet.
         */
        public static final String MICROBUSES_CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MICROBUSES;

        public static final String[] LIST_ITEM_PROJECTION ={
                microbusesEntry._ID,
                microbusesEntry.COLUMN_LOCATION,
                microbusesEntry.COLUMN_PIC,
                microbusesEntry.COLUMN_FAVOURITED,
                microbusesEntry.COLUMN_START_POINT, microbusesEntry.COLUMN_FROM_ZONE,
                microbusesEntry.COLUMN_END_POINT, microbusesEntry.COLUMN_TO_ZONE,
                microbusesEntry.COLUMN_STATIC_PRICE,
                microbusesEntry.COLUMN_MIN_PRICE, microbusesEntry.COLUMN_MAX_PRICE};
        public static final String[] INFO_PROJECTION = {
          microbusesEntry._ID,
          microbusesEntry.COLUMN_FROM_ZONE,
          microbusesEntry.COLUMN_TO_ZONE,
          microbusesEntry.COLUMN_STATIC_PRICE,
          microbusesEntry.COLUMN_MIN_PRICE,
          microbusesEntry.COLUMN_MAX_PRICE
        };
    }

}

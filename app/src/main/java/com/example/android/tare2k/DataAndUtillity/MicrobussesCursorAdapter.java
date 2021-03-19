package com.example.android.tare2k.DataAndUtillity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tare2k.R;

import java.util.Currency;
import java.util.Locale;

public class MicrobussesCursorAdapter extends CursorAdapter {

    Integer layoutIndecaitor;
    public MicrobussesCursorAdapter(Context context, Cursor c,int i) {
        super(context, c, 0);layoutIndecaitor = i;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        if(layoutIndecaitor == MicrobusesContract.mainListItem) {
            return LayoutInflater.from(context).inflate(R.layout.main_list_view_item, parent, false);
        } else if(layoutIndecaitor == MicrobusesContract.unselectedListItem){
            return LayoutInflater.from(context).inflate(R.layout.unselected_list_view_item, parent, false);
        } else if(layoutIndecaitor == MicrobusesContract.selectedListItem){
            return LayoutInflater.from(context).inflate(R.layout.selected_list_view_item, parent, false);
        }
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String currencySymbol = "ج";

        ImageView favouritedStar = view.findViewById(R.id.list_item_star);
        ImageView microbusPic = view.findViewById(R.id.list_item_microbus_pic);
        TextView microbusLocation = view.findViewById(R.id.list_item_microbus_location);
        TextView microbusFrom = view.findViewById(R.id.list_item_microbus_from);
        TextView microbusTo = view.findViewById(R.id.list_item_microbus_to);
        TextView microbusCost = view.findViewById(R.id.list_item_microbus_cost);

        Integer starStateIndicator  = cursor.getInt(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FAVOURITED));
        Integer microbusPicValue = cursor.getInt(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_PIC));
        String microbusLocationValue = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_LOCATION));
        String micronusFromValue = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_START_POINT));
        String microbusToValue = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_END_POINT));
        String microbusStartZone = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE));
        String microbusEndZone = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE));
        String microbusStaticCost = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE));
        String microbusMinCost = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE));
        String microbusMaxCost = cursor.getString(cursor.getColumnIndex(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE));

        if(starStateIndicator == 0){
            favouritedStar.setImageResource(R.drawable.star_off);
        } else {
            favouritedStar.setImageResource(R.drawable.star_on);
        }
        if (microbusPicValue == null){
            microbusPic.setImageResource(R.drawable.item_not_found);
        } else {
            microbusPic.setImageResource(microbusPicValue);
        }

        microbusLocation.setText(microbusLocationValue);
        microbusFrom.setText(micronusFromValue + "(" + microbusStartZone + ")");
        microbusTo.setText(microbusToValue + "(" + microbusEndZone + ")");

        if(microbusStaticCost.equals("0.0")) {
            if(layoutIndecaitor == MicrobusesContract.selectedListItem){
                microbusCost.setText(MicrobusesContract.from + microbusMinCost + currencySymbol +"\n"+ MicrobusesContract.to+ microbusMaxCost + currencySymbol);
            } else {
                microbusCost.setText(MicrobusesContract.from+ microbusMinCost + currencySymbol + MicrobusesContract.to+ microbusMaxCost + currencySymbol);
            }
        } else {
            microbusCost.setText(MicrobusesContract.only  + microbusStaticCost + currencySymbol);
        }

    }
}

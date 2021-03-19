package com.example.android.tare2k.DataAndUtillity;

import android.content.ContentValues;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android.tare2k.R;

public final class Utillity {

    public  static  void setupSpinner(Context context,Spinner spinner) {

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(context,
                R.array.zones_names, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        spinner.setAdapter(genderSpinnerAdapter);
    }

    public static Integer DBItemsAmount(){
        return 7;
    }

    public static ContentValues getData(Integer i){
        ContentValues contentValues = new ContentValues();
        if(i == 1){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام مترو العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"مترو العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"كوبري الدائري");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع مؤسسة الزكاة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المرج");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE,"3.0");;
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        } else if(i == 2){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"موقف الجراج امام نادي النخيل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة الجراج");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع 6 اكتوبر");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"جسر السويس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE,"3.50");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.128855,31.361748&daddr=30.1326843,31.3235547");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.128855,31.361748");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1326843,31.3235547");
        }  else if(i == 3){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"بجوار النادي الاجتماعي");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"مساكن عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"ميدان العباسية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"جسر السويس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"العباسية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE,"5.0");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1366399,31.3502430&daddr=30.073869,31.285205");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1366399,31.3502430");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.073869,31.285205");
        }  else if(i == 4){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"ميدان الف مسكن");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"الف مسكن");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"ميدان المحكمة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"جسر السويس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"جسر السويس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"مصر الجديدة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,"3.50");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,"5.0");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1192970,31.340390&daddr=30.103623,31.328510");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1192970,31.340390");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.103623,31.328510");
        }  else if(i == 5){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام صيدلية حسين");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"شارع العشرين");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"الف مسكن");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع احمد عصمت");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"جسر السويس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE,"3.50");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.130437,31.337881&daddr=30.123182,31.340123");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.130437,31.337881");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.123182,31.340123");
        }  else if(i == 6){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"الجهة المقابلة لنادي النخيل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة الجراج");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"مول صن سيتي");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شيراتون شارع 6 اكتوبر");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"جسر السويس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"مصر الجديدة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,"3.50");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,"4.0");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1280171,31.3617926&daddr=30.1037713,31.3842816");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1280171,31.3617926");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1037713,31.3842816");
        }  else if(i == 7){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"مزلقان عين شمس امام البنك الاهلي");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"مزلقان عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"مزلقان العشرين");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع 6 اكتوبر عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"عين شمس");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_STATIC_PRICE,"2.0");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1326840,31.323550&daddr=30.1368681,31.3385285");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1326840,31.323550");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1368681,31.3385285");
        }  else if(i == 8){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.logo);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 9){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 10){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 11){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 12){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 13){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 14){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }  else if(i == 15){
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_LOCATION ,"امام محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_START_POINT ,"محطة بنزين العزبة");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_END_POINT,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_THROUGH,"شارع الزهراء");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_FROM_ZONE,"عزبة النخل");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_TO_ZONE,"المطرية");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MIN_PRICE,2);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_MAX_PRICE,4);
            contentValues.put(MicrobusesContract.microbusesEntry.COLUMN_PIC,R.drawable.item_not_found);
            contentValues.put(MicrobusesContract.microbusesEntry.GOOGLE_MAPS_URI,"http://maps.google.com/maps?saddr=30.1393391,31.3248120&daddr=30.1662914,31.3832466");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_START_POINT_LAT_LON,"30.1393391,31.3248120");
            contentValues.put(MicrobusesContract.microbusesEntry.COLUM_END_POINT_LAT_LON,"30.1662914,31.3832466");
        }
        return contentValues;
    }

    public static Integer checkSpinner(Spinner fromSpinner,Spinner toSpinner){
        Integer i;
        String from = String.valueOf(fromSpinner.getSelectedItem());
        String to = toSpinner.getSelectedItem().toString();
        if(from.equals(MicrobusesContract.allZones) && to.equals(MicrobusesContract.allZones))
        {
            i=0;
        } else if(from.equals(MicrobusesContract.allZones) && !to.equals(MicrobusesContract.allZones)){
            i =1;
        } else if (to.equals(MicrobusesContract.allZones) && !from.equals(MicrobusesContract.allZones)) {
            i = 2;
        } else {i = 3;}
        return i;
    }
}

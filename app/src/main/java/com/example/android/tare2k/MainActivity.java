package com.example.android.tare2k;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.tare2k.DataAndUtillity.MicrobusesContract;
import com.example.android.tare2k.DataAndUtillity.MicrobusesSQLiteHelper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View loadingScreenView = findViewById(R.id.losding_screen_view);
        loadingScreenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,choose_your_route.class);
                startActivity(intent);
            }
        });

    }
}

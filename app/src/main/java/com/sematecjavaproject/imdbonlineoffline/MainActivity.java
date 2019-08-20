package com.sematecjavaproject.imdbonlineoffline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        TextView textViewOpenDrawer = findViewById(R.id.txtOpenDrawer);
        final TextView textViewOnlineSearch = findViewById(R.id.txtOnlineSearch);
        final TextView textViewOfflineSearch = findViewById(R.id.txtOfflineSearch);

        textViewOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);

                textViewOnlineSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent onlineIntent = new Intent(MainActivity.this, OnlineSearch.class);
                        startActivity(onlineIntent);
                    }
                });

                textViewOfflineSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent offlineIntent = new Intent(MainActivity.this, OfflineSearch.class);
                        startActivity(offlineIntent);
                    }
                });
            }
        });
    }
}

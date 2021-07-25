package com.example.seedtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    NavigationView navigation;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Toolbar toolbar;
    ImageView cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navmenu);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        cam = findViewById(R.id.cam);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId())
                {

                    case R.id.recent:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(MainActivity.this,recentfiles.class);
                        startActivity(intent);
                        break;
                    case R.id.about:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent i = new Intent(MainActivity.this,about.class);
                        startActivity(i);
                        break;
                    case R.id.help:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent in = new Intent(MainActivity.this,help.class);
                        startActivity(in);
                        break;
                    case R.id.termsandconditions:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent inte = new Intent(MainActivity.this,termsandconditions.class);
                        startActivity(inte);
                        break;
                }

                return true;
            }
        });
    }
}
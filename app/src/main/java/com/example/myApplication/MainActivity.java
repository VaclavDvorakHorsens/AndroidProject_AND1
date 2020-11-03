package com.example.myApplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.navigationdrawer.R;
import com.example.myApplication.Model.Diary.DiaryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    private BottomNavigationView bottomNavigationView;
    private android.view.MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupNavigation();



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
      /*  navigationView.setNavigationItemSelectedListener(this);*/

    }




    private void setupNavigation()
    {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_email,
                R.id.nav_call)
                .setOpenableLayout(drawerLayout)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
        setBottomNavigationVisibility();
    }

    private void setBottomNavigationVisibility()
    {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private void initViews()
    {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }




    public boolean onOptionsItemSelected(MenuItem item) {

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);*/

        switch(item.getItemId())
        {
            /*case R.id.nav_host_fragment:
                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);*/
            case R.id.action_settings:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
                startActivity(intent);
                return true;

        }
        super.onOptionsItemSelected(item);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }










    public static class DiaryItemList {
        private static ArrayList<DiaryItem> diaryItems=new ArrayList<DiaryItem>();
        public static ArrayList<DiaryItem> getDiaryItems()
        {
            return diaryItems;
        }
}
}
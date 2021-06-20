package com.example.mot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.map:
                        if(isNetworkAvailable())
                        {
                            startActivity(new Intent(getApplicationContext(),map.class));
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        }
                        else {
                            Toast.makeText(MainActivity.this,"No Internet Found.. Please Connect to internet and try again..",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    case R.id.home:
                        return true;
                    case R.id.leads:
                        if(isNetworkAvailable())
                        {
                            startActivity(new Intent(getApplicationContext(),leads.class));
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        }
                        else {
                            Toast.makeText(MainActivity.this,"No Internet Found.. Please Connect to internet and try again..",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                }
                return false;
            }
        });


        String htmlAsString = getString(R.string.html);
        Spanned builder = Html.fromHtml(htmlAsString);
        TextView tv = new TextView(MainActivity.this);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat.ttf");
        tv.setTypeface(typeface);
        tv.setText(builder);

        Button about = new Button(MainActivity.this);
        about.setText("Contact Us");
        about.setTextSize(16);
        about.setPadding(5,5,5,25);
        about.setBackgroundResource(R.drawable.shadow);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,about.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        LinearLayout ll = findViewById(R.id.textLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 20, 20, 20);
        about.setLayoutParams(params);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(tv);
        ll.addView(about);

    }

    private boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (Exception e){
            return true;
        }
    }



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }





}

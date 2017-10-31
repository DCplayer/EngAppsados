package com.engappsados.engappsadosapp;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Permissions extends AppCompatActivity {

    private Button btnExit, btnSett;
    public int mode;
    public boolean granted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions);
        //Check if permission enabled/*
        AppOpsManager appOps = (AppOpsManager) this.getSystemService(this.APP_OPS_SERVICE);
        mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), this.getPackageName());
        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (this.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        }
        else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        btnExit = (Button) findViewById(R.id.btn_exit);
        btnSett = (Button) findViewById(R.id.btn_setting);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        btnSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!granted){
                    //startActivity(new Intent(MainActivity.this, Permissions.class));
                    Toast.makeText(Permissions.this, "Necesito permisos!" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                }else{
                    Toast.makeText(Permissions.this, "Si hay!" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Permissions.this, MainActivity.class));
                    finish();
                }

            }
        });
    }
}
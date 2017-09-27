package com.engappsados.engappsadosapp;


import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by sebas on 9/26/2017.
 */

public class Ustats {

    public Ustats() {
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getTimeUstats(Context mContext, ArrayList<String> array){
        // UsageStats usageStats;
        Log.i("BAC", "ENTRE ACA");
        String PackageName = "Nothing" ;

        long TimeInforground = 500 ;

        int minutes=500,seconds=500,hours=500 ;
        Log.i("BAC", "ENTRE ACA2");
        UsageStatsManager mUsageStatsManager = (UsageStatsManager)mContext.getSystemService(Context.USAGE_STATS_SERVICE);
        Log.i("BAC", "ENTRE ACA3");
        long time = System.currentTimeMillis();

        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*10, time);
        Log.i("BAC", "ENTRE ACA4");
        if(stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : stats) {
                if(array.contains(usageStats.getPackageName())){
                    TimeInforground = usageStats.getTotalTimeInForeground();

                    PackageName = usageStats.getPackageName();

                    minutes = (int) ((TimeInforground / (1000 * 60)) % 60);

                    seconds = (int) (TimeInforground / 1000) % 60;

                    hours = (int) ((TimeInforground / (1000 * 60 * 60)) % 24);

                    Log.i("BAC", "PackageName is" + PackageName + "Time is: " + hours + "h" + ":" + minutes + "m" + seconds + "s");
                }
            }
        }
    }
}


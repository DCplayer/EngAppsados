package com.engappsados.engappsadosapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuTabbed extends AppCompatActivity {
    Noticias_Tab tab1;
    Apps_Tab tab2;
    Encuestas_tab tab3;
    Tienda_tab tab4;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EngAppsados");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_library_books_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_phone_android_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_assignment_white_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_card_giftcard_white_24dp);    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        ArrayList<String> a = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        ArrayList<Integer> t = new ArrayList<>();
        for (AppModelo as: tab2.aplicaciones) {
            a.add(as.getPackageName());
            p.add(as.getPuntos());
            t.add(as.getTiempoenMins());
        }


        switch (item.getItemId()){
            case R.id.action_User:
                Intent intent = new Intent(this, userProfile.class);
                Bundle b = new Bundle();
                b.putStringArrayList("aplicaciones",a); //Your id
                b.putIntegerArrayList("tiempo",t);
                b.putIntegerArrayList("puntos",p);
                intent.putExtras(b); //Put your id to your next Intent
                this.startActivity(intent);
                //startActivity(new Intent(this, userProfile.class));
                //haciendo pruebas
                //startActivity(new Intent(MenuTabbed.this, GoogleForm_Activity.class));
                return true;

            case R.id.action_Bug:
                startActivity(new Intent(this, ReportarErrores.class));
                //startActivity(new Intent(this, userProfile.class));
                //haciendo pruebas
                //startActivity(new Intent(MenuTabbed.this, GoogleForm_Activity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.noticias_tab, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // returning the current tabs
            switch (position){
                case 0:
                    tab1 = new Noticias_Tab();
                    return tab1;
                case 1:
                    tab2 = new Apps_Tab();
                    return tab2;
                case 2:
                    tab3 = new Encuestas_tab();
                    return tab3;
                case 3:
                    tab4 = new Tienda_tab();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NOTICIAS";
                case 1:
                    return "APPS";
                case 2:
                    return "ENCUESTAS";
                case 3:
                    return "TIENDA";
            }
            return null;
        }
    }
}

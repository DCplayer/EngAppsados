package com.engappsados.engappsadosapp;

/**
 * Created by sebas on 8/15/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Noticias_Tab extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.noticias_tab, container, false);

        return rootView;
    }


}

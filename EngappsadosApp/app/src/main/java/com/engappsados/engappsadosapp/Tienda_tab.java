package com.engappsados.engappsadosapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sebas on 8/15/2017.
 */

public class Tienda_tab extends Fragment{

    private ListView lvRewards;
    private AdapterRewardItem adapter;
    private List<Recompensa> recompensaList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tienda_tab, container, false);

        lvRewards = (ListView)rootView.findViewById(R.id.listview_tienda);
        recompensaList = new ArrayList<>();
        return rootView;
    }


}
package fxp.com.resumeclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fxp.com.resumeclient.R;

public class MainFragment extends Fragment {

    private static final String ARG_PARAM = "mainFragment";

    public MainFragment newInstance(String param) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
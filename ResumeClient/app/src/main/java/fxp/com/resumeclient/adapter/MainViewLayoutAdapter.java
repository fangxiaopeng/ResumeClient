package fxp.com.resumeclient.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxp on 2018/2/9.
 */

public class MainViewLayoutAdapter extends FragmentStatePagerAdapter {

    private Context context;

    private final List<Fragment> fragments = new ArrayList<>();

    private final List<String> fragmentTitles = new ArrayList<>();

    public MainViewLayoutAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
package test.ds.com.dailystudy.factory;

import android.support.v4.app.Fragment;

import java.util.HashMap;

import test.ds.com.dailystudy.fragment.CicleFragment;
import test.ds.com.dailystudy.fragment.HomeFragment;
import test.ds.com.dailystudy.fragment.MineFragment;
import test.ds.com.dailystudy.fragment.TypeFragment;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:50.
 */

public class FragmentFactory {

    private static HashMap<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new TypeFragment();
                break;
            case 2:
                fragment = new CicleFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }
        fragmentMap.put(position, fragment);
        return fragment;
    }
}

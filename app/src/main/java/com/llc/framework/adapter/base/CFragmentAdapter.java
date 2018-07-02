package com.llc.framework.adapter.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author liliuchen
 * @package com.crm.framework.adapter.base
 * @fileName CFragmentAdapter
 * @date 2018/2/12
 * @emial 871898381@qq.com
 * @describe 切换 ViewPage的适配器
 * @company
 */
public class CFragmentAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;

    public CFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    String[] mPageTitles;

    public CFragmentAdapter(FragmentManager fm, List<Fragment> fragments, String[] pagetitles) {
        super(fm);
        this.mFragments = fragments;
        this.mPageTitles = pagetitles;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (mPageTitles != null && mPageTitles.length > position) return mPageTitles[position];
        return super.getPageTitle(position);
    }
}

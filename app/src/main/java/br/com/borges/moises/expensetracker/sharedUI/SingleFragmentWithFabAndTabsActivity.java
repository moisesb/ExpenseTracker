package br.com.borges.moises.expensetracker.sharedUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by moise on 25/01/2016.
 */
public abstract class SingleFragmentWithFabAndTabsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_with_fab_and_tabs);
        ButterKnife.bind(this);

        initActionBar();

        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
    }


    protected void setupViewPager(ViewPager viewPager) {
        viewPager.setAdapter(getPageAdapter());
    }

    /**
     *
     * @return return ViewPagerAdapter with fragments and titles
     */
    protected abstract PagerAdapter getPageAdapter();

    private void initActionBar() {
        setSupportActionBar(mToolbar);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(@NonNull Fragment fragment,
                                @NonNull String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

package contacts.test.moviesdatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Tv_Fragment extends Fragment {
   ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_, container, false);
                viewPager = (ViewPager) v.findViewById(R.id.viewPager_TvShows);
               tabLayout = (TabLayout) v.findViewById(R.id.tablayout_tvfragment);

                tabLayout.addTab(tabLayout.newTab().setText("Airing Today"));
                tabLayout.addTab(tabLayout.newTab().setText("On The Air"));
                tabLayout.addTab(tabLayout.newTab().setText("Popular"));
                tabLayout.addTab(tabLayout.newTab().setText("Top Rated"));
                 tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                tabLayout.setupWithViewPager(viewPager);
           TvShowPagerAdapter pagerAdapter = new TvShowPagerAdapter(getFragmentManager(),tabLayout.getTabCount());
            viewPager.setAdapter(pagerAdapter);

              tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                  @Override
                  public void onTabSelected(Tab tab) {
                      viewPager.setCurrentItem(tab.getPosition());
                  }

                  @Override
                  public void onTabUnselected(Tab tab) {

                  }

                  @Override
                  public void onTabReselected(Tab tab) {

                  }
              });
                return v;
    }
}

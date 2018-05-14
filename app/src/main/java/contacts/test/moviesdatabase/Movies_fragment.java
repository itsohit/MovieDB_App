package contacts.test.moviesdatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Movies_fragment extends Fragment {

    TabLayout tabLayout;
      ViewPager viewpager;
    MyMoviePagerAdpater pagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_movies_fragment, container, false);

                viewpager = (ViewPager) v.findViewById(R.id.view_pager_movies);
                tabLayout = (TabLayout) v.findViewById(R.id.tablayout_Movies);


        tabLayout.addTab(tabLayout.newTab().setText("Up Coming"));
        tabLayout.addTab(tabLayout.newTab().setText("Latest"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Rated"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

         tabLayout.setupWithViewPager(viewpager);

        pagerAdapter = new MyMoviePagerAdpater(getFragmentManager(),tabLayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }
}

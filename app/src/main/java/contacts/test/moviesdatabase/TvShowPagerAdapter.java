package contacts.test.moviesdatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by IT sohit on 1/11/2018.
 */

class TvShowPagerAdapter extends FragmentStatePagerAdapter {
        int tabCount;
     String titlename[] = {"Airing Today","On The Air","Popular","Top Rated"};
    public TvShowPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlename[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
          switch(position){
              case 0:
                   fragment = new TvAiringTodayFragment();
                  return fragment;
              case 1:
                  fragment = new TvOnTheAirFragment();
                  return fragment;
              case 2:
                   fragment = new TvPopular_Fragment();
                    return fragment;
              case 3:
                   fragment = new TopRatedTvShowFragment();
                  return fragment;
              default:
                  return null;
          }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

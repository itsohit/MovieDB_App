package contacts.test.moviesdatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by IT sohit on 1/4/2018.
 */

class MyMoviePagerAdpater extends FragmentStatePagerAdapter{
    int tabno;
    String []names= {"Up Coming","Latest","Popular","Rated"};
    public MyMoviePagerAdpater(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        tabno=tabCount;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }
    @Override
    public Fragment getItem(int position) {
       Fragment fragment = null;
         switch(position){
             case 0:
                  fragment = new Up_Coming_Fragment();
                        return fragment;
             case 1:
                 fragment = new NowPlaying_Fragment();
                       return fragment;
             case 2:
                 fragment = new Popular_movies();
                   return fragment;
             case 3:
                 fragment = new Rated_Movies();
                    return fragment;
             default:
                  return null;
         }
    }

    @Override
    public int getCount() {
        return tabno;
    }
}

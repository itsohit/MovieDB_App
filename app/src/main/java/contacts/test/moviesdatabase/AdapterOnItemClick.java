package contacts.test.moviesdatabase;

import android.view.View;

/**
 * Created by IT sohit on 1/31/2018.
 */

public interface AdapterOnItemClick {
    public void onClickItem(View view,int position,String poster,String backposter,String name,String overview,String releasedate);
}

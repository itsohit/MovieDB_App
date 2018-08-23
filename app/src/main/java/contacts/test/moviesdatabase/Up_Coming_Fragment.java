package contacts.test.moviesdatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Up_Coming_Fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    UpcomingAdapter upcomingAdapter;
     Call<UpComingApiData> apiDataCall=null;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_up__coming_, container, false);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_upcoming);
           recyclerView = (RecyclerView) v.findViewById(R.id.recycleview_upComing_Movie);

        layoutManager = new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);
        apiDataCall = UpComing_DataService.getService().getUpComingData();
        apiDataCall.enqueue(new Callback<UpComingApiData>() {
            @Override
            public void onResponse(Call<UpComingApiData> call, Response<UpComingApiData> response) {
                UpComingApiData upComingApiData = response.body();
                     //   List<Result> upComingApiDataList = upComingApiData.getResults();
               ResultAdapter  upcomingAdapter = new ResultAdapter(getContext(),upComingApiData.getResults(),adapterOnItemClick);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(upcomingAdapter);
           //     upcomingAdapter.setData(mData); // need to implement setter method for data in adapter
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),"Success ",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<UpComingApiData> call, Throwable t) {
                Toast.makeText(getContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    AdapterOnItemClick adapterOnItemClick = new AdapterOnItemClick() {
        @Override
        public void onClickItem(View view, int position, String poster, String backposter, String name, String overview, String releasedate,Double voteAverage,int vcount) {
            Detail_Page detail_page = new Detail_Page();
            Bundle bundle = new Bundle();
            bundle.putString("poster",poster);
            bundle.putString("BackPoster",backposter);
            bundle.putString("Name",name);
            bundle.putString("Overview",overview);
            bundle.putString("ReleaseDate",releasedate);
            bundle.putDouble("voteAverage",voteAverage);
            bundle.putInt("voteCount",vcount);
            detail_page.setArguments(bundle);
            FragmentTransaction ft =  getFragmentManager().beginTransaction();
            ft.add(R.id.frame_layout_nav,detail_page).addToBackStack(null).commit();
        }
    };
}


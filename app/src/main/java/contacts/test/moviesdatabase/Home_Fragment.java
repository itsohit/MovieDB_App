package contacts.test.moviesdatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Home_Fragment extends Fragment {
        RecyclerView movieRecycleview,tvshowRecyclerView;
        RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                
        View v= inflater.inflate(R.layout.fragment_home_, container, false);
            movieRecycleview = (RecyclerView) v.findViewById(R.id.descover_movie_recyclerview);
            tvshowRecyclerView = (RecyclerView) v.findViewById(R.id.descover_tvShows_recyclerview);

              movieAdapter();
                tvAdapter();
            return v;
    }

    void movieAdapter(){
         layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClass.Descover_movie_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            ApiClass.ApiInterface moviesCallback = retrofit.create(ApiClass.ApiInterface.class);
                Call<DescoverMovies> descoverMoviesCallback = moviesCallback.getDescoverMovies();

             descoverMoviesCallback.enqueue(new Callback<DescoverMovies>() {
                 @Override
                 public void onResponse(Call<DescoverMovies> call, Response<DescoverMovies> response) {
                           DescoverMovies descoverMovies = response.body();
                    DescoverMovieAdapter  descoverMovieAdapter = new DescoverMovieAdapter(getContext(),descoverMovies.getResults(),adapterOnItemClick);
                     movieRecycleview.setLayoutManager(layoutManager);
                        movieRecycleview.setAdapter(descoverMovieAdapter);
                 }
                 @Override
                 public void onFailure(Call<DescoverMovies> call, Throwable t) {

                 }
             });
    }

     void tvAdapter(){
         final Call<DescoverTvShows> descoverTvShowsCall = ApiClass.getTvApiInterface().getDescoverTv();
          descoverTvShowsCall.enqueue(new Callback<DescoverTvShows>() {
              @Override
              public void onResponse(Call<DescoverTvShows> call, Response<DescoverTvShows> response) {
                  DescoverTvShows descoverTvShows = response.body();
                    Log.d("Response :",response.message());
                  TvshowAdapter adapter = new TvshowAdapter(getContext(),descoverTvShows,adapterOnItemClick);
                  RecyclerView.LayoutManager layouttvmanager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                   tvshowRecyclerView.setLayoutManager(layouttvmanager);
                   tvshowRecyclerView.setAdapter(adapter);
              }

              @Override
              public void onFailure(Call<DescoverTvShows> call, Throwable t) {
                  Toast.makeText(getContext(),"Error in Tv show api",Toast.LENGTH_SHORT).show();
              }
          });
     }
     AdapterOnItemClick adapterOnItemClick = new AdapterOnItemClick() {
         @Override
         public void onClickItem(View view, int position,String poster,String bkposter,String name,String overview,String releasedate,Double voteAverage,int vcount) {
                Detail_Page detail_page = new Detail_Page();
                    Bundle bundle = new Bundle();
                     bundle.putString("poster",poster);
                     bundle.putString("BackPoster",bkposter);
                     bundle.putString("Name",name);
                     bundle.putString("Overview",overview);
                    bundle.putString("ReleaseDate",releasedate);
                    bundle.putDouble("voteAverage",voteAverage);
                    bundle.putInt("voteCount",vcount);
                        detail_page.setArguments(bundle);
             FragmentTransaction ft =  getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout_nav,detail_page).addToBackStack(null).commit();
         }
     };
}


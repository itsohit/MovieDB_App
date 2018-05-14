package contacts.test.moviesdatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Popular_movies extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String url = "https://api.themoviedb.org/3/movie/popular?api_key=c686d702de9f7a64b3b2fe2a04f8e7b6&language=en-US&page=1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_popular_movies, container, false);

            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_popularmovies);

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Log.d("Response :",response);
                Gson gson = new GsonBuilder().create();
                layoutManager = new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                ResultAdapter adapter = new ResultAdapter(getContext(),(gson.fromJson(response,PopularMovieData.class)).getResults(),adapterOnItemClick);
                   recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
         requestQueue.add(request);
            return v;
    }
    AdapterOnItemClick adapterOnItemClick = new AdapterOnItemClick() {
        @Override
        public void onClickItem(View view, int position, String poster, String backposter, String name, String overview, String releasedate) {
            Detail_Page detail_page = new Detail_Page();
            Bundle bundle = new Bundle();
            bundle.putString("poster",poster);
            bundle.putString("BackPoster",backposter);
            bundle.putString("Name",name);
            bundle.putString("Overview",overview);
            bundle.putString("ReleaseDate",releasedate);
            detail_page.setArguments(bundle);
            FragmentTransaction ft =  getFragmentManager().beginTransaction();
            ft.add(R.id.frame_layout_nav,detail_page).addToBackStack(null).commit();
        }
    };
}

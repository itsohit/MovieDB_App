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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.http.Url;


public class TvPopular_Fragment extends Fragment {
            String url_data;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String url = "https://api.themoviedb.org/3/tv/popular?api_key=c686d702de9f7a64b3b2fe2a04f8e7b6&language=en-US&page=1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv_popular_, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_popularTv);

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new GsonBuilder().create();
                        layoutManager = new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(layoutManager);
                        TvAdapter adapter = new TvAdapter(getContext(),(gson.fromJson(response,TvPopularData.class)).getResults(),adapterOnItemClick);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               try {
//                   URL urlString = new URL(url);
//                   HttpURLConnection urlConnection = (HttpURLConnection) urlString.openConnection();
//                   if(urlConnection.getResponseCode()==200) {
//                       InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                       StringBuffer stringBuffer = new StringBuffer();
//                       while ((url_data = reader.readLine()) != null) {
//                           stringBuffer.append(url_data);
//                       }
//                       url_data = stringBuffer.toString();
//
//                   }
//               } catch (MalformedURLException e) {
//                   e.printStackTrace();
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//
//           }
//       }).start();
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

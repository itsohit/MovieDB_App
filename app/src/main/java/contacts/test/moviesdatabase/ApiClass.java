package contacts.test.moviesdatabase;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by IT sohit on 1/6/2018.
 */

public class ApiClass {
    static final String Descover_movie_url ="https://api.themoviedb.org/3/discover/movie/";
    static final String Descover_Tv_url = "https://api.themoviedb.org/3/discover/";
     static final String apikey = "c686d702de9f7a64b3b2fe2a04f8e7b6";

    static ApiInterface apiInterface = null;
    static ApiInterface getTvApiInterface(){
          if(apiInterface==null){
              Retrofit retrofit = new Retrofit.Builder()
                      .baseUrl(Descover_Tv_url)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
              apiInterface = retrofit.create(ApiInterface.class);
          }
         return apiInterface;
     }


    interface ApiInterface {
        @GET("?api_key="+apikey+"&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
        Call<DescoverMovies> getDescoverMovies();

        @GET("tv?api_key="+apikey+"&language=en-US&sort_by=popularity.desc&page=1&timezone=Indian%2FChristmas&include_null_first_air_dates=false")
        Call<DescoverTvShows> getDescoverTv();
    }
}

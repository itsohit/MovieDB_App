package contacts.test.moviesdatabase;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by IT sohit on 1/4/2018.
 */

public class UpComing_DataService {

   private static final String api_key = "c686d702de9f7a64b3b2fe2a04f8e7b6";

    //String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>&language=en-US&page=1";

   private static String url = "https://api.themoviedb.org/3/movie/upcoming/";

      public static Service service = null;

        public static Service getService(){
            if(service ==null){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(Service.class);
            }
            return service;
        }
   public interface Service {
        @GET("?api_key="+api_key+"&language=en-US&page=1")
        Call<UpComingApiData> getUpComingData();
    }
}

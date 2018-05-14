package contacts.test.moviesdatabase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IT sohit on 5/11/2018.
 */

public class NetworkCheck {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ConnectivityManager cm;
    NetworkInfo networkInfo;
    Context context;

    public NetworkCheck(Context c){
         context = c;
     }

      public Boolean checkInternet(){
          cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
           networkInfo  = (NetworkInfo) cm.getActiveNetworkInfo();
           if(networkInfo==null)
               return true;
          return false;
      }

    public Boolean checkEmail(String email){
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(matcher.find())
            return false;
        return true;
    }
}

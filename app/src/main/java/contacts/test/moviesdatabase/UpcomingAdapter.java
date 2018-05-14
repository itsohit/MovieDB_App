package contacts.test.moviesdatabase;

import android.content.Context;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IT sohit on 1/4/2018.
 */

class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.InnerClass>{

    String Imageurl ="https://image.tmdb.org/t/p/w185_and_h278_bestv2";
    Context context;
    List<Result> result;

    public UpcomingAdapter(Context context, UpComingApiData apiData) {
            this.result = apiData.getResults();
           this.context = context;
    }

    @Override
    public InnerClass onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater  inflate  = LayoutInflater.from(context);
         View v= inflate.inflate(R.layout.upcoming_recycler_layout,parent,false);
        return new InnerClass(v);
    }

    @Override
    public void onBindViewHolder(InnerClass holder, int position) {
            Result rr = result.get(position);
           holder.title.setText(rr.getTitle());
      //  Toast.makeText(context,"Url : "+rr.getPosterPath(),Toast.LENGTH_SHORT).show();
        holder.date.setText("Date : "+String.valueOf(rr.getReleaseDate()));
        Glide.with(holder.poster.getContext()).load(Imageurl+""+rr.getPosterPath()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class InnerClass extends RecyclerView.ViewHolder{
          ImageView poster;
          TextView title,date;
          public InnerClass(View itemView) {
              super(itemView);
                poster = (ImageView) itemView.findViewById(R.id.recycleview_upcoming_imageview);
                title = (TextView) itemView.findViewById(R.id.recycleview_upcoming_textview);
                date = (TextView) itemView.findViewById(R.id.recycleview_upcoming_date);
          }
      }
}

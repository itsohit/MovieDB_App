package contacts.test.moviesdatabase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by IT sohit on 1/8/2018.
 */

class DescoverMovieAdapter extends RecyclerView.Adapter<DescoverMovieAdapter.InnerClass> {
     Context context;
    String urlImage = "https://image.tmdb.org/t/p/w500/";
     List<Result> result;
    AdapterOnItemClick adapterOnItemClick;
    public DescoverMovieAdapter(Context context,List<Result> descoverMovies,AdapterOnItemClick adapterOnItemClick) {
            this.context= context;
          result = descoverMovies;
        this.adapterOnItemClick = adapterOnItemClick;
    }

    @Override
    public InnerClass onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = (LayoutInflater.from(context));
         View view = inflate.inflate(R.layout.descover_movie_layout,parent,false);
        return new InnerClass(view,adapterOnItemClick);
    }

    @Override
    public void onBindViewHolder(InnerClass holder, int position) {
           Result descoverResult = result.get(position);
          holder.name.setText(descoverResult.getTitle());
        String d = descoverResult.getReleaseDate();
           String s[] = d.split("-");
         holder.date.setText(s[0]);
          holder.rating.setText(String.valueOf(descoverResult.getVoteAverage()));
        Glide.with(holder.image.getContext()).load(urlImage+""+descoverResult.getPosterPath()).into(holder.image);
//
     }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class InnerClass extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView rating;
          TextView name,date;
          ImageView image;
        AdapterOnItemClick adapterOnItemClick;
          public InnerClass(View itemView, AdapterOnItemClick adapterOnItemClick) {
              super(itemView);
              image = (ImageView) itemView.findViewById(R.id.imageView_descover_recycleview);
              name = (TextView) itemView.findViewById(R.id.textView_descover_title_recycleview);
              rating = (TextView) itemView.findViewById(R.id.textView_descoverRate_recycleview);
              date = (TextView) itemView.findViewById(R.id.date_descover_movie);
                this.adapterOnItemClick= adapterOnItemClick;
                 itemView.setOnClickListener(this);
          }

        @Override
        public void onClick(View v) {
            Result r = result.get(getAdapterPosition());
            adapterOnItemClick.onClickItem(v,getAdapterPosition(),urlImage+""+r.getPosterPath(),urlImage+""+r.getBackdropPath(),r.getTitle(),r.getOverview(),r.getReleaseDate(),r.getVoteAverage(),r.getVoteCount());
        }
    }
}

package contacts.test.moviesdatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by IT sohit on 1/10/2018.
 */

class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.InnerClass>{
     Context context;
    AdapterOnItemClick adapterOnItemClick;
    String imageUrl = "https://image.tmdb.org/t/p/w500";
     List<DescoverTvResult> result;
    public TvshowAdapter(Context context,DescoverTvShows descoverTvShows,AdapterOnItemClick adapterOnItemClick) {
            this.context = context;
          result = descoverTvShows.getResults();
         this.adapterOnItemClick = adapterOnItemClick;
    }

    @Override
    public InnerClass onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
         View v = inflate.inflate(R.layout.descover_movie_layout,parent,false);
        return new InnerClass(v,adapterOnItemClick);
    }

    @Override
    public void onBindViewHolder(InnerClass holder, int position) {
       DescoverTvResult r = result.get(position);
        holder.name.setText(r.getName());
           String d = r.getFirstAirDate();
          String s[] = d.split("-");
         holder.date.setText(s[0]);
        holder.rate.setText(String.valueOf(r.getVoteAverage()));
        Glide.with(holder.image.getContext()).load(imageUrl+""+r.getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class InnerClass extends ViewHolder implements View.OnClickListener{
            ImageView image;
        TextView rate,date,name;
         AdapterOnItemClick adapterOnItemClick;
        public InnerClass(View itemView,AdapterOnItemClick adapterOnItemClick) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView_descover_recycleview);
            name = (TextView) itemView.findViewById(R.id.textView_descover_title_recycleview);
            rate = (TextView) itemView.findViewById(R.id.textView_descoverRate_recycleview);
            date  = (TextView) itemView.findViewById(R.id.date_descover_movie);
            this.adapterOnItemClick = adapterOnItemClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DescoverTvResult r = result.get(getAdapterPosition());
            adapterOnItemClick.onClickItem(v,getAdapterPosition(),imageUrl+""+r.getPosterPath(),imageUrl+""+r.getBackdropPath(),r.getOriginalName(),r.getOverview(),r.getFirstAirDate(),r.getVoteAverage(),r.getVoteCount());
        }
    }
}

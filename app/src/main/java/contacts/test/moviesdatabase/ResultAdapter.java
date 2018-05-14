package contacts.test.moviesdatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by IT sohit on 1/11/2018.
 */

class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.InnerClass>{
        Context context;
     String urlimage = "https://image.tmdb.org/t/p/w500";
        List<Result> result;
    AdapterOnItemClick adapterOnItemClick;
    public ResultAdapter(Context context, List<Result> results,AdapterOnItemClick adapterOnItemClick) {
            this.context = context;
          result = results;
        this.adapterOnItemClick = adapterOnItemClick;
    }

    @Override
    public InnerClass onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.upcoming_recycler_layout,parent,false);
        return new InnerClass(v,adapterOnItemClick);
    }

    @Override
    public void onBindViewHolder(InnerClass holder, int position) {
         Result rr = result.get(position);
            holder.name.setText(rr.getTitle());
         holder.vote.setText("Rating :"+rr.getVoteAverage());
        Glide.with(holder.image.getContext()).load(urlimage+""+rr.getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class InnerClass extends RecyclerView.ViewHolder implements View.OnClickListener{
            ImageView image;
          TextView name,vote;
        AdapterOnItemClick adapterOnItemClick;
        public InnerClass(View itemView,AdapterOnItemClick adapterOnItemClick) {
            super(itemView);
             image = (ImageView) itemView.findViewById(R.id.recycleview_upcoming_imageview);
             name = (TextView) itemView.findViewById(R.id.recycleview_upcoming_textview);
             vote = (TextView) itemView.findViewById(R.id.recycleview_upcoming_date);
                this.adapterOnItemClick = adapterOnItemClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
             Result r = result.get(getAdapterPosition());
            adapterOnItemClick.onClickItem(v,getAdapterPosition(),urlimage+""+r.getPosterPath(),urlimage+""+r.getBackdropPath(),r.getTitle(),r.getOverview(),r.getReleaseDate());
        }
    }
}

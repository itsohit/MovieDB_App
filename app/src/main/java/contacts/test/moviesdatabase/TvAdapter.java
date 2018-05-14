package contacts.test.moviesdatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IT sohit on 1/11/2018.
 */

class TvAdapter extends RecyclerView.Adapter<TvAdapter.InnerClass>{
    Context context;
    String imageUrl = "https://image.tmdb.org/t/p/w500";
    List<TvResult> result;
    AdapterOnItemClick adapterOnItemClick;
    public TvAdapter(Context context, List<TvResult> results,AdapterOnItemClick adapterOnItemClick) {
                this.context = context;
           result = results;
        this.adapterOnItemClick = adapterOnItemClick;
    }

    @Override
    public InnerClass onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.tvlayout_recyclerview,parent,false);
        return new InnerClass(v,adapterOnItemClick);
    }

    @Override
    public void onBindViewHolder(InnerClass holder, int position) {
         TvResult tr = result.get(position);
        holder.title.setText(tr.getName());
        holder.rate.setText(String.valueOf(tr.getVoteAverage()));
        SimpleDateFormat sd = new SimpleDateFormat(tr.getFirstAirDate());
        String[] cal = tr.getFirstAirDate().split("-");
        try {
            holder.date.setText(cal[0]);
        }catch(Exception e){
        }
       // int cal = sd.YEAR_FIELD;
          //    int i =  d.getYear();
        Glide.with(holder.image.getContext()).load(imageUrl+""+tr.getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class InnerClass extends RecyclerView.ViewHolder implements View.OnClickListener{
            ImageView image;
        AdapterOnItemClick adapterOnItemClick;
        TextView title,date,rate;
        public InnerClass(View itemView,AdapterOnItemClick adapterOnItemClick) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.tv_recyclerview_layout_image);
            title = (TextView) itemView.findViewById(R.id.tv_recyclerview_layout_title);
            date = (TextView) itemView.findViewById(R.id.tv_recyclerview_layout_date_text);
            rate = (TextView) itemView.findViewById(R.id.tv_recyclerview_layout_rate_text);
            this.adapterOnItemClick = adapterOnItemClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                TvResult r = result.get(getAdapterPosition());
            adapterOnItemClick.onClickItem(v,getAdapterPosition(),imageUrl+""+r.getPosterPath(),imageUrl+""+r.getBackdropPath(),r.getOriginalName(),r.getOverview(),r.getFirstAirDate());
        }
    }
}

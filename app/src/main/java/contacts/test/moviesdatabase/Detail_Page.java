package contacts.test.moviesdatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static android.R.attr.fragment;


public class Detail_Page extends Fragment {
        String poster,backposter,name,overview,releasedate;
        ImageView frontposter,backimage;
       TextView titlename,detail,date;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            poster = getArguments().getString("poster");
            backposter = getArguments().getString("BackPoster");
            name = getArguments().getString("Name");
            overview = getArguments().getString("Overview");
            releasedate = getArguments().getString("ReleaseDate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_detail__page, container, false);
                frontposter = (ImageView) v.findViewById(R.id.poster_image_detailPage);
                backimage = (ImageView) v.findViewById(R.id.background_img_detailPage);
                titlename = (TextView) v.findViewById(R.id.textview_titlename_detailPage);
                detail = (TextView) v.findViewById(R.id.textview_overview_detialPage);
                date = (TextView) v.findViewById(R.id.releasedate_detailPage);

                titlename.setText(name);
                detail.setText(overview);
                date.setText("Release Date : "+releasedate);
        Glide.with(getActivity()).load(poster).into(frontposter);
        Glide.with(getContext()).load(backposter).into(backimage);


        return v;
    }
}

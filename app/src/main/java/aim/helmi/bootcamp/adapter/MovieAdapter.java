package aim.helmi.bootcamp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.model.Movie;
import aim.helmi.bootcamp.ui.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mList;
    private Context context;

    public MovieAdapter(Context context, List<Movie> data) {
        this.context = context;
        this.mList = data;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_views, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mList.get(i));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView title;

        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.IvPoster);
            title = itemView.findViewById(R.id.TvTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Movie movie = mList.get(position);

                    movie.setTitle(movie.getTitle());
                    movie.setSynopsis(movie.getSynopsis());

                    Intent i = new Intent(itemView.getContext(), DetailActivity.class);
                    i.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                    itemView.getContext().startActivity(i);

                }
            });
        }

        public void bind(Movie movie) {

            String url_img = movie.getImage();

            title.setText(movie.getTitle());
            Glide.with(itemView.getContext())
                    .load(url_img)
                    .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(100)))
                    .into(poster);
        }
    }
}

package aim.helmi.bootcamp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.comment.CommentActivity;
import aim.helmi.bootcamp.model.Movie;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private ProgressBar PbDetailMovie;
    Toolbar appBar;

    private ImageView poster;
    private ImageView backdrop;
    private TextView title;
    private TextView date;
    private TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        title = findViewById(R.id.jdl_detail);
        date = findViewById(R.id.rilis_detail);
        poster = findViewById(R.id.img_detail);
        backdrop = findViewById(R.id.img_backdrop);
        overview = findViewById(R.id.deskripsi_detail);

        PbDetailMovie = findViewById(R.id.PbDetailMovie);

        PbDetailMovie.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        String url_image = movie.getImage();

                        title.setText(movie.getTitle());
                        overview.setText(movie.getSynopsis());
                        date.setText(movie.getTimeStamp());
                        Glide.with(DetailActivity.this)
                                .load(url_image)
                                .into(poster);

                        Glide.with(DetailActivity.this)
                                .load(url_image)
                                .into(backdrop);

                        PbDetailMovie.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();

    }

    private void initView(){
        appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_comment:
                Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
                startActivity(intent);
                break;
            case R.id.action_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

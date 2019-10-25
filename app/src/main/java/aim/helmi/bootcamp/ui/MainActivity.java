package aim.helmi.bootcamp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import aim.helmi.bootcamp.dialog.DialogActivity;
import aim.helmi.bootcamp.login.LoginActivity;
import aim.helmi.bootcamp.network.ApiClient;
import aim.helmi.bootcamp.adapter.MovieAdapter;
import aim.helmi.bootcamp.utils.Preference;
import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    FloatingActionButton fabLogout;
    Preference preference;
    MovieAdapter adapter;
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new Preference(this);

        initView();

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        fabLogout = findViewById(R.id.fabLogout);
        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preference.setIsLogin(false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://limaefdua.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient api = retrofit.create(ApiClient.class);

        api.movieList().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> data = response.body();
                adapter = new MovieAdapter(MainActivity.this, data);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                DialogActivity dialog = new DialogActivity(MainActivity.this);
                dialog.show();
                dialog.setTitle("ERROR");
                dialog.setMesssage(t.getMessage() + "");
            }
        });
    }

    private void initView(){
        appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        setTitle("Daftar Film");
    }


}
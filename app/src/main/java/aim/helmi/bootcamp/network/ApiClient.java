package aim.helmi.bootcamp.network;

import java.util.List;

import aim.helmi.bootcamp.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("movie.json")
    Call<List<Movie>> movieList();
}

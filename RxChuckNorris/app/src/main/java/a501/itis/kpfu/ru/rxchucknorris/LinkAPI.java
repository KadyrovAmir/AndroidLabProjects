package a501.itis.kpfu.ru.rxchucknorris;

import a501.itis.kpfu.ru.rxchucknorris.joke.Joke;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Амир on 23.12.2016.
 */

public interface LinkAPI {
    @GET("jokes/random")
    Observable<Joke> getJoke();
}

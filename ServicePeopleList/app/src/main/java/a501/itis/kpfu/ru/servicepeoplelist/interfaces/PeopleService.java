package a501.itis.kpfu.ru.servicepeoplelist.interfaces;

import a501.itis.kpfu.ru.servicepeoplelist.api.PeopleResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Амир on 13.03.2017.
 */

public interface PeopleService {
    @GET("api/?results=20")
    Call<PeopleResponse> getPeople();
}

package a501.itis.kpfu.ru.servicepeoplelist.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import a501.itis.kpfu.ru.servicepeoplelist.activities.MainActivity;
import a501.itis.kpfu.ru.servicepeoplelist.api.People;
import a501.itis.kpfu.ru.servicepeoplelist.api.PeopleResponse;
import a501.itis.kpfu.ru.servicepeoplelist.interfaces.PeopleService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RandomUserGetterService extends IntentService {

    public static final String DATA = "a501.itis.kpfu.ru.servicepeoplelist";

    public RandomUserGetterService() {
        super("RandomUserGetterService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://randomuser.me/").addConverterFactory(GsonConverterFactory.create())
                .build();
        PeopleService service = retrofit.create(PeopleService.class);
        Call<PeopleResponse> list = service.getPeople();
        List<People> peopleList = null;
        try {
            peopleList = list.execute().body().getPeopleList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> peopleListJson = new ArrayList<>();
        Gson gson = new Gson();
        for (People human : peopleList) {
            peopleListJson.add(gson.toJson(human));
        }
        SharedPreferences shPref = getApplicationContext().getSharedPreferences(DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shPref.edit();
        int keyHelper = 0;
        for (String human : peopleListJson) {
            keyHelper++;
            String key = "human" + keyHelper;
            editor.putString(key, human);
        }
        editor.apply();
        Intent responseIntent = new Intent(MainActivity.BROADCAST);
        sendBroadcast(responseIntent);
    }
}

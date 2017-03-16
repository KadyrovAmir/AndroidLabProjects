package a501.itis.kpfu.ru.servicepeoplelist.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import a501.itis.kpfu.ru.servicepeoplelist.R;
import a501.itis.kpfu.ru.servicepeoplelist.adapter.PeopleListAdapter;
import a501.itis.kpfu.ru.servicepeoplelist.api.People;
import a501.itis.kpfu.ru.servicepeoplelist.interfaces.PeopleClickListener;
import a501.itis.kpfu.ru.servicepeoplelist.services.RandomUserGetterService;

public class MainActivity extends AppCompatActivity implements PeopleClickListener {

    public final static String BROADCAST = "a501.itis.kpfu.ru.servicepeoplelist";

    private RecyclerView recyclerView;
    private PeopleListAdapter mAdapter;
    private ResponseReceiver responseReceiver;
    private ArrayList<People> peopleList = new ArrayList<>();
    private ArrayList<String> peopleListJson = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseReceiver = new ResponseReceiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(responseReceiver, intentFilter);
        if (savedInstanceState == null) {
            startService(new Intent(this, RandomUserGetterService.class));
        }
        else {
            displayList();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("peopleListJson", peopleListJson);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        peopleListJson = savedInstanceState.getStringArrayList("peopleListJson");

        Gson gson = new Gson();
        for (String human : peopleListJson) {
            People people = gson.fromJson(human, People.class);
            peopleList.add(people);
        }
    }

    @Override
    public void onHumanClick(People human) {
        Intent intent = new Intent(MainActivity.this, PeopleInfoActivity.class);
        intent.putExtra("image", human.getPicture().getLarge());
        intent.putExtra("surname", human.getName().getLast());
        intent.putExtra("name", human.getName().getFirst());
        intent.putExtra("email", human.getEmail());
        intent.putExtra("username", human.getLogin().getUsername());
        intent.putExtra("city", human.getLocation().getCity());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(responseReceiver);
    }

    public class ResponseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Gson gson = new Gson();
            SharedPreferences shPref = MainActivity.this.getSharedPreferences(RandomUserGetterService.DATA, Context.MODE_PRIVATE);
            for (int i = 1; i <= 20; i++) {
                String key = "human" + i;
                peopleListJson.add(shPref.getString(key, "empty"));
            }
            for (String human : peopleListJson) {
                People people = gson.fromJson(human, People.class);
                peopleList.add(people);
            }
            displayList();
        }
    }

    public void displayList() {
        recyclerView = (RecyclerView)findViewById(R.id.people_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new PeopleListAdapter(peopleList, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setListener(MainActivity.this);
    }
}

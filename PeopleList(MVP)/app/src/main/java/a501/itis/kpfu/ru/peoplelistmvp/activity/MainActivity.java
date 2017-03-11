package a501.itis.kpfu.ru.peoplelistmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import a501.itis.kpfu.ru.peoplelistmvp.R;
import a501.itis.kpfu.ru.peoplelistmvp.adapter.PeopleAdapter;
import a501.itis.kpfu.ru.peoplelistmvp.interfaces.HumanClickListener;
import a501.itis.kpfu.ru.peoplelistmvp.model.Human;
import a501.itis.kpfu.ru.peoplelistmvp.presenter.MainPresenter;
import a501.itis.kpfu.ru.peoplelistmvp.tools.Tools;
import a501.itis.kpfu.ru.peoplelistmvp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;
    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_main);
        peopleAdapter = new PeopleAdapter();
        recyclerView.setAdapter(peopleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainPresenter = new MainPresenter(this);

        peopleAdapter.setListener(new HumanClickListener() {
            @Override
            public void onHumanClick(Human human) {
                mainPresenter.humanItemClick(human);
            }
        });
    }

    @Override
    public void editHuman(Human human) {
        Intent intent = new Intent(this, HumanEditActivity.class);
        intent = Tools.fillIntent(intent, human);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setPeopleList(this.mainPresenter.getUserList());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPeopleList(List<Human> list) {
        this.peopleAdapter.setList(list);
    }
}

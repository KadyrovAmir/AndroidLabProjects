package a501.itis.kpfu.ru.peoplelistmvp.presenter;

import java.util.ArrayList;
import java.util.List;

import a501.itis.kpfu.ru.peoplelistmvp.model.Human;
import a501.itis.kpfu.ru.peoplelistmvp.tools.PeopleProvider;
import a501.itis.kpfu.ru.peoplelistmvp.tools.Tools;
import a501.itis.kpfu.ru.peoplelistmvp.view.MainView;

/**
 * Created by Амир on 08.03.2017.
 */

public class MainPresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.mainView.setPeopleList(this.getUserList());
    }

    public List<Human> getUserList(){
        PeopleProvider peopleProvider = PeopleProvider.getInstance();
        if (peopleProvider.getPeopleList() == null) {
            List<Human> peopleList = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                peopleList.add(Tools.createHuman());
            }
            peopleProvider.setPeopleList(peopleList);
        }
        return peopleProvider.getPeopleList();
    }

    public void humanItemClick(Human human) {
        this.mainView.editHuman(human);
    }
}

package a501.itis.kpfu.ru.peoplelistmvp.view;

import java.util.List;

import a501.itis.kpfu.ru.peoplelistmvp.model.Human;

/**
 * Created by Амир on 11.03.2017.
 */

public interface MainView {

    void editHuman(Human human);

    void setPeopleList(List<Human> peopleList);

}

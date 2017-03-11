package a501.itis.kpfu.ru.peoplelistmvp.tools;

import java.util.List;

import a501.itis.kpfu.ru.peoplelistmvp.model.Human;

/**
 * Created by Амир on 11.03.2017.
 */

public class PeopleProvider {
    private List<Human> peopleList;
    private static PeopleProvider peopleProvider = new PeopleProvider();

    public static PeopleProvider getInstance() {
        return peopleProvider;
    }

    public List<Human> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<Human> peopleList) {
        this.peopleList = peopleList;
    }
}

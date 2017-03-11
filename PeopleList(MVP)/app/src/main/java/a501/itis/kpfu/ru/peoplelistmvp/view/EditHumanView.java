package a501.itis.kpfu.ru.peoplelistmvp.view;

import a501.itis.kpfu.ru.peoplelistmvp.model.Human;

/**
 * Created by Амир on 11.03.2017.
 */

public interface EditHumanView {
    String getName();

    String getSurname();

    String getEmail();

    Human getHuman();

    void setHuman(Human human);

    void showHuman(Human human);

    void exit();
}

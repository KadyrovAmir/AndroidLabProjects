package a501.itis.kpfu.ru.peoplelistmvp.presenter;

import java.util.List;

import a501.itis.kpfu.ru.peoplelistmvp.model.Human;
import a501.itis.kpfu.ru.peoplelistmvp.tools.PeopleProvider;
import a501.itis.kpfu.ru.peoplelistmvp.view.EditHumanView;

/**
 * Created by Амир on 11.03.2017.
 */

public class EditHumanPresenter {
    private EditHumanView editView;

    public EditHumanPresenter(EditHumanView editView) {
        this.editView = editView;
        this.editView.showHuman(this.editView.getHuman());
    }

    public void saveClick(){
        Human oldHuman = editView.getHuman();
        PeopleProvider peopleProvider = PeopleProvider.getInstance();
        List<Human> newList = peopleProvider.getPeopleList();
        int index = -1;
        for (Human human: newList) {
            if (human.getName().equals(oldHuman.getName()) &&
                    human.getSurname().equals(oldHuman.getSurname()) &&
                    human.getEmail().equals(oldHuman.getEmail())) {
                index = newList.indexOf(human);
            }
        }
        newList.remove(index);
        Human newHuman =
                new Human(editView.getName(),
                editView.getSurname(),
                editView.getEmail());
        newList.add(index, newHuman);
        peopleProvider.setPeopleList(newList);
        editView.setHuman(newHuman);
        editView.exit();
    }
}

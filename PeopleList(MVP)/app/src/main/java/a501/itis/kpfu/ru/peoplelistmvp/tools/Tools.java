package a501.itis.kpfu.ru.peoplelistmvp.tools;

import android.content.Intent;
import android.os.Bundle;

import a501.itis.kpfu.ru.peoplelistmvp.data.EmailProvider;
import a501.itis.kpfu.ru.peoplelistmvp.data.NameProvider;
import a501.itis.kpfu.ru.peoplelistmvp.data.SurnameProvider;
import a501.itis.kpfu.ru.peoplelistmvp.model.Human;

/**
 * Created by Амир on 11.03.2017.
 */

public class Tools {

    public static Human createHuman() {
        String name = NameProvider.provideName();
        String surname = SurnameProvider.provideSurname();
        String email = EmailProvider.provideEmail();
        return new Human(name, surname, email);
    }

    public static Intent fillIntent(Intent intent, Human human) {
        intent.putExtra(Human.ATTRIBUTE_NAME, human.getName());
        intent.putExtra(Human.ATTRIBUTE_SURNAME, human.getSurname());
        intent.putExtra(Human.ATTRIBUTE_EMAIL, human.getEmail());
        return intent;
    }

    public static Human getHumanFromIntent(Intent intent) {
        return new Human(intent.getStringExtra(Human.ATTRIBUTE_NAME),
                intent.getStringExtra(Human.ATTRIBUTE_SURNAME),
                intent.getStringExtra(Human.ATTRIBUTE_EMAIL));
    }

    public static Bundle fillBundle(Bundle bundle, Human human) {
        bundle.putString(Human.ATTRIBUTE_NAME, human.getName());
        bundle.putString(Human.ATTRIBUTE_SURNAME, human.getSurname());
        bundle.putString(Human.ATTRIBUTE_EMAIL, human.getEmail());
        return bundle;
    }

    public static Human getFromBundle(Bundle bundle) {
        return new Human(bundle.getString(Human.ATTRIBUTE_NAME),
                bundle.getString(Human.ATTRIBUTE_SURNAME),
                bundle.getString(Human.ATTRIBUTE_EMAIL));
    }
}

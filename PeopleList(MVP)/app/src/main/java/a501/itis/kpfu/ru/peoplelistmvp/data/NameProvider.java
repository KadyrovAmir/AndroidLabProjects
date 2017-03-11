package a501.itis.kpfu.ru.peoplelistmvp.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Амир on 08.03.2017.
 */

public class NameProvider {
    private static List<String> names = Arrays.asList("Bernard", "Monica", "Petr", "Ayrat", "Maxim", "Jacob", "Eliza", "Nina", "James", "Diana");
    public static String provideName() {
        Random random = new Random();
        int index = random.nextInt(names.size());
        return names.get(index);
    }
}

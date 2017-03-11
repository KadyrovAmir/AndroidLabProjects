package a501.itis.kpfu.ru.peoplelistmvp.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Амир on 08.03.2017.
 */

public class SurnameProvider {
    private static List<String> surnames = Arrays.asList("Obama", "Bond", "Smith", "Carry", "Trump", "Wyndham", "Garrett", "Evered", "Scriven", "Bean");
    public static String provideSurname() {
        Random random = new Random();
        int index = random.nextInt(surnames.size());
        return surnames.get(index);
    }
}

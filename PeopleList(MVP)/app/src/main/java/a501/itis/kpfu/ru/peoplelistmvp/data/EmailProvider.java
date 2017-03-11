package a501.itis.kpfu.ru.peoplelistmvp.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Амир on 08.03.2017.
 */

public class EmailProvider {
    private static List<String> emails = Arrays.asList("-nshyr.xk6j@d7zhtn2o1.com",
            "ddj4k2o0ilj1t9f@rspk1sgpz14q.com",
            "qbxu@km4-1zha9.com",
            "6b.lrla-sqzhid@k1tmlbxy5.com",
            "t7or39j49vi@gufx-45i.com",
            "-qrkrd0k2l5v1i@kuqbqz.com",
            "vqjvib@3ef1tw-0z8.com",
            "ha_8ij@pyk6yuri.com",
            "5g4vl0k6uj5ieax@laugi4w8dq.com",
            "05xyw4oebl@n65wxa1xxb.com");

    public static String provideEmail() {
        Random random = new Random();
        int index = random.nextInt(emails.size());
        return emails.get(index);
    }
}

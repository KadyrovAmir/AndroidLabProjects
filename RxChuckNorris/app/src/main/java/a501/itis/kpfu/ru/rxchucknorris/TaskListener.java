package a501.itis.kpfu.ru.rxchucknorris;

/**
 * Created by Амир on 19.05.2017.
 */

public interface TaskListener {
    void onTaskFinish(String joke);
    void onTaskStarted();
}

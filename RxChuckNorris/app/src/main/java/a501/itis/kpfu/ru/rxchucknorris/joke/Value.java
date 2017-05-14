package a501.itis.kpfu.ru.rxchucknorris.joke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Амир on 23.12.2016.
 */

public class Value {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("joke")
    @Expose
    private String joke;
    @SerializedName("categories")
    @Expose
    private List<Object> categories = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

}

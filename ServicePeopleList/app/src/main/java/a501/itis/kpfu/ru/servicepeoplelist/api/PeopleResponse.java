
package a501.itis.kpfu.ru.servicepeoplelist.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PeopleResponse implements Serializable {

    @SerializedName("results")
    @Expose
    private List<People> peopleList = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


}

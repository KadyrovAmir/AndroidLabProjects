package a501.itis.kpfu.ru.peoplelistmvp.model;

/**
 * Created by Амир on 08.03.2017.
 */

public class Human {

    public static final String ATTRIBUTE_NAME = "human_name";
    public static final String ATTRIBUTE_SURNAME = "human_surname";
    public static final String ATTRIBUTE_EMAIL = "human_email";

    private String name;
    private String surname;
    private String email;

    public Human(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

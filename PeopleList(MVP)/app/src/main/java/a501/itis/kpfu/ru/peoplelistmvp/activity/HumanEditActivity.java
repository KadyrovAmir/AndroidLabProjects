package a501.itis.kpfu.ru.peoplelistmvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import a501.itis.kpfu.ru.peoplelistmvp.R;
import a501.itis.kpfu.ru.peoplelistmvp.model.Human;
import a501.itis.kpfu.ru.peoplelistmvp.presenter.EditHumanPresenter;
import a501.itis.kpfu.ru.peoplelistmvp.tools.Tools;
import a501.itis.kpfu.ru.peoplelistmvp.view.EditHumanView;

public class HumanEditActivity extends AppCompatActivity implements EditHumanView {

    private EditText etName, etSurname, etEmail;
    private Button btnSave;
    private EditHumanPresenter editHumanPresenter;
    private Human human;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        if (savedInstanceState == null) {
            this.human = Tools.getHumanFromIntent(getIntent());
        } else {
            this.human = loadHuman(savedInstanceState);
        }

        etName = (EditText) findViewById(R.id.et_name);
        etSurname = (EditText) findViewById(R.id.et_surname);
        etEmail = (EditText) findViewById(R.id.et_email);

        btnSave = (Button) findViewById(R.id.btn_save);
        editHumanPresenter = new EditHumanPresenter(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editHumanPresenter.saveClick();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        this.saveUser(outState, this.getHuman());
        super.onSaveInstanceState(outState);
    }

    private void saveUser(Bundle bundle, Human human) {
        Tools.fillBundle(bundle, human);
    }

    private Human loadHuman(Bundle savedInstanceState) {
        return Tools.getFromBundle(savedInstanceState);
    }

    @Override
    public String getName() {
        return this.etName.getText().toString();
    }

    @Override
    public String getSurname() {
        return this.etSurname.getText().toString();
    }

    @Override
    public String getEmail() {
        return this.etEmail.getText().toString();
    }

    @Override
    public Human getHuman() {
        return this.human;
    }

    @Override
    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public void showHuman (Human human) {
        etName.setText(human.getName());
        etSurname.setText(human.getSurname());
        etEmail.setText(human.getEmail());
    }

    @Override
    public void exit() {
        finish();
    }

}

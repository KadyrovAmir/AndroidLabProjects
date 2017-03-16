package a501.itis.kpfu.ru.servicepeoplelist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import a501.itis.kpfu.ru.servicepeoplelist.R;

public class PeopleInfoActivity extends AppCompatActivity {

    ImageView image;
    TextView surname;
    TextView name;
    TextView email;
    TextView username;
    TextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_info);

        image = (ImageView) findViewById(R.id.people_image_huge);
        surname = (TextView) findViewById(R.id.people_surname);
        name = (TextView) findViewById(R.id.people_name);
        email = (TextView) findViewById(R.id.people_email);
        username = (TextView) findViewById(R.id.people_username);
        city = (TextView) findViewById(R.id.people_city);

        Intent intent = getIntent();

        Picasso.with(this)
                .load(intent.getStringExtra("image"))
                .into(image);
        surname.setText("Surname: " + intent.getStringExtra("surname"));
        name.setText("Name: " + intent.getStringExtra("name"));
        email.setText("Email: " + intent.getStringExtra("email"));
        username.setText("Username: " + intent.getStringExtra("username"));
        city.setText("City: " + intent.getStringExtra("city"));
    }

}

package a501.itis.kpfu.ru.rxchucknorris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import a501.itis.kpfu.ru.rxchucknorris.joke.Joke;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;
    private ProgressBar progressBar;

    private static final String KEY_CURRENT_PROGRESS_VISIBILITY = "current_progress";
    private static final String KEY_CURRENT_TEXT = "current_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            switch (savedInstanceState.getInt(KEY_CURRENT_PROGRESS_VISIBILITY)) {
                case View.VISIBLE :
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case View.GONE :
                    progressBar.setVisibility(View.GONE);
                    break;
            }
            tv.setText(savedInstanceState.getString(KEY_CURRENT_TEXT));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Gson gson = new GsonBuilder().create();
                Retrofit retrofit = new Retrofit.Builder()
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl("http://api.icndb.com/")
                        .build();
                LinkAPI interfaceAPI = retrofit.create(LinkAPI.class);
                Observable<Joke> joke = interfaceAPI.getJoke();
                joke.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Joke>() {
                            @Override
                            public void onCompleted() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "Упс! Что-то пошло не так!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onNext(Joke joke) {
                                tv.setText(joke.getValue().getJoke());
                            }
                        });
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PROGRESS_VISIBILITY, progressBar.getVisibility());
        outState.putString(KEY_CURRENT_TEXT, tv.getText().toString());
    }
}

package a501.itis.kpfu.ru.rxchucknorris;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

/**
 * Created by Амир on 19.05.2017.
 */

public class AsyncFragment extends Fragment {
    TaskListener mTaskListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTaskListener = (TaskListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mTaskListener = (TaskListener) activity;
    }

    public void getJoke(final TextView tv, final ProgressBar progressBar){
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
                        tv.setText("Произошла ошибка!");
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Joke joke) {
                        tv.setText(joke.getValue().getJoke());
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTaskListener = null;
    }


}

package com.pursuit.appfromscratch032319;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.pursuit.appfromscratch032319.model.StatesWrapper;
import com.pursuit.appfromscratch032319.network.GetApi;
import com.pursuit.appfromscratch032319.network.RetrofitSingleton;
import com.pursuit.appfromscratch032319.recyclerview.StatesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private List<StatesWrapper.State> stateList = new ArrayList<>();
    //private Retrofit retrofit;
    private RecyclerView recyclerView;
    private StatesAdapter statesAdapter;
    private SearchView stateSearchView;

    //private final CompositeDisposable disposable = new CompositeDisposable();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        gettingStateList();
        searchViewListener();

    }

    public void initializeView() {
        recyclerView = findViewById(R.id.my_recyclerview);
        stateSearchView = findViewById(R.id.states_searchView);
    }

    @SuppressLint("CheckResult")
    public void gettingStateList() {

//        retrofit = RetrofitSingleton.getInstance();
//        retrofit.create(GetApi.class)
//                .getStates()
//                .enqueue(new Callback<StatesWrapper>() {
//                    @Override
//                    public void onResponse(Call<StatesWrapper> call, Response<StatesWrapper> response) {
//                        Log.d("Checking if the call works: ", "onResponse: " + response.body().AK.getName());
//                        stateList.addAll(response.body().getStateList());
//                        populateRecyclerView(stateList);
//                    }
//
//                    @Override
//                    public void onFailure(Call<StatesWrapper> call, Throwable t) {
//                        Log.e("KHAING!", "onFailure: " + t.getMessage());
//                    }
//                });


        RetrofitSingleton
                .getInstance()
                .create(GetApi.class)
                .getStates()
                .subscribeOn(Schedulers.io())
                .map(StatesWrapper::getStateList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        statesList -> {
                            Log.d("Checking if the call works: ", "onResponse: " + statesList.get(0).getName());
                            this.stateList = statesList;
                            populateRecyclerView(stateList);
                        },
                        throwable -> Log.e("KHAING!: ", "onFailure: " + throwable));


    }



    public void populateRecyclerView(List<StatesWrapper.State> stateList) {
        statesAdapter = new StatesAdapter(stateList);
        recyclerView.setAdapter(statesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void searchViewListener() {
        stateSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<StatesWrapper.State> newStateList = new ArrayList<>();
        for (StatesWrapper.State state : stateList) {
            if (state.getName().toLowerCase().startsWith(newText.toLowerCase())) {
                newStateList.add(state);
            }
        }
        statesAdapter.setData(newStateList);
        return false;

//
//        Observable.fromArray(stateList)
//                .flatMapIterable(stateList -> stateList)
//                .filter(state -> state.capital.toLowerCase().startsWith(newText.toLowerCase()))
//                .toList()
//                .subscribe(states -> statesAdapter.setData(states));
//        return false;

    }

}


//put your onSubscribe in disposable to prevent memory leak.
//    disposable.add(
//            .getStates()
//                .subscribeOn(Schedulers.io())
//            //.map(statesWrapper ->stateList)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//            states -> {
////
//        Log.d("Checking if the call works: ", "onResponse: " + states.AK.getName());
//
//    },
//    throwable -> Log.e("KHAING!: ", "onFailure: " + throwable));
//
//
//            )

//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        disposable.clear();
//        super.onStop();


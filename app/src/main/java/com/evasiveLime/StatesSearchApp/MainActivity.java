package com.evasiveLime.StatesSearchApp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.evasiveLime.StatesSearchApp.data.model.StatesWrapper;
import com.evasiveLime.StatesSearchApp.network.GetApi;
import com.evasiveLime.StatesSearchApp.network.RetrofitSingleton;
import com.evasiveLime.StatesSearchApp.presentation.recyclerview.StatesAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private List<StatesWrapper.State> stateList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StatesAdapter statesAdapter;
    private SearchView stateSearchView;


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

        RetrofitSingleton
                .getInstance()
                .create(GetApi.class)
                .getStates()
                .subscribeOn(Schedulers.io())
                .map(StatesWrapper::getStateList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        statesList -> {
                            this.stateList = statesList;
                            populateRecyclerView(stateList);
                        },
                        throwable -> Log.e(getString(R.string.retrofit_msg), getString(R.string.failure_msg) + throwable));

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
    }

}



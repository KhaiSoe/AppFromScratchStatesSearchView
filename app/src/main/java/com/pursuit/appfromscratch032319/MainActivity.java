package com.pursuit.appfromscratch032319;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import com.pursuit.appfromscratch032319.model.StatesWrapper;
import com.pursuit.appfromscratch032319.network.GetApi;
import com.pursuit.appfromscratch032319.network.RetrofitSingleton;
import com.pursuit.appfromscratch032319.recyclerview.StatesAdapter;

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

        RetrofitSingleton.getInstance()
                .create(GetApi.class)
                .getStates()
                .subscribeOn(Schedulers.io())
                //.map(statesWrapper ->stateList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        states -> {
//                            for(){
//                                stateList.add(state);
//                            }
                            //stateList.addAll(states);
                            Log.d("Checking if the call works: ", "onResponse: " + states.AK.getName());

                        },
                        throwable -> Log.e("KHAING!: ", "onFailure: " + throwable));


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<StatesWrapper.State> newStateList = new ArrayList<>();
        for(StatesWrapper.State state : stateList){
            if(state.getName().toLowerCase().startsWith(newText.toLowerCase())){
                newStateList.add(state);
            }
        }
        statesAdapter.setData(newStateList);
        return false;
    }
}

package com.pursuit.StatesSearch.presentation.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pursuit.StatesSearch.R;
import com.pursuit.StatesSearch.data.model.StatesWrapper;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesViewHolder> {
    private List<StatesWrapper.State> stateList;

    public StatesAdapter(List<StatesWrapper.State> stateList) {
        this.stateList = stateList;
    }

    @NonNull
    @Override
    public StatesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StatesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.states_itemview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatesViewHolder statesViewHolder, int i) {
        statesViewHolder.onBind(stateList.get(i));
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public void setData(List<StatesWrapper.State> stateList) {
        this.stateList = stateList;
        notifyDataSetChanged();

    }
}


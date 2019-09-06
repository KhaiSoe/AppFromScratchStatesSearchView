package com.evasiveLime.StatesSearchApp.presentation.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.evasiveLime.StatesSearchApp.R;
import com.evasiveLime.StatesSearchApp.data.model.StatesWrapper;

public class StatesViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private TextView capitalTextView;
    private TextView latTextView;
    private TextView longTextView;

    public StatesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.state_name);
        capitalTextView = itemView.findViewById(R.id.state_capital);
        latTextView = itemView.findViewById(R.id.state_lat);
        longTextView = itemView.findViewById(R.id.state_long);

    }

    public void onBind(StatesWrapper.State state) {
        nameTextView.setText(state.getName());
        capitalTextView.setText(state.getCapital());
        latTextView.setText(state.getLat());
        longTextView.setText(state.getLong());
    }
}


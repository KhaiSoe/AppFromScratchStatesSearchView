package com.pursuit.appfromscratch032319.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pursuit.appfromscratch032319.R;
import com.pursuit.appfromscratch032319.model.StatesWrapper;

public class StatesViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private TextView capitalTextView;

    public StatesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.state_name_textview);
        capitalTextView = itemView.findViewById(R.id.state_capital_textview);
    }

    public void onBind(StatesWrapper.State state) {
        nameTextView.setText(state.getName());
        capitalTextView.setText(state.getCapital());
    }
}

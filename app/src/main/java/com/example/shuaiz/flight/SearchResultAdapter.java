package com.example.shuaiz.flight;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.shuaiz.flight.MainActivity.BUNDLE_KEY_END;
import static com.example.shuaiz.flight.MainActivity.BUNDLE_KEY_START;

class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private Flight[] data = new Flight[]{};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.flightStartText.setText(data[position].getStart());
        holder.flightEndText.setText(data[position].getEnd());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setData(Flight[] data) {
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView flightStartText;
        TextView flightEndText;
        ImageView flightIconImage;

        ViewHolder(View itemView) {
            super(itemView);
            flightStartText = itemView.findViewById(R.id.flight_start);
            flightEndText = itemView.findViewById(R.id.flight_end);
            flightIconImage = itemView.findViewById(R.id.flight_icon);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                intent.putExtra(BUNDLE_KEY_START, flightStartText.getText());
                intent.putExtra(BUNDLE_KEY_END, flightEndText.getText());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

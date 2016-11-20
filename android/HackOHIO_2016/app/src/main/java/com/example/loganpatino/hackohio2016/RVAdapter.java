package com.example.loganpatino.hackohio2016;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        //TextView name;
        ImageView image;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            //name = (TextView)itemView.findViewById(R.id.person_name);
            image = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<Card> cards;

    RVAdapter(List<Card> cards){
        this.cards = cards;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        CardViewHolder pvh = new CardViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        //cardViewHolder.name.setText(cards.get(i).name);

        cardViewHolder.image.setImageResource(cards.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


}

